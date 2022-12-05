package com.skilldistillery.filmquery.app;

import java.sql.SQLException;
import java.util.List;
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
		sc.close();
		
	}

	private void startUserInterface(Scanner sc) throws SQLException{
		
		printMenu();
		boolean userMenu = true;
		while (userMenu) {

			System.out.println("Please select an option.");
			int userInput = sc.nextInt();

			switch (userInput) {
			case 1:

				System.out.println("Please enter film ID to search for film.");
				int userId = sc.nextInt();
				Film filmById = db.findFilmById(userId);
				if (filmById != null) {
					
					System.out.println(filmById.toString());
				} else {
					System.out.println("Errror: film not found. Please try again.");
				}

				break;

			case 2:

				System.out.println("Please enter a keyword to search for film.");
				String userKeySearch = sc.nextLine();

				List<Film> films = db.findFilmByKeyword(userKeySearch);

				if (films.size() == 0) {
					System.out.println("Error: 0 Films Found.");
				} else {
					System.out.println(films.size());
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
