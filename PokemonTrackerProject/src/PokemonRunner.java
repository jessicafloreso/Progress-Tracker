import java.util.Scanner;

import DAO.PokemonDb;

public class PokemonRunner {

	public static void main(String[] args) {
		PokemonDb db = new PokemonDb();
		
		while(db.getUser() == null) {
			Scanner sc = new Scanner(System.in);
			try {
				System.out.println("Enter Username:");
				String username = sc.nextLine();
				
				System.out.println("Enter Password:");
				String password = sc.nextLine();
				//sc.close();
				
				db.login(username, password);
				
			} catch (Exception e) { // TODO: custom exception for invalid login
				System.out.println("Invalid login");
				e.printStackTrace();
			}
			sc.close();
		}
		System.out.println("logged in as " + db.getUser() );
	}
}
