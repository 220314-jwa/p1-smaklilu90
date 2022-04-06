package com.revature.data;

import com.revature.models.Person;

//we can set the generic's type here since we are inheriting it
//i've set it to User so a class that implements this interface will
//have the types as User
public interface PersonDAO extends GenericDAO<Person> {
	// here we can add any other methods that are not covered by basic CRUD
	// (we inherited the CRUD method from the generic DAO
	public Person getByUsername(String username);
}
