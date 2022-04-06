package com.revature.data;

// this class is responsible for instantiating/returning dao
public class DaoFactory {
    // initialize our pith dao to be null
    // keep static instances of our DAOs
    // save memory, etc.
    private static PitchDAO pithDAO = null;
    private static PersonDAO personDAO = null;

    // make our constructor private, so it can't be accessed publicly
    private DaoFactory() {

    }

    public static PitchDAO getPitchDAO() {
        // make sure we're not recreating the dao if it already exists:
        if (pithDAO == null) {
            pithDAO = new PitchDAOImpl();
        }
        return pithDAO;
    }
}
