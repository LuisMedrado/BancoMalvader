package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

    private static final String URL = "jdbc:mysql://localhost:3306/banco_malvader";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    public static Connection conectar() throws SQLException {
	return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public static void desconectar(Connection connection) {
	try {
	    if (connection != null)
		connection.close();
	} catch (SQLException e) {
	    e.printStackTrace();
	}
    }

}
