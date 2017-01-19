package Services;

import java.sql.SQLException;
import java.util.Random;

import javax.jws.WebMethod;
import javax.jws.WebService;

import DataBase.DataBaseConnection;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;



import IServices.GenerateMesuresInterface;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;


@WebService
public class GenerateMesures extends Thread implements GenerateMesuresInterface{
	public int Cbegin;
	public int Cend;
	public int Tbegin;
	public int Tend;
	Data data = new Data();
	Connection connection;
	
	

	public void setCbegin(int cbegin) {
		Cbegin = cbegin;
	}
	public void setCend(int cend) {
		Cend = cend;
	}
	public void setTbegin(int tbegin) {
		Tbegin = tbegin;
	}
	public void setTend(int tend) {
		Tend = tend;
	}
	public void run(){
		Random randomC = new Random();
		Random randomT = new Random();
		int cmp = 0;
		//long start = System.currentTimeMillis();
		    
		    while( cmp < 30) {
		      GenerateDataCond(Cbegin ,Cend , randomC);
		      GenerateDataTemp(Tbegin ,Tend , randomT);
		      
		      DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
              LocalDateTime now = LocalDateTime.now();
              data.mesureDate = dtf.format(now);
		      
		      saveDataCond(data);
		      try {
		        // pause
		        Thread.sleep(500);
		      }
		      catch (InterruptedException ex) {}
		    cmp++;
		    }
	}
	@WebMethod
	
	public Data GenerateDataCond(int aStart, int aEnd, Random aRandom) {
		// TODO Auto-generated method stub
	    if (aStart > aEnd) {
	        throw new IllegalArgumentException("Start cannot exceed End.");
	      }
	      //get the range, casting to long to avoid overflow problems
	      long range = (long)aEnd - (long)aStart + 1;
	      // compute a fraction of the range, 0 <= frac < range
	      long fraction = (long)(range * aRandom.nextDouble());
	      data.condValue =  (int)(fraction + aStart);    
	     
	      log("Cond : " + data.condValue);
		return data;
	}
	
	public Data GenerateDataTemp(int aStart, int aEnd, Random aRandom) {
		// TODO Auto-generated method stub
		
	    if (aStart > aEnd) {
	        throw new IllegalArgumentException("Start cannot exceed End.");
	      }
	      //get the range, casting to long to avoid overflow problems
	      long range = (long)aEnd - (long)aStart + 1;
	      // compute a fraction of the range, 0 <= frac < range
	      long fraction = (long)(range * aRandom.nextDouble());
	      data.tempValue =  (int)(fraction + aStart);   
	      
	      log("Temp : " + data.tempValue);
	 return data;	
	}
	
	private static void log(String aMessage){
	    System.out.println(aMessage);
	  }
	
	
	public void saveDataCond(Data data) {
		// TODO Auto-generated method stub
		Crudes c = new Crudes();
		try{
			connection = DataBaseConnection.getConnection();
			Random randomGeneratedID = new Random();
			data.ID = randomGeneratedID.nextInt(1000);
			String sql = "INSERT INTO dataValues(mesureID,condValue,tempValue,mesureDate) values ( ?, ?, ?, ?)";

			PreparedStatement pst = connection.prepareStatement(sql);
			
			// to verify if the ID of the data exist in the DataBase
			while(c.findID(data.ID, "dataValues")){
				data.ID = randomGeneratedID.nextInt(1000);
				c.findID(data.ID, "dataValues");
			}
			
			pst.setInt(1, data.ID);
			pst.setInt(2, data.condValue);
			pst.setInt(3, data.tempValue);
			pst.setString(4, data.mesureDate);

			int res = pst.executeUpdate();

			if (res > 0) {
				System.out.println("");
			}
			
	
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	/*public Boolean findID(int ID , String tableName) {	
		try{
			//create a connexion with the database
			 connection= DataBaseConnection.getConnection();
			//prepare the sql body
			 String sql="SELECT * FROM ? WHERE condID = ? ";
			//prepare statement to the connexion with the database 
			 PreparedStatement pst = connection.prepareStatement(sql);
			//set into the statement the variable at the first position
			 pst.setString(1, tableName);
			//set into the statement the variable at the first position
			 pst.setInt(2, ID); 
			 
			 ResultSet rs = pst.executeQuery();
			 if(rs.next()){
				 return true;
			 }	 		 
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}*/
}
