package com.revature.data;

import com.revature.ConnectionFactory;
import com.revature.models.Pitch;
import com.revature.models.Person;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

// ide should let you auto-generate methods
public class PitchDAOImpl implements PitchDAO{

    // connection object, used to connect to the database:
    Connection connection;

    // constructor, retrieve/get a connection from the connection factory
    public PitchDAOImpl () {
        // calling the method that we made in ConnectionFactory:
        connection = ConnectionFactory.getConnection();
    }

    @Override
    // this method needs to insert the object into the database:
    // so, we need to connect to the database:
    public int create(Pitch newObj) {

        // this stores our sql command, that we would normally to DBeaver/command line
        //                                           0      1         2        3            4                 5
        String sql = "insert into story_pitch (pitch_id, person_id, genre_id, status_id, tentative_title, comp_date,description, blurb, length_id)" + 
        "values (default, ?, ?, ?, ?, ?, ?, ?, ?)";

        try {
            // create a prepared statement, we pass in the sql command
            // also the flag "RETURN_GENERATED_KEYS" so we can get that id that is generated
            PreparedStatement preparedStatement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            // set the fields:
            preparedStatement.setInt(1, newObj.getPerson_id());
            preparedStatement.setInt(2, newObj.getGenre_id());
           preparedStatement.setInt(3, newObj.getStatus_id());
            preparedStatement.setString(4, newObj.getTentative_title());
            preparedStatement.setString(5, newObj.getComp_date());
            preparedStatement.setString(6, newObj.getDescription());
            preparedStatement.setString(7, newObj.getBlurb());
            preparedStatement.setString(8, newObj.getBlurb());
            preparedStatement.setInt(8, newObj.getLength_id());

            // shortcut for now, but we need the corresponding id for the status
            int status_id = 1;
            
           // I will  fix this later
//            if (newObj.getStatus_id().equals(1)) {
//                status_id = 1;
//            }
//            else {
//                status_id = 2;
//            }
            preparedStatement.setInt(9, status_id);
            // execute this command, return number of rows affected:
            int count = preparedStatement.executeUpdate();
            // lets us return the id that is auto-generated
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            // if we affected one or more rows:
            if (count > 0) {
                System.out.println("Pitch added!");
                // return the generated id:
                // before we call resultSet.next(), it's basically pointing to nothing useful
                // but moving that pointer allows us to get the information that we want
                resultSet.next();
                int id = resultSet.getInt(1);
                return id;
            }
            // if 0 rows are affected, something went wrong:
            else {
                System.out.println("Something went wrong when trying to add pitch!");
                return -1;
            }
        } catch (SQLException e){
            // print out what went wrong:
            e.printStackTrace();
            return -1;
        }
    }

    @Override
    // take in an id, return the corresponding pitch
    public Pitch getById(int id) {
        // initialize our pitch object to be null:
        Pitch pitch = null;
        // placeholder for our final sql string
        // ? placeholder for our id:
        // * means all fields
        // but we specify an id so we only get one single pitch:
        String sql = "SELECT * FROM story_pitch WHERE pitch_id = ?";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            // if result set doesn't point to a next value, that means something went wrong
            if(resultSet.next()) {
                pitch = PitchDAOImpl.parseResultSet(resultSet);
                // now, we've created a pitch Java object based on the info from our table:
                return pitch;            }
            else {
                System.out.println("Something went wrong when querying the pitch!");
                // return null in this case:
                return pitch;
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        finally {
            return pitch;
        }

    }

    @Override
    public List<Pitch> getAll() {
        // initialize empty Pitch List:
        List<Pitch> pitchs = new ArrayList<Pitch>();

        String sql = "SELECT * FROM story_pitch";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            // get the result from our query:
            ResultSet resultSet = preparedStatement.executeQuery();
            // because the resultSet has multiple pitchs in it, we don't just want an if-statement. We want a loop:
            while(resultSet.next()) {
                Pitch pitch = PitchDAOImpl.parseResultSet(resultSet);
                pitchs.add(pitch);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pitchs;
    }

    // given a result Set, parse it out and then return the pitch:
    private static Pitch parseResultSet(ResultSet resultSet) throws SQLException {
        Pitch pitch = new Pitch();
        // do something with the return value:
        
        
        
        pitch.setPerson_id(resultSet.getInt(1));
        pitch.setGenre_id(resultSet.getInt(2));
        pitch.setStatus_id(resultSet.getInt(3));
        pitch.setTentative_title(resultSet.getString(4));
        pitch.setComp_date(resultSet.getString(5));
        pitch.setTentative_title(resultSet.getString(4));
        pitch.setComp_date(resultSet.getString(5));
        pitch.setDescription(resultSet.getString(6));
        pitch.setBlurb(resultSet.getString(7));
        pitch.setLength_id(resultSet.getInt(8));
        int status_id = resultSet.getInt(9); 
        
    
        

        // TODO: get the status from the status db:

        String status;
       if (status_id == 1) {
        status = "available";
       }
       else {           status = "taken";
       }
          pitch.setStatus_id(1);
          return pitch;
  
} 
    @Override
    public void update(Pitch updatedObj) {
    	// we create the template for the SQL string:
    	String sql = "update pitch set person_id = ?, genre_id = ?, status_id = ?, tenative_id = ?, comp_date = ? description = ? blurb = ? length_id ? ;" ;
    	try {
        	PreparedStatement preparedStatement = connection.prepareStatement(sql);
        	// fill in the template:
        	 preparedStatement.setInt(1, updatedObj.getPerson_id());
             preparedStatement.setInt(2, updatedObj.getGenre_id());
            preparedStatement.setInt(3, updatedObj.getStatus_id());
             preparedStatement.setString(4, updatedObj.getTentative_title());
             preparedStatement.setString(5, updatedObj.getComp_date());
             preparedStatement.setString(6, updatedObj.getDescription());
             preparedStatement.setString(7, updatedObj.getBlurb());
             preparedStatement.setString(8, updatedObj.getBlurb());
             preparedStatement.setInt(8, updatedObj.getLength_id());
        	int count = preparedStatement.executeUpdate();
        	
        	 
        	
        	if(count != 1) {
        		System.out.println("Oops! Something went wrong with the update!");
        	}
        	
    		
    	} catch(SQLException e) {
    		e.printStackTrace();
    	}

    }

    @Override
    public void deleteById(int id) {
    	String sql = "delete from pitch where id = ?;";
    	try {
    		PreparedStatement preparedStatement = connection.prepareStatement(sql);
    		preparedStatement.setInt(1, id);
    		int count = preparedStatement.executeUpdate();
    		if (count != 1) {
    			System.out.println("Something went wrong with the deletion!");
    		}
    	} catch (SQLException e) {
    		e.printStackTrace();
    	}
    	
    }

    // change this to just take id:
    @Override
    public void delete(Pitch objToDelete) {

    }

    @Override
    public List<Pitch> getByStatus(String status) {
        return null;
    }

    @Override
    public List<Pitch> getByOwner(Person owner) {
        return null;
    }
}
