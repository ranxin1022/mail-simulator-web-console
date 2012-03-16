package Test;

public class User {

	public String name;
	public String password;
	
	public User(){}
	
	public User(String name){
		this.name  = name;
		this.password = "123456";
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
}
