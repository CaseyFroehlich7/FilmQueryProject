package com.skilldistillery.filmquery.app;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import com.skilldistillery.filmquery.database.DatabaseAccessor;
import com.skilldistillery.filmquery.database.DatabaseAccessorObject;
import com.skilldistillery.filmquery.entities.Film;

public class FilmQueryApp {
	DatabaseAccessor db = new DatabaseAccessorObject();
	public static void main(String[] args) throws SQLException, ClassNotFoundException  {
		FilmQueryApp app = new FilmQueryApp();
//		app.test();
		app.launch();
	}

	private void launch() throws SQLException {
		Scanner sc = new Scanner(System.in);
		startUserInterface(sc);
//		sc.close();
		
	}

	private void startUserInterface(Scanner sc) throws SQLException{
		
		printMenu();
		boolean userMenu = true;
		while (userMenu) {

			System.out.println("Please select an option.");
			int userInput = sc.nextInt();
			sc.nextLine();
			
			switch (userInput) {
			case 1:

				System.out.println("Please enter film ID to search for film.");
				int userId = sc.nextInt();
				sc.nextLine();
				Film filmById = db.findFilmById(userId);
				if (filmById != null) {
					
					System.out.println(filmById);
				} else {
					System.out.println("Errror: film not found. Please try again.");
				}

				break;

			case 2:
				
				System.out.println("Please enter a keyword to search for film.");
				try {
					String key = sc.nextLine();
					System.out.println("Key: " + key);
					ArrayList<Film> films = db.findFilmByKeyword(key);
					System.out.println("Films: " + films);
					
					if (films.size() > 0 ) {
						for (Film film : films) {
							System.out.println(film);
						}
					} else {
						System.out.println("Error: film not found."); 
						
					}

				} catch (Exception E) {
					System.out.println("Error");
					sc.next();
					startUserInterface(sc);
				}

				break;

			case 3:

				System.out.println("Exiting Program");
				userMenu = false;
				break;

			default:
				System.out.println("Error: Invalid Selection : Please try again.");

			}

		}

	}
	public void printMenu() {
		System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
		System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
		System.out.println("%%             FILM QUERY MENU                 %%");
		System.out.println("%%           1. Search for film by its ID      %%");
		System.out.println("%%           2. Search for film by keyword     %%");
		System.out.println("%%           3. Exit program                   %%");
		System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
		System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
		
	}
	
}
