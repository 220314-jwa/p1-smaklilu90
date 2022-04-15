package com.revature.data;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;
import java.util.List;
import java.util.Random;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;

import com.revature.models.Pitch;

@TestMethodOrder(OrderAnnotation.class)
public class PitchDAOTest {
	private static PitchDAO pitchDao = DAOFactory.getPitchDAO();
	private static Pitch testPitch = new Pitch();
	private static Pitch testNewPitch = new Pitch();
	
	@BeforeAll
	public static void setUp() {
		// this is the base test pitch used for most tests
		testPitch.setDescription("test");
		
		// this is the pitch to test create and delete
		Random rand = new Random();
		testNewPitch.setDescription("test_" + rand.nextLong());
		
		// TODO create pitch in DB with name "test"
		// and set the pitch's ID to the returned value
		testPitch.setPitch_id(pitchDao.create(testPitch));
	}
	
	@AfterAll
	public static void cleanUp() throws SQLException {
		// TODO remove pitchs in DB with name containing "test"
		pitchDao.delete(testPitch);
	}
	
	@Test
	@Disabled
	public void getByOwnerExists() {
		// TODO
	}
	
	@Test
	@Disabled
	public void getByOwnerDoesNotExist() {
		// TODO
	}
	

	
	@Test
	@Order(1)
	public void createpitchSuccessfully() {
		int id = pitchDao.create(testNewPitch);
		testNewPitch.setPitch_id(id);
		
		assertNotEquals(0, id);
	}
	
	@Test
	public void getByIdExists() {
		int id = testPitch.getPitch_id();
		
		Pitch pitch = pitchDao.getById(id);
		
		assertEquals(testPitch, pitch);
	}
	
	@Test
	public void getByIdDoesNotExist() {
		Pitch pitch = pitchDao.getById(0);
		assertNull(pitch);
	}
	
	@Test
	public void getAll() {
		assertNotNull(pitchDao.getAll());
	}
	
	@Test
	public void updatepitchExists() {
		assertDoesNotThrow(() -> {
			pitchDao.update(testPitch);
		});
	}
	
	@Test
	public void updatepitchDoesNotExist() {
		assertThrows(SQLException.class, () -> {
			pitchDao.update(new Pitch());
		});
	}
	
	@Test
	@Order(2)
	public void deletepitchExists() {
		assertDoesNotThrow(() -> {
			pitchDao.delete(testNewPitch);
		});
	}
	
	@Test
	public void deletepitchDoesNotExist() {
		assertThrows(SQLException.class, () -> {
			pitchDao.delete(new Pitch());
		});
	}
}
