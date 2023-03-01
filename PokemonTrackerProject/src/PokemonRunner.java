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

	public static void main(String[] args) {
		db = new PokemonDb();
		sc = new Scanner(System.in);
		
		// login process

		
		// 
		boolean running = true;
		while (running) {
			String command = sc.nextLine();
			
			switch(command.toLowerCase()) {
				case "help":
					System.out.println("login, exit, collection, search, catch, level up");
					break;
				case "login": 
					login();
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
				}
				
			} catch (Exception e) { // TODO: custom exception for invalid login
				System.out.println("Invalid login");
				e.printStackTrace();
			}
			
		}
	}
}
