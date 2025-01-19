package Model;

import java.util.Date;

public class Professor extends Person{
    private int salary;
    private Courses course;
    public Professor(){
        super();
    }
    public Professor(Person p){
        super(p.getFirstName(),p.getLastName(),p.getBirthDate(),p.getAddress(),p.getEmail(),p.getPassword(),p.getGender(),p.getPhone(),"Professor");
    }
    public Professor(String firstName, String lastName, Date birthDate, String address, String email, String password, String gender, String Phone,String role) {
        super(firstName, lastName, birthDate, address, email, password, gender, Phone,role);
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }
    public void AssignCourse(Courses c){
        this.course =c;
    }
    public Courses getCourse(){
        return this.course;
    }
}
