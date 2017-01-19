package Services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import DataBase.DataBaseConnection;

public class Crudes {
	Connection connection;
	
	public Boolean findID(int ID , String tableName) {	
		try{
			//create a connexion with the database
			 connection= DataBaseConnection.getConnection();
			//prepare the sql body
			 String sql="SELECT * FROM dataValues WHERE mesureID = ? ";
			//prepare statement to the connexion with the database 
			 PreparedStatement pst = connection.prepareStatement(sql);
			//set into the statement the variable at the first position
			// pst.setString(1, tableName);
			//set into the statement the variable at the first position
			 pst.setInt(1, ID); 
			 
			 ResultSet rs = pst.executeQuery();
			 if(rs.next()){
				 return true;
			 }	 		 
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

}
