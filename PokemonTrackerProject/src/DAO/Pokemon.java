package DAO;

public class Pokemon {
	private int id;
	private String name;
	public Pokemon(int id, String name) {
		this.id = id;
		this.name = name;
	}
	@Override
	public String toString() {
		return "Pokemon [id=" + id + ", name=" + name + "]";
	}
	
	
	
	
}
