import java.lang.*;
import java.util.List;

public interface StudentDAO {
	public boolean getStudentByKey(String id); //to verify login
	public void EnrollForCourse(String Course_ID);
	public void UnenrollForCourse(String Course_ID);
	public void getTranscript();
	public void getEligibleCourses();
	public void viewMyCourses();
	//public void addStudent(Student student);
	//public void updateStudent(Student student);
	//public void deleteStudent(Student student);
	//
}

