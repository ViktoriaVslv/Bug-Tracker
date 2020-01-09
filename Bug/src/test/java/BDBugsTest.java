import java.util.ArrayList;

import org.junit.*;

import junit.framework.TestCase;
import model.BDBugs;

public class BDBugsTest extends TestCase {

	private BDBugs bugs; 
	private ArrayList <String> bugTest;
	
	@Before
	public void setUp() {
		bugs = new BDBugs();
		bugTest= new ArrayList<String>();
		bugTest.add("Open");
		bugTest.add("test");
		bugTest.add("vika");
		bugTest.add("vika");
		bugTest.add("08.01.2020 17:31");
		bugTest.add("");
		bugTest.add("Bug");
		bugTest.add("");
	}
	@After
	public void after()throws Exception {
		bugs.close();
	}
	
	@Test
	public void testGetBugs() throws Exception {
		ArrayList bug= bugs.getAll("test");
		for(int i=0; i<bug.size();i++) {
			Assert.assertEquals((String)bug.get(i), bugTest.get(i));
		}
	 }

	@Test
	public void testFindNoExist() throws Exception {
		String exist = "xxx";
		assertFalse(bugs.find(exist));
	}
	
	@Test
	public void testFindExist() throws Exception {
		String exist = "test";
		assertTrue(bugs.find(exist));
	}
	@Test
	public void testCheckStatus() throws Exception {
		
		assertTrue(bugs.checkSt("test", "test"));
	}

	 @Test
	 public void testGetChild() throws Exception {
		 assertSame(0,bugs.getChildren("p1").size());
	 }
}