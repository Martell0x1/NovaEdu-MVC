package Model;

import java.util.Date;

public class Student extends Person{
    private int GPA;
    private int UniversityId;

    public Student(){}
    public Student(String firstName, String lastName, Date birthDate, String address, String email, String password, String gender,String Phone ,String role) {
        super(firstName, lastName, birthDate, address, email, password, gender, Phone,role);

    }
    public Student(Person p){
        super(p.getFirstName(),p.getLastName(),p.getBirthDate(),p.getAddress(),p.getEmail(),p.getPassword(),p.getGender(),p.getPhone(),"Student");
        this.setId(p.getId());
    }

    public int getGPA() {
        return GPA;
    }

    public void setGPA(int GPA) {
        this.GPA = GPA;
    }

    public int getUniversityId() {
        return UniversityId;
    }

    public void setUniversityId(int universityId) {
        UniversityId = universityId;
    }
}
