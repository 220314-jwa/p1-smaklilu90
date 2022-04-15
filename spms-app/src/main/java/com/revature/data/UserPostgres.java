package com.revature.data;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import com.revature.models.Pitch;
import com.revature.models.User;
import com.revature.utils.ConnectionFactory;

public class UserPostgres implements UserDAO   {
private static ConnectionFactory connFactory = ConnectionFactory.getConnectionFactory();
	
	@Override
	public int create(User newObj) {
		int generatedId = 0;
		Connection conn = connFactory.getConnection();
		//                                      0			1		2		3		4			5
		try {                                     
			String sql = "insert into users  (user_id, role_id, full_name , email, password, phone )"
					+ " values (default,?,?,?,?,?)";
			PreparedStatement pStmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
			
			pStmt.setInt(1, newObj.getRole_id());
			pStmt.setString(2, newObj.getFull_name());
			pStmt.setString(3, newObj.getEmail());
			pStmt.setString(4, newObj.getPassword());
			pStmt.setString(5, newObj.getPhone());
			
			
			conn.setAutoCommit(false); // for ACID (transaction management)
			pStmt.executeUpdate();
			ResultSet resultSet = pStmt.getGeneratedKeys();
			
			if (resultSet.next()) {
				generatedId = resultSet.getInt(1);
				conn.commit();
			} else {
				conn.rollback();
			}
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return generatedId;
	}

	@Override
	public User getById(int id) {
		User user = null;
		try (Connection conn = connFactory.getConnection()) {
			String sql = "select * from users where user_id = ?";
			PreparedStatement pStmt = conn.prepareStatement(sql);
		
			
			 try (Connection connection = connFactory.getConnection()) {
		            PreparedStatement preparedStatement = connection.prepareStatement(sql);
		            preparedStatement.setInt(1, id);
		            ResultSet resultSet = preparedStatement.executeQuery();
		            // if result set doesn't point to a next value, that means something went wrong
		            if(resultSet.next()) {
		                user = parseResultSet(resultSet);
		                // now, we've created a pitch Java object based on the info from our table
		            }
		        } catch (SQLException e){
		            e.printStackTrace();
		        }

		        
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return user;
	}

	@Override
	public List<User> getAll() {
		List<User> users = new LinkedList<>();
		try (Connection conn = connFactory.getConnection()) {
			// left join because we want ALL the people even if they don't have any pitchs.
			// a full join would be fine too since everything in the pet_owner table
			// will have a user associated with it, but a left join makes more sense logically
			String sql = "select * from users left join story_pitch on users.user_id= story_pitch.users_id";
			Statement stmt = conn.createStatement();
			
			ResultSet resultSet = stmt.executeQuery(sql);
			while (resultSet.next()) {
				User user = new User();
				//user.setId(resultSet.getInt("id"));
				user.setRole_id(resultSet.getInt("role_id"));
				user.setFull_name(resultSet.getString("full_name"));
				user.setEmail(resultSet.getString("email"));
				user.setPhone(resultSet.getString("phone"));
				user.setPassword(resultSet.getString("password"));
				
//				PitchDAO petDao = DAOFactory.getPitchDAO();
//		     	user.setPitchs(petDao.getByOwner(user));
				
				users.add(user);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return users;
	}

	@Override
	public void update(User updatedObj) throws SQLException {
		Connection conn = connFactory.getConnection();
		try {
			String sql = "update person set role_id=?, full_name=?, email=?, phone=?, password=? "
					+ "where id=?";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setInt(1, updatedObj.getRole_id());
			pStmt.setString(2, updatedObj.getFull_name());
			pStmt.setString(3, updatedObj.getEmail());
			pStmt.setString(3, updatedObj.getPhone());
			pStmt.setString(4, updatedObj.getPassword());
		
			
			conn.setAutoCommit(false); // for ACID (transaction management)
			int rowsUpdated = pStmt.executeUpdate();
			
			if (rowsUpdated==1) {
				conn.commit();
			} else {
				conn.rollback();
				throw new SQLException("ERROR: no object found to update");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			throw e;
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void delete(User objToDelete) throws SQLException {
		Connection conn = connFactory.getConnection();
		try {
			String sql = "delete from users where user_id=?";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setInt(1, objToDelete.getUser_id());
			
			conn.setAutoCommit(false); // for ACID (transaction management)
			int rowsUpdated = pStmt.executeUpdate();
			
			if (rowsUpdated==1) {
				conn.commit();
			} else {
				conn.rollback();
				throw new SQLException("ERROR: no object found to delete");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			throw e;
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public User getByUsername(String username) {
		User user = null;
		try (Connection conn = connFactory.getConnection()) {
			String sql = "select * from users left join story_pitch on users.user_id = story_pitch.user_id"
					+ " where users.email= ?";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, username);
			
			ResultSet resultSet = pStmt.executeQuery();
			if (resultSet.next()) {
				user = new User();
				user.setUser_id(resultSet.getInt("user_id"));
				user.setRole_id(resultSet.getInt("role_id"));
				user.setEmail(resultSet.getString("email"));
				user.setPhone(resultSet.getString("phone"));
				user.setPassword(resultSet.getString("password"));
				
		//		PitchDAO petDao = DAOFactory.getPitchDAO();
			//	user.setPitchs(pitchDao.getByOwner(user));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}

	@Override
	public void updatePitchs(int pitchId) throws SQLException {
		// TODO Auto-generated method stub
		
	}


	

	
	  // given a result Set, parse it out and then return the pitch:
    private User parseResultSet(ResultSet resultSet) throws SQLException {
        User user = new User();
        
        // do something with the return value:
        user.setUser_id(resultSet.getInt("user_id"));  
        user.setRole_id(resultSet.getInt("role_id"));  
        user.setFull_name(resultSet.getString("full_name"));  
        user.setEmail(resultSet.getString("email"));  
        user.setPassword(resultSet.getString("password"));  
        user.setPhone(resultSet.getString("phone"));  
       
        
       
        return user;
    }

	@Override
	public void update(int id) throws SQLException {
		// TODO Auto-generated method stub
		
	}
	

}

