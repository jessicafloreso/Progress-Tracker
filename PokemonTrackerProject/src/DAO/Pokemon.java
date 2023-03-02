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
		return String.format("%-3s %-10s", id, name);
	}
}
