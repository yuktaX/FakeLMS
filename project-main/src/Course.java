public class Course {
    String Course_ID;
    String CourseName;
    String Type;
    String Professor_ID;
    String Branch;
    int SemOffered;
    public Course(String Course_ID, String CourseName, String Type, String Professor_ID, String Branch, int SemOffered)
    {
        this.Course_ID = Course_ID;
        this.CourseName = CourseName;
        this.Type = Type;
        this.Professor_ID = Professor_ID;
        this.Branch = Branch;
        this.SemOffered = SemOffered;
    }
    public String getCourseID()
    {
        return Course_ID;
    }
    public void setCourseID(String id)
    {
        this.Course_ID = id;
    }
    public String getCourseName()
    {
        return CourseName;
    }
    public void setCourseName(String name)
    {
        this.CourseName = name;
    }
    public String getType()
    {
        return Type;
    }
    public void setType(String type)
    {
        this.Type = type;
    }
    public String getProfessorID()
    {
        return Professor_ID;
    }
    public void setProfessor_ID(String id)
    {
        this.Professor_ID = id;
    }
    public String getBranch()
    {
        return Course_ID;
    }
    public void setBranch(String branch)
    {
        this.Branch = branch;
    }
    public int getSemOffered()
    {
        return SemOffered;
    }
    public void setSemOffered(int id)
    {
        this.SemOffered = id;
    }

}
