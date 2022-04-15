package com.revature.data;
import java.sql.SQLException;
import java.util.List;

import com.revature.models.Pitch;


	
public interface GenericDAO <T> {
		// CRUD methods: create, read, update, delete
		public int create(T newObj); // returns the generated ID
		public T getById(int id); // read one
		public List<T> getAll(); // read all
		public void update(T updatedObj) throws SQLException;
		public void update(int id) throws SQLException;
		public void delete(T objToDelete) throws SQLException;
	}

	

