package com.skilldistillery.filmquery.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.skilldistillery.filmquery.entities.Actor;
import com.skilldistillery.filmquery.entities.Film;

public class DatabaseAccessorObject implements DatabaseAccessor {
	
	private static final String URL = "jdbc:mysql://localhost:3306/sdvid?useSSL=false&useLegacyDatetimeCode=false&serverTimezone=US/Mountain";
	private static final String user = "student";
	private static final String password = "student";
	
	
	
	static {
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
				System.err.println("Error: Database Driver not found");
			
			}
	}

	@Override
	public Film findFilmById(int filmId) throws SQLException {
		Film film = null;
		String sql = "SELECT film * FROM film WHERE film.id = ?";

		Connection conn = DriverManager.getConnection(URL, user, password);
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, filmId);
		ResultSet rs = stmt.executeQuery();
			
			if(rs.next()) {
				film = new Film();
				
				film.setId(rs.getInt("1"));
				film.setTitle(rs.getString("title"));
				film.setDescription(rs.getString("description"));
				film.setReleaseYear(rs.getInt("release_year"));
				film.setLanguageId(rs.getInt("languageId"));
				film.setRentalDuration(rs.getInt("rental_duration"));
				film.setRentalRate(rs.getDouble("rental_rate"));
				film.setLength(rs.getInt("length"));
				film.setReplacementCost(rs.getDouble("replacement_cost"));
				film.setRating(rs.getString("rating"));
				film.setSpecialFeatures(rs.getString("special_features"));
				film.setLanguage(rs.getString("language.name"));
				film.setActors(findActorsByFilmId(filmId));
			}
			conn.close();
			rs.close();
			stmt.close();
			return film;
	} 
		
	
	@Override
	public Actor findActorById(int actorId) throws SQLException {
		Actor actor = null;
		String sql = "SELECT id, first_name, last_name FROM actor WHERE id = ?";
	
		
		Connection conn = DriverManager.getConnection(URL, user, password);
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, actorId);
		ResultSet rs = stmt.executeQuery();	
		
		if (rs.next()) {
			
			actor = new Actor();
			actor.setId(rs.getInt(1));
			actor.setFirstName(rs.getString("first_name"));
			actor.setLastName(rs.getString("last_name"));
			
		}
		return actor;

	}
	
	
	
	private List<Film> findFilmsByActorId(int actorId, Film film) {
		List<Film> films = new ArrayList<>();
		String sql = "SELECT *" + " FROM film JOIN film_actor ON film.id = film_actor.film_id" + " WHERE actor_id = ?";
		
		try {
			Connection conn = DriverManager.getConnection(URL, user, password);
			PreparedStatement stmt = conn.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) {
				int filmId = rs.getInt("id");
				rs.getString("title");
				rs.getString("description");
				rs.getShort("release_year");
				rs.getInt("language_id");
				rs.getInt("rental_duration");
				rs.getDouble("rental_rate");
				rs.getInt("length");
				rs.getDouble("replacement_cost");
				rs.getString("rating");
				rs.getString("special_features");
				findActorsByFilmId(filmId);
			
			films.add(film);
			}
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			
		}
		return films;
	
}
	@Override
	public List<Actor> findActorsByFilmId(int filmId) {
		
		List<Actor> actors = new ArrayList<>();
		Actor actor;
		
	
		try {
		Connection conn = DriverManager.getConnection(URL, user, password);
		String sql = "SELECT actor.id, actor,first_name, actor.last_name " + "FROM actor " + " JOIN film_actor " + "ON actor.id = film_actor.actor_id " + "WHERE film_actor.film_id = ?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, filmId);
		ResultSet rs = stmt.executeQuery();

		while (rs.next()) {
			
			actor = new Actor();

			// Here is our mapping of query columns to our object fields:
			actor.setId(rs.getInt("id"));

			actor.setFirstName(rs.getString("first_name"));

			actor.setLastName(rs.getString("last_name"));

			actors.add(actor);

		}
		
		if (actors.isEmpty()) {
			System.out.println("Error: No actor found.");
		}
		rs.close();
		conn.close();
		stmt.close();
	} catch (SQLException e) {
		e.printStackTrace();
	}

		return actors;
	

	}
	
	public List<Film> findFilmByKeyword(String userKeySearch) {
		List<Film> films = new ArrayList<>();
		Film film = null;
		String sql = "SELECT * FROM film WHERE film.title LIKE ? or film.description LIKE ?";
	
		try {
			
			Connection conn = DriverManager.getConnection(URL, user, password);
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, "%" + userKeySearch + "%");
			stmt.setString(2, "%" + userKeySearch + "%");
				ResultSet rs = stmt.executeQuery();
					
					while (rs.next()) {
					
						film = new Film();
						film.setId(rs.getInt("id"));
						film.setTitle(rs.getString("title"));
						film.setDescription(rs.getString("description"));
						film.setReleaseYear(rs.getInt("release_year"));
						film.setLanguageId(rs.getInt("language.id"));
						film.setRentalDuration(rs.getInt("rental_duration"));
						film.setRentalRate(rs.getDouble("rental_rate"));
						film.setLength(rs.getInt("length"));
						film.setReplacementCost(rs.getDouble("replacement_cost"));
						film.setRating(rs.getString("rating"));
						film.setSpecialFeatures(rs.getString("special_features"));
						film.setLanguage(rs.getString("language.name"));
						film.setActors(findActorsByFilmId(film.getId()));
						film.setLanguage(getLanguage(film.getId()));
						
						film.add(film);
					}
				rs.close();
				conn.close();
				
					} catch (SQLException e) {
					e.printStackTrace();
				}
			return films;
	}
	
	private String getLanguage(int filmId) {
		String sql = "SELECT language.name FROM film JOIN language ON film.language_id = language.id WHERE film.id = ?";
		String language = "";
		
		try {
			Connection conn = DriverManager.getConnection(URL, user, password);
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, filmId);
			ResultSet rs = stmt.executeQuery();
			
			if (rs.next()) {
				language = rs.getString("language_name");
			}
			
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return language;
	}

}
	

	
	
	
	

		


	
	
	
	
