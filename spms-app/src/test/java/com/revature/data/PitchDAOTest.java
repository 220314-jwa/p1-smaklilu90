package com.revature.data;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;
import java.util.List;
import java.util.Random;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;

import com.revature.models.Pet;

@TestMethodOrder(OrderAnnotation.class)
public class PitchDAOTest {
	private static PitchDAO petDao; // TODO instantiate
	private static Pitch testPitch = new Pitch();
	private static Pitch testNewPitch = new Pitch(); 

	@BeforeAll
	public static void setUp() {
		// this is the base test pet used for most tests
		testPitch.setPitch_id("test");

		// this is the pet to test create and delete
		Random rand = new Random();
		testNewPitch.setName("test_" + rand.nextLong());

		// TODO create pet in DB with name "test"
		// and set the pet's ID to the returned value
	}

	@AfterAll
	public static void cleanUp() {
		// TODO remove pets in DB with name containing "test"
	}

	@Test
	public void getByOwnerExists() {
		// TODO
	}

	@Test
	public void getByOwnerDoesNotExist() {
		// TODO
	}

	@Test
	public void getByStatus() {
		String testStatus = "Available";
		List<Pet> pets = petDao.getByStatus(testStatus);

		boolean onlyCorrectStatus = true;
		for (Pet pet : pets) {
			if (!(pet.getStatus().equals(testStatus))) {
				onlyCorrectStatus = false;
				break;
			}
		}
		assertTrue(onlyCorrectStatus);
	}

	@Test
	@Order(1)
	public void createPetSuccessfully() throws SQLException{
		int id = petDao.create(testNewPet);

		assertNotEquals(0, id);
	}

	@Test
	public void getByIdExists() {
		int id = testPet.getId();

		Pet pet = petDao.getById(id);

		assertEquals(testPet, pet);
	}

	@Test
	public void getByIdDoesNotExist() {
		Pet pet = petDao.getById(0);
		assertNull(pet);
	}

	@Test
	public void getAll() {
		assertNotNull(petDao.getAll());
	}

	@Test
	public void updateUserExists() {
		assertDoesNotThrow(() -> {
			petDao.update(testPet);
		});
	}

	@Test
	public void updateUserDoesNotExist() {
		assertThrows(SQLException.class, () -> {
			petDao.update(new Pet());
		});
	}

	@Test
	@Order(2)
	public void deleteUserExists() {
		assertDoesNotThrow(() -> {
			petDao.delete(testNewPet);
		});
	}

	@Test
	public void deleteUserDoesNotExist() {
		assertThrows(SQLException.class, () -> {
			petDao.delete(new Pet());
		});
	}
}
