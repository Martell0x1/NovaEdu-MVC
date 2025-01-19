package Model;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Objects;

import org.mindrot.jbcrypt.BCrypt;

public class DatabaseOperations {
    private  Connection connection;
    private static DatabaseOperations instace;
    private Person person;
    private Student student;

    private DatabaseOperations() throws SQLException {
        connection = DatabaseConnect.getConnection();
        if(connection == null || connection.isClosed())
            throw new SQLException("Connection Refused");
    }


    private String Hash(String string){
        return BCrypt.hashpw(string,BCrypt.gensalt());
    }
    private  boolean PersonEdit(Person person) throws SQLException {
        ResultSet rs1 = null;
        int personId = -1;

        String Query = "INSERT INTO Person(FirstName,LastName,Address,Gender,Birthdate) VALUES (?,?,?,?,?)";
        PreparedStatement insert = connection.prepareStatement(Query, Statement.RETURN_GENERATED_KEYS);
        insert.setString(1, person.getFirstName());
        insert.setString(2, person.getLastName());
        insert.setString(3,person.getAddress());
        insert.setString(4, person.getGender());
        Date sqlDate = new Date(person.getBirthDate().getTime());
        insert.setDate(5, sqlDate);
        int rowsAffected = insert.executeUpdate();
        if (rowsAffected > 0) {
            ResultSet rs = insert.getGeneratedKeys();
            if (rs.next()) {
                personId = rs.getInt(1);
                person.setId(personId);
            } else {
                System.out.println("Error: No ID generated for Person.");
                return false;
            }
        }
        return rowsAffected > 0;
    }

    private boolean EditPhone(Person person) throws SQLException {
        String Query = "INSERT into Phone (Phone,PersonID) values (?,?)";
        PreparedStatement insert = connection.prepareStatement(Query,Statement.RETURN_GENERATED_KEYS);
        insert.setString(1,person.getPhone());
        insert.setInt(2,person.getId());
        int rowsAffected = insert.executeUpdate();
        return rowsAffected > 0;
    }

    private boolean EditStudent(Person person) throws SQLException{
        String Query2 = "INSERT INTO Student(StudentID, UniversityID) VALUES (?, ?)";
        PreparedStatement    insert = connection.prepareStatement(Query2);
        int universityId = ((Student) person).getUniversityId();
        insert.setInt(1, person.getId());
        insert.setInt(2, universityId);
        int rowsAffected = insert.executeUpdate();
        return rowsAffected > 0;
    }
    private boolean EditProessorr(Person person) throws SQLException{
        String Query3 = "INSERT INTO Professor(ProfID, Salary, CourseID) VALUES (?, ?, ?)";
        PreparedStatement insert = connection.prepareStatement(Query3);
        int courseId = ((Professor) person).getCourse().getCourseID();
        insert.setInt(1, person.getId());
        insert.setInt(2, ((Professor) person).getSalary());
        insert.setInt(3, courseId);
        int rowsAffected = insert.executeUpdate();
        return rowsAffected > 0;
    }
    private boolean LoginUpdate(Person person) throws  SQLException {
        String query  = "INSERT INTO Login (Email,Password, PersonID) values (?,?,?)";
        PreparedStatement insert = connection.prepareStatement(query);
        insert.setString(1,person.getEmail());
        insert.setString(2,this.Hash(person.getPassword()));
        insert.setInt(3,person.getId());
        int rowsAffected = insert.executeUpdate();
        return rowsAffected > 0;

    }
    private Person SyncPerson(ArrayList<String> arr) throws ParseException {
        SimpleDateFormat sqlDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date x = sqlDateFormat.parse(arr.get(3));
        Person person = new Person();
        person.setId(Integer.parseInt(arr.get(0)));
        person.setFirstName(arr.get(1));
        person.setLastName(arr.get(2));
        person.setBirthDate(x);
        person.setAddress(arr.get(4));
        person.setEmail(arr.get(5));
        person.setPassword(arr.get(6));
        person.setGender(arr.get(7));
        person.setPhone(arr.get(8));
        person.setAge(Integer.parseInt(arr.get(9)));
        person.setRole(arr.get(10));

        return person;
    }
    public static DatabaseOperations getInstance() throws SQLException {
        if(instace == null){
            instace = new DatabaseOperations();
        }
        return instace;
    }

