public class Professor {
	String Name;
	Integer Professor_ID;
	String Email;

	public Professor() {
	}

	public Professor(String name, Integer id, String email) {
		this.Name = name;
		this.Professor_ID = id;
		this.Email = email;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		this.Name = name;
	}

	public Integer getProfessortID() {
		return Professor_ID;
	}

	public void setProfessor_ID(Integer id) {
		this.Professor_ID = id;
	}

	public String getEmail() {
		return Name;
	}

	public void setEmail(String email) {
		this.Email = email;
	}

}
