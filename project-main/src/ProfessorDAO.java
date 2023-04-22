public interface ProfessorDAO {
    public Professor loginProfessor(Integer id, String password);

    public void addPerformance(Professor p);

    public void addCourse(Professor p);

    public void getStudentsFromCourse();

    public void viewMyCourses(Professor p);
}