    public boolean Authenticate(String Email , String Password) throws SQLException {
        String Query = "select * from Login where Email = ?";
        PreparedStatement login = connection.prepareStatement(Query);
        login.setString(1,Email);
        ResultSet rs = login.executeQuery();
        if(rs.next()){
            String pass = rs.getString("Password");
            return BCrypt.checkpw(Password,pass);
        }
        return false;
    }

    public boolean Authorize(Person person) throws SQLException {
        return (this.PersonEdit(person) && this.EditPhone(person) && LoginUpdate(person)) && (("Student".equals(person.getRole()) && this.EditStudent(person))
                || ("Professor".equals(person.getRole()) && this.EditProessorr(person)));
    }

    public Person GetPerson(String Email,String Password) throws SQLException, ParseException {
        ArrayList<String> result = new ArrayList<>();
        String Query = "SELECT \n" +
                "    p.PersonID, \n" +
                "    p.FirstName, \n" +
                "    p.LastName, \n" +
                "    p.Birthdate,\n" +
                "    p.Address, \n" +
                "    L.Email,\n" +
                "    L.Password,\n" +
                "    p.Gender, \n" +
                "    Pho.Phone,\n" +
                "    DATEDIFF(MONTH,Birthdate,GETDATE())/12 age,\n" +
                "    CASE \n" +
                "        WHEN s.StudentID IS NOT NULL THEN 'Student'\n" +
                "        WHEN pr.ProfID IS NOT NULL THEN 'Professor'\n" +
                "    END AS Role\n" +
                "FROM \n" +
                "    Person p\n" +
                "LEFT JOIN \n" +
                "    Student s ON p.PersonID = s.StudentID\n" +
                "LEFT JOIN \n" +
                "    Professor pr ON p.PersonID = pr.ProfID\n" +
                "LEFT JOIN\n" +
                "    Login L on p.PersonID = L.PersonID\n" +
                "LEFT JOIN\n" +
                "    Phone Pho on p.PersonID = pho.PersonID\n" +
                "where p.PersonID in (\n" +
                "    select PersonID from Login where Email = ?\n" +
                ")";
        PreparedStatement  stm = connection.prepareStatement(Query);
        stm.setString(1,Email);
        ResultSet rs = stm.executeQuery();
        if(rs.next()){
            ResultSetMetaData meta =  rs.getMetaData();
            int n = meta.getColumnCount();
            for(int x = 1 ; x <=n;x++){
                result.add(rs.getString(x));
            }
        }
        result.add(Password);
        return this.SyncPerson(result);
    }

    public ArrayList<String> GetStudentData(Person p) throws SQLException {
        ArrayList<String> list = new ArrayList<>();
        String Query = "select p1.UniversityID, AVG(p2.GPA_Value) as GPA from Student p1 \n" +
                "    left join Enrollment p2 on p1.StudentID = p2.StudentID \n" +
                "    where p1.StudentID = ?\n" +
                "GROUP By p1.StudentID,p1.UniversityID;";
        PreparedStatement stm = connection.prepareStatement(Query);
        stm.setInt(1, p.getId());
        ResultSet rs = stm.executeQuery();
        if(rs.next()) {
            list.add(String.valueOf(rs.getInt("UniversityID")));
            list.add(String.valueOf(rs.getInt("GPA")));
        }
        return list;
    }

