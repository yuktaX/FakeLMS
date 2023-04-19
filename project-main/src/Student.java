import java.sql.Date;

public class Student{
	int Student_ID;
	String Name;
	String Email;
	String Branch;
	int CurrentSem;
	String Gender;
	Date DOB;
	public Student() { }
	public Student(int id, String name, String email, String branch, int CurrentSem, String Gender, Date DOB)
	{
		this.Student_ID = id;
		this.Name = name;
		this.Email = email;
		this.Branch = branch;
		this.CurrentSem = CurrentSem;
		this.Gender = Gender;
		this.DOB = DOB;
	}	
	public String getName() 
	{ 
		return Name; 
	}
	public void setName(String name)
	{ 
		this.Name = name; 
	}
	public int getStudentID() 
	{ 
		return Student_ID; 
	}
	public void setStudentID(int id)
	{ 
		this.Student_ID = id; 
	}
	public String getEmail() 
	{ 
		return Name; 
	}
	public void setEmail(String email)
	{ 
		this.Email = email; 
	}
	public String getBranch() 
	{ 
		return Branch; 
	}
	public void setBranch(String branch)
	{ 
		this.Branch = branch; 
	}
	public int getSem() 
	{ 
		return CurrentSem; 
	}
	public void setCurrentSem(int sem)
	{ 
		this.CurrentSem = sem; 
	}
	public String getGender() 
	{ 
		return Name; 
	}
	public void setGender(String gender)
	{ 
		this.Gender = gender; 
	}
	public Date getDOB()
	{
		return DOB;
	}
	public void setDOB(Date dob)
	{
		this.DOB = dob;
	}
};

