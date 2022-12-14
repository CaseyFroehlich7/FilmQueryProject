package com.skilldistillery.filmquery.entities;

import java.util.ArrayList;
import java.util.Objects;

public class Actor extends Film {
	
	private int id;
	private String firstName;
	private String lastName;
	private ArrayList<Film> film; //not required for hw 
	
	public Actor() {
		
	}
	
	public Actor(int id, String fn, String ln) {
		this.id = id;
		this.firstName = fn;
		this.lastName = ln;
	}
	

	public Actor(int id, String firstName, String lastName, ArrayList<Film> films) {
		this(id, firstName, lastName);
		this.film = films;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	

	public ArrayList<Film> getFilms() {
		return film;
	}

	public void setFilms(ArrayList<Film> films) {
		this.film = films;
	}

	@Override
	public String toString() {
		return "Actor [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Actor other = (Actor) obj;
		return id == other.id;
	}
	
}
