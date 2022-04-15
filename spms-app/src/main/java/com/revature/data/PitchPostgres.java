package com.revature.data;

import com.revature.utils.ConnectionFactory;
import com.revature.models.Pitch;
import com.revature.models.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.sql.Date;  

public class PitchPostgres implements PitchDAO  {
	
	private static ConnectionFactory connFactory = ConnectionFactory.getConnectionFactory();

	@Override
    // this method needs to insert the object into the database:
    // so, we need to connect to the database:
    public int create(Pitch newObj) {
		Connection connection = connFactory.getConnection();
		
        // this stores our sql command, that we would normally to DBeaver/command line
        //                                         0         1        2        3               4           5          6              7       8
        String sql = "INSERT INTO story_pitch (pitch_id, user_id, genre_id, length_id, tentative_title, comp_date,  description, blurb)"+
                "values (default, ?, ?, ?, ?, ?, ?, ?)";
        
        try {
            // create a prepared statement, we pass in the sql command
            // also the flag "RETURN_GENERATED_KEYS" so we can get that id that is generated
            PreparedStatement preparedStatement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            // set the fields:
            preparedStatement.setInt(1, newObj.getUser_id());
            preparedStatement.setInt(2, newObj.getGenre_id());
            // the status_id has a default value of 1(pending approval) and don't need to be set by the author
           // preparedStatement.setInt(3, newObj.getStatus_id());
            preparedStatement.setInt(3, newObj.getLength_id());
            preparedStatement.setString(4, newObj.getTentative_title());
            //convert string to Date 
            Date date = Date.valueOf(newObj.getComp_date());
            preparedStatement.setDate(5, date);
            preparedStatement.setString(6, newObj.getDescription());
            preparedStatement.setString(7, newObj.getBlurb());

            
            connection.setAutoCommit(false); // for tx management (ACID)
            // execute this command, return number of rows affected:
            int count = preparedStatement.executeUpdate();
            // lets us return the id that is auto-generated
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            // if we affected one or more rows:
            if (count > 0) {
                System.out.println("Story Pitch added!");
                // return the generated id:
                // before we call resultSet.next(), it's basically pointing to nothing useful
                // but moving that pointer allows us to get the information that we want
                resultSet.next();
                int id = resultSet.getInt(1);
                newObj.setPitch_id(id);
                connection.commit(); // commit the changes to the DB
            }
            // if 0 rows are affected, something went wrong:
            else {
                System.out.println("Something went wrong when trying to add the Pitch!");
                connection.rollback(); // rollback the changes
            }
        } catch (SQLException e){
            // print out what went wrong:
            e.printStackTrace();
            try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
        } finally {
        	try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
        }
        
        return newObj.getPitch_id();
    }

    @Override
    // take in an id, return the corresponding pitch
    public Pitch getById(int id) {
        // initialize our pet object to be null:
        Pitch pitch = null;
        // placeholder for our final sql string
        // ? placeholder for our id:
        // * means all fields
        // but we specify an id so we only get one single pet:
        String sql = "SELECT * FROM story_pitch WHERE pitch_id = ?";

        try (Connection connection = connFactory.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            // if result set doesn't point to a next value, that means something went wrong
            if(resultSet.next()) {
                pitch = parseResultSet(resultSet);
                // now, we've created a pet Java object based on the info from our table
            }
        } catch (SQLException e){
            e.printStackTrace();
        }

        return pitch;
    }

