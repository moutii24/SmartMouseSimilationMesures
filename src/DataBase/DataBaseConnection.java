package DataBase;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;


public class DataBaseConnection {
	
	private static Connection connection = null;
	
	public static Connection getConnection() {

		if (connection != null)
			return connection;
		else {
			try {
				String driver = "com.mysql.jdbc.Driver";
				String url = "jdbc:mysql://localhost:3306/datamouse";
				String user = "root";
				String password = "admin";
				Properties prop = new Properties();
				InputStream inputStream = DataBaseConnection.class.getClassLoader()
						.getResourceAsStream(
								"/config.propertiesprop.load(inputStream");
				Class.forName(driver);
				connection = DriverManager.getConnection(url, user, password);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return connection;
		}

	}

}
