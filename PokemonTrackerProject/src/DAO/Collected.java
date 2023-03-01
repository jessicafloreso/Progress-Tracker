package DAO;

import java.util.Arrays;

public class Collected {
	private String userName;
	private String pokemonName;
	private int level;
	private boolean completed;
	
	public Collected(String userName, String pokemonName, int level, boolean completed) {
		super();
		this.userName = userName;
		this.pokemonName = pokemonName;
		this.level = level;
		this.completed = completed;
	}
	
	public Collected(String userName, String pokemonName, int level) {
		super();
		this.userName = userName;
		this.pokemonName = pokemonName;
		this.level = level;
		this.completed = false;
	}
	
	public Collected(String userName, String pokemonName) {
		super();
		this.userName = userName;
		this.pokemonName = pokemonName;
		this.level = 0;
		this.completed = false;
	}



	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPokemonName() {
		return pokemonName;
	}
	public void setPokemonName(String pokemonName) {
		this.pokemonName = pokemonName;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public boolean isCompleted() {
		return completed;
	}
	public void setCompleted(boolean completed) {
		this.completed = completed;
	}

	@Override
	public String toString() {
		return "Collected [userName=" + userName + ", pokemonName=" + pokemonName + ", level=" + level + ", completed="
				+ completed + "]";
	}
	
	
	
	
	
	
	
	
}
