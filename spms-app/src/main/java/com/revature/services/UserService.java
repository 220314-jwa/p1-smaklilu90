package com.revature.services;

import java.util.List;

//import com.revature.exceptions.AlreadyRejectedException;
import com.revature.exceptions.IncorrectCredentialsException;
import com.revature.exceptions.EmailAlreadyExistsException;
import com.revature.models.Pitch;
import com.revature.models.User;

public interface UserService {
	/**
	 * returns the User if username and password are correct. 
	 * otherwise throws an IncorrectCredentialsException.
	 * 
	 * @param username
	 * @param password
	 * @return User matching the given username/password
	 */
	public User logIn(String username, String password) throws IncorrectCredentialsException;
	
	/**
	 * creates a new user. if the username is available, 
	 * returns the new user with their database-generated ID. 
	 * otherwise, throws a UsernameAlreadyExistsException.
	 * 
	 * @param newUser
	 * @return User with newly generated ID
	 */
	public User register(User newUser) throws EmailAlreadyExistsException;
	
	
	public Pitch registerPitch(Pitch newPitch) throws EmailAlreadyExistsException;
	
	/**
	 * 
	 * @return all available pets
	 */
	public List<Pitch> viewAllPitchs();
	
	/**
	 * 
	 * @param species
	 * @return all available pets with the specified species
	 */
//	public List<Pitch> updatePitchs(String genre);
	
	/**
	 * sets the pet to be adopted by the user. if the pet
	 * is not available, throws AlreadyAdoptedException.
	 * 
	 * @param user
	 * @param petToAdopt
	 * @return user with their newly adopted pet
	 * @throws Exception 
	 */

	
	/**
	 * 
	 * @param id
	 * @return the pet with the specified ID
	 */
	public Pitch getPitchById(int id);
	
	/**
	 * 
	 * @param id
	 * @return the user with the specified ID
	 */
	public User getUserById(int id);

	public Pitch updatePitch(int id);

	//User updatePitch(int id);
}
