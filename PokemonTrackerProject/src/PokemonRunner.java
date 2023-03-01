import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.EOFException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import DAO.Collected;
import DAO.PokemonDb;
import customExceptions.MaxLevelException;

public class PokemonRunner {
	private static PokemonDb db;
	private static Scanner sc;
	private static String user;
	private static String cookiePath;

	public static void main(String[] args) {
		db = new PokemonDb();
		sc = new Scanner(System.in);
		cookiePath = "resources/cookies.txt";
		
		// read cookies
		try(BufferedReader reader = new BufferedReader(new FileReader(new File(cookiePath)))) {
			String savedUser = reader.readLine();
			if (savedUser != null) {
				user = savedUser;
				System.out.println("Welcome back " + savedUser);
			} else {
				System.out.println("login wiht 'login' to continue!");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		

		
		// 
		boolean running = true;
		while (running) {
			String command = sc.nextLine();
			
			switch(command.toLowerCase()) {
				case "help":
					System.out.println("login, logout, exit, collection, search, catch, level");
					break;
				case "login": 
					login();
					break;
				case "logout":
					logout();
					break;
				case "exit":
					running = false;
					break;
				case "collection":
					collection();
					break;
				case "search":
					search();
					break;
				case "catch":
					catcher();
					break;
				case "level":
					level();
					break;
				default:
					System.out.println("Not a command");
					break;
			}
			
		}
		
		sc.close();
		System.out.println("Exiting, thanks for playing");
		
	}
	public static void logout() {
		user = null;
		System.out.println("logged out");
		
		//update cookies
		try(BufferedWriter writer = new BufferedWriter( new FileWriter(new File(cookiePath), false))) {
			writer.write("");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void catcher() {
		System.out.println("Enter pokemon name");
		String name = sc.nextLine();
		
		System.out.println("Enter pokemon level");
		int level = sc.nextInt();
		
		try {
			boolean success = db.getCollection().catchPokemon(user, name, level);
			if (success == true) {
				System.out.println("pokemon leveled");
			}
		} catch (MaxLevelException e) {
			System.out.println("level out of bounds");
		}
		
	}
	
	public static void level() {
		System.out.println("Enter pokemon name");
		String name = sc.nextLine();
		
		System.out.println("Enter new pokemon level");
		int level = sc.nextInt();
		
		try {
			boolean success = db.getCollection().levelUp(user, name, level);
			if (success == true) {
				System.out.println("pokemon leveled");
			}
		} catch (MaxLevelException e) {
			System.out.println("level out of bounds");
		}
	}
	
	public static void collection() {
		System.out.println("Getting collection:");
		List<Collected> returnedCollection = db.getCollection().getAllCollected(user);
		for (Collected p : returnedCollection) {
			System.out.println(p);
		}
	}
	
	public static void search() {
		System.out.println("Enter Pokemon Name:");
		String name = sc.nextLine();
		Optional<Collected> result = db.getCollection().getPokemon(user, name);
		if (result.isPresent()) {
			System.out.println(result.get());
		} else {
			System.out.println("Pokemon not found");
		}
		
	}
	
	public static void login() {
		while(user == null) {
			try {
				System.out.println("Enter Username:");
				String username = sc.nextLine();
				
				System.out.println("Enter Password:");
				String password = sc.nextLine();
				
				boolean success = db.login(username, password);
				if (success == false) {
					System.out.println("press q to quit or c to continue:");
					String ans = sc.nextLine();
					if (ans.toLowerCase().equals("q")) {
						System.out.println("Exiting");
						break;
					}
				} else {
					user = username;
					System.out.println("logged in as " + user);
					
					//update cookies
					try(BufferedWriter writer = new BufferedWriter( new FileWriter(new File(cookiePath), false))) {
						writer.write(user);
					} catch (Exception e) {
						e.printStackTrace();
					}
					
					
				}
				
			} catch (Exception e) { // TODO: custom exception for invalid login
				System.out.println("Invalid login");
				e.printStackTrace();
			}
			
		}
	}
}
