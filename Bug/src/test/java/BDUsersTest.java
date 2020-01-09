import org.junit.*;

import junit.framework.TestCase;
import model.BDUsers;
import java.util.ArrayList;

public class BDUsersTest extends TestCase {

	private  BDUsers users;
	private ArrayList <String> allUsers;
	
	@Before
	public void setUp() {
		users = new BDUsers();
		allUsers= new ArrayList<String>();
		allUsers.add("vika");
		allUsers.add("v");
		allUsers.add("viktoria");
		allUsers.add("vik");
		allUsers.add("1");
		allUsers.add("polina");
		allUsers.add("Viktoria");
		}
	@After
	public  void after()throws Exception {
		users.close();
	}

	@Test
	public void testFindExist() throws Exception {
		String exist = "vika";
		assertTrue(users.find(exist));
	}
	
	
	@Test
	public void testLogin() throws Exception {
		String log = "vv+";
		String pass = "v";
		assertFalse(users.auth(log, pass));
	}
	@Test
	public void testGetUsers() throws Exception {
		ArrayList us= users.getAllUsers();
		for(int i=0; i<us.size();i++) {
			Assert.assertEquals((String)us.get(i), allUsers.get(i));
		}
	 }

}