import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import DAO.Collected;
import DAO.Pokemon;
import DAO.PokemonDb;
import customExceptions.MaxLevelException;
import customExceptions.PokemonNotFoundException;
import customExceptions.InvalidLoginException;

public class PokemonRunner {
	private static PokemonDb db;
	private static Scanner sc;
	private static String user;
	private static String cookiePath;

	public static void main(String[] args) {
		
		icon();
		
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
				System.out.println("Login with 'login' to continue!");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		

		
		// 
		help();
		boolean running = true;
		while (running) {
			if (user != null) {
				System.out.print(user + "> ");
			} else {
				System.out.print("LOGGED OUT> ");
			}
			String command = sc.nextLine(); 
			switch(command.toLowerCase()) {
				case "help":
					help();
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
				case "list all pokemon":
					getAllPokemon();
					break;
				case "collection":
					collection();
					break;
				case "search":
					try {
						search();
					} catch (PokemonNotFoundException e) {
						System.out.println( e.getMessage());
					}
					break;
				case "catch":
					catcher();
					break;
				case "level":
					level();
					break;
				case "uncaught":
					uncaught();
					break;
				case "add user":
					addUser();
					break;
				default:
					System.out.println("Please type a valid input/command. For help, type help");
					break;
			}
			
		}
		
		sc.close();
		System.out.println("Exiting, thanks for playing");
		
	}
	
	public static void help() {
		System.out.println("commands: help, login, logout, exit, collection, search, catch, level, add user, list all pokemon, uncaught");

	}
	
	public static void addUser() {
		try {
			System.out.println("Enter Username:");
			String username = sc.nextLine();
			
			System.out.println("Enter Password:");
			String password = sc.nextLine();
			
			boolean success = db.getUsers().addUser(username, password);
			if (success == false) {
				System.out.println("Can't create user");
			} else {
				user = username;
				System.out.println("Logged in as " + user);
				
				//update cookies
				try(BufferedWriter writer = new BufferedWriter( new FileWriter(new File(cookiePath), false))) {
					writer.write(user);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		} catch (Exception e) { // TODO: custom exception for invalid login
			e.printStackTrace();
		}
		
		
	}
	
	public static void logout() {
		user = null;
		System.out.println("Logged out");
		
		//update cookies
		try(BufferedWriter writer = new BufferedWriter( new FileWriter(new File(cookiePath), false))) {
			writer.write("");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void catcher() {
		String name = "";
		int level = 0;
		try {
			System.out.println("Enter pokemon name");
			name = sc.nextLine();
			
			System.out.println("Enter pokemon level");
			level = Integer.parseInt(sc.nextLine()); 
		} catch (Exception e){
			System.out.println("invalid input");
		}
		
		try {
			boolean success = db.getCollection().catchPokemon(user, name, level);
			if (success == true) {
				System.out.println("Pokemon added");
			} else {
				System.out.println("Not able to catch");
			}
		} catch (MaxLevelException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public static void level() {
		String name = "";
		int level = 0;
		try {
			System.out.println("Enter pokemon name");
			name = sc.nextLine();
			
			System.out.println("Enter new pokemon level");
			level = Integer.parseInt(sc.nextLine());
		} catch (Exception e){
			System.out.println("invalid input");
		}
		
		try {
			boolean success = db.getCollection().levelUp(user, name, level);
			if (success == true) {
				System.out.println("Pokemon leveled");
			}
		} catch (MaxLevelException e) {
			System.out.println(e.getMessage()); //exception calls for message in the levelup method
		}
	}
	
	public static void collection() {
		System.out.println("Getting collection:");
		List<Collected> returnedCollection = db.getCollection().getAllCollected(user);
		for (Collected p : returnedCollection) {
			System.out.println(p);
		}
	}
	
	public static void getAllPokemon() {
		System.out.println("Getting collection:");
		List<Pokemon> returnedCollection = db.getAllPokemon();
		for (Pokemon p : returnedCollection) {
			System.out.println(p);
		}
	}
	public static void uncaught() {
		System.out.println("Getting collection:");
		List<Pokemon> returnedCollection = db.gottaCatchem(user);
		for (Pokemon p : returnedCollection) {
			System.out.println(p);
		}
	}
	
	public static void search() throws PokemonNotFoundException{
		System.out.println("Enter Pokemon Name:");
		String name = sc.nextLine();
		Optional<Collected> result = db.getCollection().getPokemon(user, name);
		if (result.isPresent()) {
			System.out.println(result.get());
		}
	}
	
	public static void login() {
		boolean loop = true;
		while(loop) {
			try {
				System.out.println("Enter Username:");
				String username = sc.nextLine();
				
				System.out.println("Enter Password:");
				String password = sc.nextLine();
				
				db.getUsers().login(username, password);
				// if exception not thrown, then success
				user = username;
				System.out.println("Logged in as " + user);
				
				//update cookies
				try(BufferedWriter writer = new BufferedWriter( new FileWriter(new File(cookiePath), false))) {
					writer.write(user);
				} catch (Exception e) {
					e.printStackTrace();
				}
				loop = false;
        
			} catch (InvalidLoginException e) { // TODO: custom exception for invalid login
				System.out.println( e.getMessage());
				System.out.println("Press q to quit or any other key to try again:");
				
				String ans = sc.nextLine();
				if (ans.toLowerCase().equals("q")) {
					System.out.println("Exiting");
					loop = false; 
				}
			}
		}
	}
	
	public static void icon() {
		System.out.println( "                                  ,'\\" );
		System.out.println( "    _.----.        ____         ,'  _\\   ___    ___     ____" );
		System.out.println( "_,-'       `.     |    |  /`.   \\,-'    |   \\  /   |   |    \\  |`." );
		System.out.println( "\\      __    \\    '-.  | /   `.  ___    |    \\/    |   '-.   \\ |  |" );
		System.out.println( " \\.    \\ \\   |  __  |  |/    ,','_  `.  |          | __  |    \\|  |" );
		System.out.println( "   \\    \\/   /,' _`.|      ,' / / / /   |          ,' _`.|     |  |");
		System.out.println("    \\     ,-'/  /   \\    ,'   | \\/ / ,`.|         /  /   \\  |     |");
		System.out.println("     \\    \\ |   \\_/  |   `-.  \\    `'  /|  |    ||   \\_/  | |\\    |");
		System.out.println("      \\    \\ \\      /       `-.`.___,-' |  |\\  /| \\      /  | |   |");
		System.out.println("       \\    \\ `.__,'|  |`-._    `|      |__| \\/ |  `.__,'|  | |   |");
		System.out.println("        \\_.-'       |__|    `-._ |              '-.|     '-.| |   |");
		System.out.println("                                `'                            '-._|");





	}
}
