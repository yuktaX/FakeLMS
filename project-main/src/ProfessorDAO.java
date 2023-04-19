public interface ProfessorDAO {
    public Professor getProfessorByKey(String id);

    public void addPerformance();

    public void addCourse(Professor p);

    public void getStudentsFromCourse();

}
