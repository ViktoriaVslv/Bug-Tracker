package model;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;


public class ConnectionBD {
	private Statement stmt;
	private ResultSet rs;
	private Connection con;
	private static final String JBDC_Driver = "org.postgresql.Driver";
	private static final String url = "jdbc:postgresql://localhost:5432/";
	private static final String login = "postgres";
	private static final String password = "postgres";
	
	public ConnectionBD() {
		try {
            Class.forName(JBDC_Driver);
            con = DriverManager.getConnection(url, login, password);
            stmt = con.createStatement();
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public ResultSet connectUsers() throws Exception {
		rs = stmt.executeQuery("SELECT * FROM Users");
		return rs;
	}
	
	public Statement getStatement() {
		return stmt;
	}
	
	public ResultSet connectBugs() throws Exception {
		rs = stmt.executeQuery("SELECT * FROM Bugs");
		return rs;
	}
	
	public boolean checkConnection() throws Exception{
		if (!con.isClosed() & !stmt.isClosed())
			return true;
		else 
			return false;
    }
	
	public void close() {
		try {
			stmt.close();
			con.close();
        }	  
		catch (Exception e) {
			e.printStackTrace();
		}
	}

}
