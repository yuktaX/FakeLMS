import java.util.ArrayList;

public interface ProfessorDAO 
{
    public void addPerformance();
    public void addCourse();
    public ArrayList<Student> getStudentsFromCourse();
    
}
