package com.revature.services;

import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import com.revature.data.PitchDAO;
import com.revature.data.PersonDAO;
import com.revature.exceptions.AlreadyAdoptedException;
import com.revature.exceptions.IncorrectCredentialsException;
import com.revature.exceptions.PersonnameAlreadyExistsException;
import com.revature.models.Pitch;
import com.revature.models.Person;
import com.revature.data.DaoFactory;

public class PersonServiceImpl implements PersonService {
	private PersonDAO personDao;
	private PitchDAO pitchDao = DaoFactory.getPitchDAO();

	@Override
	public Person logIn(String personname, String password) throws IncorrectCredentialsException {
		Person person = personDao.getByPersonname(personname);
		if (person != null && person.getPassword().equals(password)) {
			return person;
		} else {
			throw new IncorrectCredentialsException();
		}
	}

	@Override
	public Person register(Person newPerson) throws PersonnameAlreadyExistsException{
		int id = personDao.create(newPerson);
		if (id != 0) {
			newPerson.setId(id);
			return newPerson;
		} // else
		return null;
	}

	@Override
	public List<Pitch> viewAvailablePitchs() {
		return pitchDao.getByStatus("Available");
		
		// alternative using Java 8 Streams (less SQL, more Java)
		// if you're interested :)
//		List<Pitch> pitchs = pitchDao.getAll();
//		pitchs = pitchs.stream()
//				.filter((pitch) -> pitch.getStatus().equals("Available"))
//				.collect(Collectors.toList());
//		return pitchs;
	}

	@Override
	public List<Pitch> searchPitchsBySpecies(String species) {
		List<Pitch> pitchs = pitchDao.getAll();
		
		// Java 8 Streams way (more efficient) with a lambda
//		pitchs = pitchs.stream()
//				.filter((pitch) -> pitch.getSpecies().toLowerCase().contains(species.toLowerCase()))
//				.collect(Collectors.toList());
//		return pitchs;
		
		// without Streams
		List<Pitch> pitchsWithSpecies = new LinkedList<>();
		for (int i=0; i<pitchs.size(); i++) {
			// if the pitch's species is equal to the species passed in
			// (toLowerCase to allow it to be case-insensitive)
			if (pitchs.get(i).getSpecies().toLowerCase().equals(species.toLowerCase())) {
				pitchsWithSpecies.add(pitchs.get(i));
			}
		}
		
		return pitchsWithSpecies;
	}

	@Override
	public Person adoptPitch(Person person, Pitch pitchToAdopt) throws AlreadyAdoptedException {
		// check pitchToAdopt with its database entry to get most up-to-date status
		pitchToAdopt = pitchDao.getById(pitchToAdopt.getId());
		
		// make sure the pitch is not already adopted
		if (pitchToAdopt.getStatus().equals("Adopted")) {
			throw new AlreadyAdoptedException();
		} else {
			// check person to make sure account is valid
			person = personDao.getById(person.getId());
			if (person != null) {
				// proceed with adopting
				pitchToAdopt.setStatus("Adopted");
				person.getPitchs().add(pitchToAdopt);
				pitchDao.update(pitchToAdopt);
				personDao.update(person);
			}
			return person;
		}
	}

	@Override
	public Pitch getPitchById(int id) {
		return pitchDao.getById(id);
	}
}
