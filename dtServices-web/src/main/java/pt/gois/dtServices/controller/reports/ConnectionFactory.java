package pt.gois.dtServices.controller.reports;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionFactory {

	public Connection getConnectionFactory() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			
			return DriverManager.getConnection("jdbc:mysql://localhost/dt_services", "root", "");
			
			
		} catch(Exception e) {
			throw new RuntimeException(e);
		}
	} 
}
