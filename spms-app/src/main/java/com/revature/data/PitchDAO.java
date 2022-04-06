package com.revature.data;

import java.util.List;

import com.revature.models.Pitch;
import com.revature.models.Person;

// we can set the generic's type here since we are inheriting it
// i've set it to Pitch so a class that implements this interface will
// have the types as Pitch
public interface PitchDAO extends GenericDAO<Pitch> {
	public List<Pitch> getByStatus(String status);
	public List<Pitch> getByOwner(Person owner);
}
