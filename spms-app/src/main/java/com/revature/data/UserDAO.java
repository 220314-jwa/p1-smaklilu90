package com.revature.data;

import java.sql.SQLException;

import com.revature.models.User;

public interface UserDAO extends  GenericDAO <User>{
	
	
	public User getByUsername(String username);
	public void updatePitchs(int pitchId) throws SQLException;

}
