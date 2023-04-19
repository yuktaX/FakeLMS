public interface StudentDAO {
	public Student getStudentByKey(String id); //to verify login
	public void EnrollForCourse(Student s);
	public void UnenrollForCourse(Student s);
	public void getTranscript(Student s);
	public void getEligibleCourses(Student s);
	public void viewMyCourses(Student s);
	public void viewCoursesbyProf(Student s);
	//public void viewGrades(Student s);
	//public void addStudent(Student student);
	//public void updateStudent(Student student);
	//public void deleteStudent(Student student);
	//
}

