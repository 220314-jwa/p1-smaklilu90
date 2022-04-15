package com.revature.services;

// static import of the Assertions methods
// this allows us to call the methods like this:
// assertTrue(result);
// instead of this:
// Assertions.assertTrue(result);
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;

import com.revature.exceptions.AlreadyAdoptedException;
import com.revature.exceptions.IncorrectCredentialsException;
import com.revature.exceptions.UsernameAlreadyExistsException;
import com.revature.models.Pet;
import com.revature.models.User;

public class UserServiceTest {
	// we need a field for the class that we're testing
	private UserService userServ = new UserServiceImpl();
	
	// test methods always have no parameters and return void
	@Test
	public void exampleTest() {
		assertTrue(true);
	}
	
	@Test
	public void logInSuccessfully() throws IncorrectCredentialsException {
		// setup (arguments, expected result, etc.)
		String username = "snicholes";
		String password = "pass";
		
		// call the method we're testing
		User result = userServ.logIn(username, password);
		
		// assertion
		assertEquals(username, result.getEmail());
	}
	
	@Test
	public void logInWrongUsername() {
		String username = "abc123";
		String password = "1234567890";
		
		assertThrows(IncorrectCredentialsException.class, () -> {
			// put the code that we're expecting to throw the exception
			userServ.logIn(username, password);
		});
	}
	
	@Test
	public void logInWrongPassword() {
		String username = "snicholes";
		String password = "1234567890";
		
		assertThrows(IncorrectCredentialsException.class, () -> {
			// put the code that we're expecting to throw the exception
			userServ.logIn(username, password);
		});
	}
	
	@Test
	public void registerSuccessfully() throws EmailAlreadyExistsException {
		User newUser = new User();
		
		User result = userServ.register(newUser);
		
		// the behavior that i'm looking for is that the
		// method returns the User with their newly generated ID,
		// so i want to make sure the ID was generated (not the default)
		assertNotEquals(0, result.getUser_id());
	}
	
	@Test
	public void registerUsernameTaken() {
		User newUser = new User();
		newUser.setUsername("snicholes");
		
		assertThrows(UsernameAlreadyExistsException.class, () -> {
			userServ.register(newUser);
		});
	}
	
	@Test
	public void viewPetsSuccessfully() {
		List<Pet> pets = userServ.viewAvailablePets();
		
		// i just want to make sure that the pets are returned -
		// i don't need to check that the pets are all available
		// because that filtering happens in the database. i just
		// need to check that the pets list isn't null
		assertNotNull(pets);
	}
	
	@Test
	public void searchPetsBySpecies() {
		String species = "cat";
		List<Pet> petsBySpecies = userServ.searchPetsBySpecies(species);
		
		boolean onlyCatsInList = true;
		for (Pet pet : petsBySpecies) {
			String petSpecies = pet.getSpecies().toLowerCase();
			// if the pet species doesn't contain the species passed in
			if (!petSpecies.contains(species)) {
				// then we'll set the boolean to false
				onlyCatsInList = false;
				// and stop the loop because we don't need to continue
				break;
			}
		}
		
		assertTrue(onlyCatsInList);
	}
	
	@Test
	public void adoptPetSuccessfully() throws Exception {
		User testUser = new User();
		Pet testPet = new Pet();
		
		User result = userServ.adoptPet(testUser, testPet);
		
		// there are two behaviors i'm looking for:
		// that the user now has the pet in their list of pets,
		// and that the pet in the list has its status updated.
		// to check this, i'm checking that the pet with the Adopted
		// status is in the user's list.
		testPet.setStatus("Adopted");
		List<Pet> usersPets = result.getPets();
		assertTrue(usersPets.contains(testPet));
	}
	
	@Test
	public void adoptPetAlreadyAdopted() {
		User testUser = new User();
		Pet testPet = new Pet();
		testPet.setStatus("Adopted");
		
		assertThrows(Exception.class, () -> {
			userServ.adoptPet(testUser, testPet);
		});
	}
}
