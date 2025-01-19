package Model;

public class Courses {
    private int CourseID;
    private String CourseName;
    private int h;

    public Courses(int courseID, String courseName,int h) {
        CourseID = courseID;
        CourseName = courseName;
        this.h = h;
    }

    public int getCourseID() {
        return CourseID;
    }

    public void setCourseID(int courseID) {
        CourseID = courseID;
    }

    public String getCourseName() {
        return CourseName;
    }

    public void setCourseName(String courseName) {
        CourseName = courseName;
    }

    public int getH() {
        return h;
    }

    public void setH(int h) {
        this.h = h;
    }
}