    public ArrayList<Integer> GetProfData(Person p) throws SQLException{
        String Query = "select Salary , CourseID from Professor where ProfID = ?;";
        PreparedStatement stm = connection.prepareStatement(Query);
        stm.setInt(1,p.getId());
        ResultSet rs = stm.executeQuery();
        ArrayList<Integer> list = new ArrayList<>();
        if(rs.next()){
            list.add(rs.getInt("Salary"));
            list.add(rs.getInt("CourseID"));
        }
        return list;
    }
    public boolean UpdatePhone(String add, int ID) throws SQLException {
        String Query="UPDATE Phone set Phone = ? where PersonID = ?";
        PreparedStatement stm = connection.prepareStatement(Query);
        stm.setString(1,add);
        stm.setInt(2,ID);
        int rows = stm.executeUpdate();
        return rows> 0;
    }
    public boolean UpdateStudent(Student p) throws SQLException {
        String updatePersonQuery = "UPDATE Person SET FirstName = ?, LastName = ?, Address=?, Gender = ?, Birthdate = ? WHERE PersonID = ?";
        String updateStudentQuery = "UPDATE Student SET UniversityID = ? WHERE StudentID = ?";
        Connection tmpConn = this.connection;
        PreparedStatement stm1 = null;
        PreparedStatement stm2 = null;
        try {
            tmpConn.setAutoCommit(false);
            stm1 = tmpConn.prepareStatement(updatePersonQuery);
            stm2 = tmpConn.prepareStatement(updateStudentQuery);
            stm1.setString(1,p.getFirstName());
            stm1.setString(2,p.getLastName());
            stm1.setString(3,p.getAddress());
            stm1.setString(4,Objects.equals(p.getGender(), "F") ?"Female":"Male");
            stm1.setDate(5,new java.sql.Date(p.getBirthDate().getTime()));
            stm1.setInt(6,p.getId());

            stm2.setInt(1,p.getUniversityId());
            stm2.setInt(2,p.getId());
            stm1.executeUpdate();
            stm2.executeUpdate();
            tmpConn.commit();
            return true;
        } catch (SQLException e) {
            if(tmpConn != null){
                tmpConn.rollback();
            }
            return false;
        }finally {
            if (stm1 != null) stm1.close();
            if (stm2 != null) stm2.close();
        }
    }
    public boolean UpdateProffessor(Professor p) throws SQLException {
        String updatePersonQuery = "UPDATE Person SET FirstName = ?, LastName = ?, Gender = ?, Birthdate = ? WHERE PersonID = ?";
        String updateStudentQuery = "UPDATE Professor SET Salary = ?, CourseID=? WHERE ProfID = ?";

        PreparedStatement stm1 = null;
        PreparedStatement stm2 = null;
        try {
            connection.setAutoCommit(false);
            stm1 = connection.prepareStatement(updatePersonQuery);
            stm2 = connection.prepareStatement(updateStudentQuery);
            stm1.setString(1,p.getFirstName());
            stm1.setString(2,p.getLastName());
            stm1.setString(3,(Objects.equals(p.getGender(), "F") ?"Female":"Male"));
            stm1.setDate(4,new java.sql.Date(p.getBirthDate().getTime()));
            stm1.setInt(5,p.getId());

            stm2.setInt(1,p.getSalary());
            stm2.setInt(2,p.getCourse().getCourseID());
            stm1.setInt(3,p.getId());
            stm1.executeUpdate();
            stm2.executeUpdate();
            connection.commit();
            return true;
        } catch (SQLException e) {
            if(connection != null){
                connection.rollback();
            }
            return false;
        }finally {
            if (stm1 != null) stm1.close();
            if (stm2 != null) stm2.close();
        }
    }

    public boolean RegisterSubject_(Person s , ArrayList<Courses> arr) throws SQLException {
        String Query = "Insert into Enrollment(GPA_Value,GPA_Percentage,EnrollDate,CourseID,StudentID) values (?,?,?,?,?) ";
        PreparedStatement p = connection.prepareStatement(Query);
        int re = 0;
        for(Courses x : arr){
            p.setBigDecimal(1,new java.math.BigDecimal("3.00"));
            p.setBigDecimal(2,new java.math.BigDecimal("85.00"));
            p.setDate(3,new java.sql.Date(System.currentTimeMillis()));
            p.setInt(4,x.getCourseID());
            p.setInt(5,s.getId());
            re+= p.executeUpdate();
        }
        return re>0;
    }
}
