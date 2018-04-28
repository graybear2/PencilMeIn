package pencilmein;

import org.junit.Test;
import static org.junit.Assert.*;
import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import com.googlecode.objectify.ObjectifyService;
import static com.google.appengine.api.datastore.FetchOptions.Builder.withLimit;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

import java.util.ArrayList;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.tools.development.testing.LocalDatastoreServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalServiceTestHelper;
import org.junit.After;
import org.junit.Before;
import pencilmein.Student;

import static com.googlecode.objectify.ObjectifyService.ofy;

public class Testing {
	private final LocalServiceTestHelper helper =
	new LocalServiceTestHelper(new LocalDatastoreServiceTestConfig());
	
	@Before
	public void setUp() {
	helper.setUp();
	ObjectifyService.register(Student.class);
	}
	
	@After
	public void tearDown() {
	helper.tearDown();
	}
	
	// Run this test twice to prove we're not leaking any state across tests.
	private void testDatastore() {
		DatastoreService ds = DatastoreServiceFactory.getDatastoreService();
		assertEquals(0, ds.prepare(new Query("yam")).countEntities(withLimit(10)));
		ds.put(new Entity("yam"));
		ds.put(new Entity("yam"));
		assertEquals(2, ds.prepare(new Query("yam")).countEntities(withLimit(10)));
	}
	
	@Test
	public void testInsert1() {
		testDatastore();
	}
	
	@Test
	public void testInsert2() {
		testDatastore();
	}
	
	@Test
	public void testUserLogIn(){
		DatastoreService ds = DatastoreServiceFactory.getDatastoreService();
        UserService userService = UserServiceFactory.getUserService();
		
        User user = new User("kevinbrill11@gmail.com", "gmail.com");
		Student s = new Student(user);
		s.save();
		
		assertSame(s, Student.getStudent(user));
	}
	
	@Test
	public void testFriendRequest1(){
		DatastoreService ds = DatastoreServiceFactory.getDatastoreService();
		UserService userService = UserServiceFactory.getUserService();
		
		User u1 = new User("kevinbrill11@gmail.com", "gmail.com");
		User u2 = new User("anji3.t@gmail.com", "gmail.com");
		Student s1 = new Student(u1);
		Student s2 = new Student(u2);
		
		s1.addFriend(s2.getUser());
		
		assertEquals(s2.getUser().getEmail(), s1.getFriends().get(0).toString());
	}
	
	@Test
	public void testFriendRequest2(){
		DatastoreService ds = DatastoreServiceFactory.getDatastoreService();
		UserService userService = UserServiceFactory.getUserService();
		
		User u1 = new User("kevinbrill11@gmail.com", "gmail.com");
		User u2 = new User("anji3.t@gmail.com", "gmail.com");
		Student s1 = new Student(u1);
		Student s2 = new Student(u2);
		
		s2.addFriend(s1.getUser());
		
		assertEquals(s1.getUser().getEmail(), s2.getFriends().get(0).toString());
	}
	
	@Test
	public void testPendingFriend1(){
		DatastoreService ds = DatastoreServiceFactory.getDatastoreService();
		UserService userService = UserServiceFactory.getUserService();
		
		User u1 = new User("kevinbrill11@gmail.com", "gmail.com");
		User u2 = new User("anji3.t@gmail.com", "gmail.com");
		Student s1 = new Student(u1);
		Student s2 = new Student(u2);
		
		s1.addRequest(s2.getUser());
		
		assertEquals(s2.getUser().getEmail(), s1.getRequests().get(0).toString());
		
	}
	
	@Test
	public void testPendingFriend2(){
		DatastoreService ds = DatastoreServiceFactory.getDatastoreService();
		UserService userService = UserServiceFactory.getUserService();
		
		User u1 = new User("kevinbrill11@gmail.com", "gmail.com");
		User u2 = new User("anji3.t@gmail.com", "gmail.com");
		Student s1 = new Student(u1);
		Student s2 = new Student(u2);
		
		s2.addRequest(s1.getUser());
		
		assertEquals(s1.getUser().getEmail(), s2.getRequests().get(0).toString());
	}
	
	@Test
	public void testAddDeleteFriend(){
		User u1 = new User("kevinbrill11@gmail.com", "gmail.com");
		User u2 = new User("anji3.t@gmail.com", "gmail.com");
		Student s1 = new Student(u1);
		Student s2 = new Student(u2);
		
		s2.addRequest(s1.getUser());
		
		s2.removeRequest(s1.getUser());
		
		assertEquals(0, s2.getRequests().size());
	}
	
	@Test
	public void testEventInput(){
		User u1 = new User("kevinbrill11@gmail.com", "gmail.com");
		Student s1 = new Student(u1);
		ArrayList<Day> days = new ArrayList<Day>();
		days.add(Day.TUESDAY);
		days.add(Day.THURSDAY);
		
		Event e1 = new Event("test", days, 7, 0, 8, 15);
		Event e2 = new Event("alsoTest", days, 13, 15, 15, 0);
		s1.addEvent(e1);
		s1.addEvent(e2);
		
		assertEquals(2, s1.getSchedule().getSize());
	}
	
	@Test
	public void testEventDelete(){
		User u1 = new User("kevinbrill11@gmail.com", "gmail.com");
		Student s1 = new Student(u1);
		ArrayList<Day> days = new ArrayList<Day>();
		days.add(Day.TUESDAY);
		days.add(Day.THURSDAY);
		
		Event e1 = new Event("test", days, 7, 0, 8, 15);
		Event e2 = new Event("alsoTest", days, 13, 15, 15, 0);
		s1.addEvent(e1);
		s1.addEvent(e2);
		
		s1.removeEvent(e1);
		
		assertEquals(1, s1.getSchedule().getSize());
	}
	
	
}


