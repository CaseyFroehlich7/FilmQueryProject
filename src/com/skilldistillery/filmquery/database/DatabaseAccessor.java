package com.skilldistillery.filmquery.database;

import java.sql.SQLException;
import java.util.ArrayList;

import com.skilldistillery.filmquery.entities.Actor;
import com.skilldistillery.filmquery.entities.Film;

public interface DatabaseAccessor {
	
  public Film findFilmById(int filmId) throws SQLException;
  public Actor findActorById(int actorId) throws SQLException;
  public ArrayList<Actor> findActorsByFilmId(int filmId);
  public ArrayList<Film> findFilmByKeyword(String userKeySearch);
  public String getLanguageName(int filmId);
  public ArrayList<Film> findFilmsByActorId(int actorId, Film film);
 
} 