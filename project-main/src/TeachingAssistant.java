import java.sql.Date;

public class TeachingAssistant extends Student
{
    String Course_ID;
    public TeachingAssistant(int id, String name, String email, String branch, int CurrentSem, String Gender, Date DOB, String Course_ID)
    {
        super(id, name, email, branch, CurrentSem, Gender, DOB);
        this.Course_ID = Course_ID;
    }
    public String getCourseID()
    {
        return Course_ID;
    }
    public void setCourseID(String id)
    {
        this.Course_ID = id;
    }
    
}
