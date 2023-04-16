public class Professor 
{
    String Name;
    String Professor_ID;
    String Email;
    public Professor(String name, String id, String email)
    {
        this.Name = name;
        this.Professor_ID = id;
        this.Email = email;
    }
    public String getName()
    { 
		return Name; 
	}
	public void setName(String name)
	{ 
		this.Name = name; 
	}
	public String getProfessortID() 
	{ 
		return Professor_ID; 
	}
	public void setProfessor_ID(String id)
	{ 
		this.Professor_ID = id; 
	}
	public String getEmail() 
	{ 
		return Name; 
	}
	public void setEmail(String email)
	{ 
		this.Email = email; 
	}

}


