package db;

import java.sql.Connection;
import java.sql.DriverManager;

public class DB {
	public Connection connect() {
		Connection conn = null;
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			String connectionUrl = "jdbc:mysql://localhost:3306/test";
			String connectionUser = "root";
			String connectionPassword = "root";
			conn = DriverManager.getConnection(connectionUrl, connectionUser, connectionPassword);
			 if (conn == null) {
		            System.out.println("Connection cannot be established");
		        }
		        return conn;
		    } catch (Exception e) {
		        System.out.println(e);
		    }
		    return null;
		}
	}