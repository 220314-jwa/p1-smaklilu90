package com.revature.services;

import java.sql.SQLException;
import java.util.List;

import com.revature.exceptions.AlreadyAdoptedException;
import com.revature.exceptions.IncorrectCredentialsException;
import com.revature.exceptions.PersonnameAlreadyExistsException;
import com.revature.models.Pitch;
import com.revature.models.Person;

/*- here we can lay out all of the behaviors that we want
 * users to be able to do. services are just about the
 * services, or tasks, that we want to provide to users,
 * and the Java that makes those methods work.
 * 
 * it also allows for a separation between database code (DAOs),
 * HTTP handling code (Javalin), and the "business logic" or actual
 * functionality that users are doing (services). this idea is called
 * "separation of concerns" and allows you to have cleaner, more
 * organized, and more maintainable code.
 */
public interface PersonService {
	/**
	 * returns the Person if username and password are correct. 
	 * otherwise throws an IncorrectCredentialsException.
	 * 
	 * @param username
	 * @param password
	 * @return Person matching the given username/password
	 */
	public Person logIn(String username, String password) throws IncorrectCredentialsException;
	
	/**
	 * creates a new user. if the username is available, 
	 * returns the new user with their database-generated ID. 
	 * otherwise, throws a PersonnameAlreadyExistsException.
	 * 
	 * @param newPerson
	 * @return Person with newly generated ID
	 */
	public Person register(Person newPerson) throws UsernameAlreadyExistsException;
	
	/**
	 * 
	 * @return all available pitchs
	 */
	public List<Pitch> viewAvailablePitchs();
	
	/**
	 * 
	 * @param species
	 * @return all available pitchs with the specified species
	 */
	public List<Pitch> searchPitchsBySpecies(String species);
	
	/**
	 * sets the pitch to be adopted by the user. if the pitch
	 * is not available, throws AlreadyAdoptedException.
	 * 
	 * @param user
	 * @param pitchToAdopt
	 * @return user with their newly adopted pitch
	 */
	public Person adoptPitch(Person user, Pitch pitchToAdopt) throws AlreadyAdoptedException;
	
	/**
	 * 
	 * @param id
	 * @return the pitch with the specified ID
	 */
	public Pitch getPitchById(int id);
}
