package DAO;

public class Users {
	private int id;
	private String password;
	private String username;
	public Users(int id, String password, String username) {
		super();
		this.id = id;
		this.password = password;
		this.username = username;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	@Override
	public String toString() {
		return "Users [id=" + id + ", password=" + password + ", username=" + username + "]";
	}
	
	
}
