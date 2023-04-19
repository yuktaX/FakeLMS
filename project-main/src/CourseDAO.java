import java.lang.*;
import java.util.List;
public interface CourseDAO {
    public Course getCourseByKey(int Course_ID);
    public void addCourse();
	public void deleteCourse();
}
