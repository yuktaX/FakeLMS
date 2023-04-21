public interface StudentDAO {
	public Student loginStudent(Integer id, String password); // to verify login

	public void EnrollForCourse(Student s);

	public void UnenrollForCourse(Student s);

	public void getTranscript(Student s);

	public void getEligibleCourses(Student s);

	public void viewMyCourses(Student s);

	public void viewCoursesbyProf(Student s);

	public void ContactTA(Student s);
}
