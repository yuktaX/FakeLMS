public class Course {
    int Course_ID;
    String CourseName;
    int SemOfferedIn;
    int Credits;
    String Type;
    int Professor_ID;
    String Branch;

    public Course(){};
    //getters
    public int getCourseID(){
        return this.Course_ID;
    }

    public String getCourseName(){
        return this.CourseName;
    }

    public int getCourseCredits(){
        return this.Credits;
    }

    public int getCourseProfessor(){
        return this.Professor_ID;
    }

    public String getType(){
        return this.Type;
    }

    public String getBranch(){
        return this.Branch;
    }

    public int getSemOfferedIn(){
        return this.SemOfferedIn;
    }

    //setters
    public void setCourseID(int Course_ID){
        this.Course_ID=Course_ID;
    }

    public void setCourseName(String CourseName){
        this.CourseName=CourseName;
    }

    public void setCourseCredit(int Credits){
        this.Credits=Credits;
    }

    public void setCourseProfessor(int Professor_ID){
        this.Professor_ID=Professor_ID;
    }

    public void setType(String Type){
        this.Type=Type;
    }

    public void setBranch(String Branch){
        this.Branch=Branch;
    }

    public void setSemOfferedIn(int SemOfferedIn){
        this.SemOfferedIn=SemOfferedIn;
    }
}
