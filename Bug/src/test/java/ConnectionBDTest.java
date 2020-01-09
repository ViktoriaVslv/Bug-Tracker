import static org.junit.Assert.assertTrue;

import java.sql.ResultSet;
import java.sql.Statement;

import org.junit.*;

import junit.framework.TestCase;
import model.ConnectionBD;

public class ConnectionBDTest {
	private static ConnectionBD conn;
	
	
	@BeforeClass
	public static void setUp() {
		conn = new ConnectionBD();
	}
	@AfterClass
	public static void after()throws Exception {
		conn.close();
	}
	
	@Test
	public void testCheckConn() throws Exception{
		assertTrue(conn.checkConnection());
	}

}
