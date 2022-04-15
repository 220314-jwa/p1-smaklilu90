package com.revature.data;
import java.sql.SQLException;
import java.util.List;

import com.revature.models.Pitch;
import com.revature.models.User;

public interface PitchDAO extends GenericDAO <Pitch> {
	public List<Pitch> getAll();
	public List<Pitch> getByOwner(User owner);

}
