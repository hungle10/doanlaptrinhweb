package cuoiki.ltweb.configs;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.microsoft.sqlserver.jdbc.SQLServerDriver;

public class DBConnectSQLServer {
	public static Connection getConnection() {
		Connection c = null;
		try {
			SQLServerDriver driver = new SQLServerDriver();

			DriverManager.registerDriver(driver);

			// String url =
			// "jdbc:sqlserver://LEHUNG\\THAIHUNG;databaseName=ltwebct5;user=testLogin;password=123456;encrypt=true;trustServerCertificate=true;";
			// String url =
			// "jdbc:sqlserver://192.168.2.8:1433;databaseName=ltwebct5;user=sa;password=Abc@123456789;encrypt=true;trustServerCertificate=true;";
			//String url = "jdbc:sqlserver://LEHUNG\\THAIHUNG;databaseName=uteshop;integratedSecurity=true;encrypt=true;trustServerCertificate=true;";
			
			String url = "jdbc:sqlserver://localhost:1433;databaseName=uteshop;integratedSecurity=true;encrypt=true;trustServerCertificate=true;";

			c = DriverManager.getConnection(url);

			return c;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static void closeConnection(Connection c) {
		try {
			if (c != null)
				c.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void printInfo(Connection c) {
		try {
			if (c != null) {
				System.out.print(c.getMetaData().toString());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		DBConnectSQLServer.printInfo(getConnection());
	}
}
