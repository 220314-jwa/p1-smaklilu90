package com.revature.services;

import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import com.revature.data.DAOFactory;
import com.revature.data.PitchDAO;
import com.revature.data.UserDAO;
//import com.revature.exceptions.AlreadyRejectedException;
import com.revature.exceptions.IncorrectCredentialsException;
import com.revature.exceptions.EmailAlreadyExistsException;
import com.revature.models.Pitch;
import com.revature.models.User;

public  class UserServiceImpl implements UserService{
	
	private UserDAO userDao = DAOFactory.getUserDAO();
	private PitchDAO pitchDao = DAOFactory.getPitchDAO();

	@Override
	public User logIn(String email, String password) throws IncorrectCredentialsException {
		User user = userDao.getByUsername(email);
		if (user != null && user.getPassword().equals(password)) {
			return user;
		} else {
			throw new IncorrectCredentialsException();
		}
	}

	@Override
	public User register(User newUser) throws EmailAlreadyExistsException {
		int id = userDao.create(newUser);
		if (id != 0) {
			newUser.setUser_id(id);
			return newUser;
		} else {
			throw new EmailAlreadyExistsException();
		}
	}

	@Override
	public List<Pitch> viewAllPitchs() {
		return pitchDao.getAll();
		
		
	}

	//@Override
	public Pitch getPitchById(int id) {
		return pitchDao.getById(id);
	}
	
	@Override
	public User getUserById(int id) {
		return userDao.getById(id);
	}
//
//	@Override
//	public List<Pitch> viewAllPitchs() {
//		// TODO Auto-generated method stub
//		return null;
//	}

	@Override
	public Pitch updatePitch(int id) {
		// TODO Auto-generated method stub
		return pitchDao.getById(id);
	}

	@Override
	public Pitch registerPitch(Pitch newPitch) throws EmailAlreadyExistsException {
		int id = pitchDao.create(newPitch);
		if (id != 0) {
			newPitch.setUser_id(id);
			return newPitch;
		} else {
			throw new EmailAlreadyExistsException();
		}
	}
	}



	
//	@Override
//	public User rejectPitch(User user, Pitch pitchToUpdate) throws AlreadyRejectedException, Exception {
//		// TODO Auto-generated method stub
//		return null;
//	}

//	@Override
//	public Pitch getPitchById(int id) {
//		// TODO Auto-generated method stub
//		return null;
//	}




