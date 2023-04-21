public interface ProfessorDAO {
    public Professor loginProfessor(Integer id, String password);

    public void addPerformance();

    public void addCourse(Professor p);

    public void getStudentsFromCourse();

}