    @Override
    public List<Pitch> getAll() {
        // initialize empty Pitch List:
        List<Pitch> pitchs = new ArrayList<Pitch>();

        String sql = "SELECT * FROM story_pitch";
        try (Connection connection = connFactory.getConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            // get the result from our query:
            ResultSet resultSet = preparedStatement.executeQuery();
            // because the resultSet has multiple pitchs in it, we don't just want an if-statement. We want a loop:
            while(resultSet.next()) {
                Pitch pitch = parseResultSet(resultSet);
                pitchs.add(pitch);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pitchs;
    }

    // given a result Set, parse it out and then return the pitch:
    private Pitch parseResultSet(ResultSet resultSet) throws SQLException {
        Pitch pitch = new Pitch();
        
        // do something with the return value:
        pitch.setPitch_id(resultSet.getInt("pitch_id"));  
        pitch.setUser_id(resultSet.getInt("user_id"));  
        pitch.setGenre_id(resultSet.getInt("genre_id"));  
        pitch.setStatus_id(resultSet.getInt("status_id"));  
        pitch.setLength_id(resultSet.getInt("length_id"));  
        pitch.setTentative_title(resultSet.getString("tentative_title"));
        pitch.setComp_date(resultSet.getString("comp_date"));
        pitch.setDescription(resultSet.getString("description"));
        pitch.setBlurb(resultSet.getString("blurb"));
        
       
        return pitch;
    }

    @Override
    public void update(Pitch updatedObj) throws SQLException {
    	Connection connection = connFactory.getConnection();
    	// we create the template for the SQL string:
    	String sql = "update story_pitch set status_id = ? where id = ?;";
    
    	try {
        	PreparedStatement preparedStatement = connection.prepareStatement(sql);
        	// fill in the template:
        	   preparedStatement.setInt(1, updatedObj.getPitch_id());
               preparedStatement.setInt(2, updatedObj.getStatus_id());
             
        	
   
        	connection.setAutoCommit(false);
        	// return a count of how many records were updated
        	int count = preparedStatement.executeUpdate();
        	if(count != 1) {
        		connection.rollback();
        		throw new SQLException("ERROR: no object found to update");
        	} else connection.commit();
        	
    		
    	} catch(SQLException e) {
    		e.printStackTrace();
    		try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
    		throw e;
    	} finally {
    		try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
    	}

    }

    @Override
    public void delete(Pitch objToDelete) throws SQLException {
    	Connection connection = connFactory.getConnection();
    	
    	String sql = "delete from story_pitch where pitch_id = ?;";
    	try {
    		PreparedStatement preparedStatement = connection.prepareStatement(sql);
    		preparedStatement.setInt(1, objToDelete.getPitch_id());
    		
    		connection.setAutoCommit(false);
    		int count = preparedStatement.executeUpdate();
    		if (count != 1) {
    			connection.rollback();
    			throw new SQLException("ERROR: no object found to update");
    		} else connection.commit();
    	} catch (SQLException e) {
    		e.printStackTrace();
    		try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
    		throw e;
    	} finally {
    		try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
    	}
    }

  //  @Override
    public List<Pitch> getByStatus(int status) {
    	List<Pitch> pitchs = new LinkedList<>();
    	try (Connection conn = connFactory.getConnection()) {
    		String sql = "select * from story_pitch where status_id=?";
    		PreparedStatement pStmt = conn.prepareStatement(sql);
//    		// may need modified later if new statuses are added
//    		int statusId = (status.equals("Available")?1:2);
    		pStmt.setInt(1, 1);
    		
    		ResultSet resultSet = pStmt.executeQuery();
    		while (resultSet.next()) {
    			Pitch pitch = parseResultSet(resultSet);
    			pitchs.add(pitch);
    		}
    	} catch (SQLException e) {
    		e.printStackTrace();
    	}
    	
        return pitchs;
    }

	@Override
	public List<Pitch> getByOwner(User owner) {
		// TODO Auto-generated method stub
		return null;
	}




 //   @Override
    
 //   check this later 
//    public List<Pitch> getByOwner(User owner) {
//    	List<Pitch> pitchs = new LinkedList<>();
//    	try (Connection conn = connFactory.getConnection()) {
//    		String sql = "select * from stroy_pitch join users on story_pitch.user_id = users.user_id"
//    				+ " where users.user_id=?";
//    		PreparedStatement pStmt = conn.prepareStatement(sql);
//    		pStmt.setInt(1, owner.getUser_id());
//    		
//    		ResultSet resultSet = pStmt.executeQuery();
//    		while (resultSet.next()) {
//    			Pitch pitch = parseResultSet(resultSet);
//    			pitch.add(pitch);
//    		}
//    	} catch (SQLException e) {
//    		e.printStackTrace();
//    	}
//    	
//        return pitchs;
//  
//    
//  }

	  public void update(int id) {
	        // initialize our pet object to be null:
	        Pitch pitch = null;
	        // placeholder for our final sql string
	        // ? placeholder for our id:
	        // * means all fields
	        // but we specify an id so we only get one single pet:
	        String sql = "update story_pitch set status_id = 2 where pitch_id=?";

	        try (Connection connection = connFactory.getConnection()) {
	            PreparedStatement preparedStatement = connection.prepareStatement(sql);
	            preparedStatement.setInt(1, id);
	            ResultSet resultSet = preparedStatement.executeQuery();
	            // if result set doesn't point to a next value, that means something went wrong
	            if(resultSet.next()) {
	                pitch = parseResultSet(resultSet);
	                // now, we've created a pet Java object based on the info from our table
	            }
	        } catch (SQLException e){
	            e.printStackTrace();
	        }

	       // return pitch;
	    }
	
	
	
	
	
	
	
	
	
	
	
	

}

	
	
	
	
	
	
	
	


