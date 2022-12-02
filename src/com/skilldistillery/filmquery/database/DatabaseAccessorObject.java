package com.skilldistillery.filmquery.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.skilldistillery.filmquery.entities.Actor;
import com.skilldistillery.filmquery.entities.Film;

public class DatabaseAccessorObject implements DatabaseAccessor {
	private static final String URL = "jdbc:mysql://localhost:3306/sdvid?useSSL=false&useLegacyDatetimeCode=false&serverTimezone=US/Mountain";

	private String user;
	private String password;
	ResultSet rs;
	Connection conn;
	String sqltxt;
	Scanner input;

	
	@Override
	public Film findFilmById(int filmId) {
	Film film = null;
	return film;
	}
	
	
	
	@Override
	public Actor findActorById(int actorId) {
		Actor actor = null;
		return actor;
	}
		
	
	
	@Override
	public List<Actor> findActorsByFilmId(int filmId) {
		List<Actor> actors = new ArrayList<>();
		user = "student";
		password = "student";
		String sql = "SELECT id, first_name, last_name FROM actor WHERE id = ?";

		// ? bind variable = actor id

		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, actorId);

		// debug purposes
		System.out.println(stmt);

		// loop not necessary
		ResultSet actorResult = stmt.executeQuery();

		if (actorResult.next()) {
			actor = new Actor(); // Create the object

			// Here is our mapping of query columns to our object fields:
			actor.setId(actorResult.getInt("id"));

			actor.setFirstName(actorResult.getString("first_name"));

			actor.setLastName(actorResult.getString("last_name"));

		//	actor.setFilms(findFilmsByActorId(actorId)); // An Actor has Films

		}
		// ... if statement conn.close();
		return actor;
	

	}
	
	
	
	
	

		


	
	
	
	
	
	
//	
//	public List<Film> findFilmsByActorId(int actorId) {
//		List<Film> films = new ArrayList<>();
//		
//		try {
//			Connection conn = DriverManager.getConnection(url, user, pass);
//			
//			String sql = "SELECT * FROM film JOIN film_actor ON film.id = film_actor.film_id WHERE actor_id = ?";
//			
//			PreparedStatement stmt = conn.prepareStatement(sql);
//			stmt.setInt(1, actorId);
//			
//			ResultSet rs = stmt.executeQuery();
//			
//			
//			while (rs.next()) {
//				int filmId = rs.getInt("id");
//				String title = rs.getString("title");
//				String desc = rs.getString("description");
//				short releaseYear = rs.getShort("realease_year");
//				int langId = rs.getInt("language_id");
//				int rentDur = rs.getInt("rental_duration");
//				double rate = rs.getDouble("rental_rate");
//				int length = rs.getInt("length");
//				double repCost = rs.getDouble("replacement_cost");
//				String rating = rs.getString("rating");
//				String features = rs.getString("special_features");
//				Film film = new Film(filmId, title, desc, releaseYear, langId, rentDur, rate, length, repCost, rating,
//						features);
//				
//				films.add(film);
//			}
//			rs.close();
//			stmt.close();
//			conn.close();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		return films;
//	}

	
}
