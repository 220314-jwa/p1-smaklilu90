package com.revature.data;

public class DAOFactory {

    // initialize our pet dao to be null
    // keep static instances of our DAOs
    // save memory, etc.
    private static PitchDAO pitchDAO = null;
    private static UserDAO userDAO = null;

    // make our constructor private, so it can't be accessed publicly
    private DAOFactory() { }

    public static PitchDAO getPitchDAO() {
        // make sure we're not recreating the dao if it already exists:
        if (pitchDAO == null) {
            pitchDAO = new PitchPostgres();
        }
        return pitchDAO;
    }
    
    public static UserDAO getUserDAO() {
    	if (userDAO == null)
    		userDAO = new UserPostgres();
    	return userDAO;
    }
}
