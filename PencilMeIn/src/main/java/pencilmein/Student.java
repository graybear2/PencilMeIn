package pencilmein;

import static com.googlecode.objectify.ObjectifyService.ofy;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.io.ObjectOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

import com.google.appengine.api.users.User;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;
import pencilmein.Student;

@Entity
public class Student {
    @Id String id;
    @Index User user;
    @Index Schedule schedule;
    ArrayList<User> friends;
    ArrayList<User> requests;
    byte[] friendBytes;
    byte[] requestBytes;
    
    private Student() {}
    
    public Student(User u) {
        user = u;
        schedule = new Schedule();
        friends = new ArrayList<User>();
        requests = new ArrayList<User>();
        id = u.getEmail();
    }
    
    public void saveEntityNow() {
    		friendBytes = serialize(friends);
    		requestBytes = serialize(requests);
    		ofy().save().entity(this).now();
    }
    
    public static byte[] serialize(Object obj) {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ObjectOutputStream os;
		try {
			os = new ObjectOutputStream(out);
			os.writeObject(obj);
		} catch (IOException e) {
			System.out.println("serialization error");
		}
        
        return out.toByteArray();
    }
    
    @SuppressWarnings("unchecked")
	public static ArrayList<User> deserialize(byte[] data) {
        ByteArrayInputStream in = new ByteArrayInputStream(data);
        ObjectInputStream is;
		try {
			is = new ObjectInputStream(in);
			return (ArrayList<User>) is.readObject();
		} catch (Exception e1) {
			System.out.println("deserialization error");
			return null;
		}
    }
    
    public static Student getStudent(User user) {
        if(user == null) 
        		return null;
        
        Student student = ofy().load().type(Student.class).id(user.getEmail()).now();
        if(student == null)
        		return null;
        student.deserializeStudent();
        return student;
    }
    
    public static Student getStudent(String friend_email) {
    	Student student = ofy().load().type(Student.class).id(friend_email).now();
        if(student == null)
        		return null;
        student.deserializeStudent();
        return student;
    }
    
    public void deserializeStudent() {
    		friends = deserialize(friendBytes);
    		requests = deserialize(requestBytes);
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    // make fancier schedule editing later
    public void setSchedule(Schedule schedule) {
        this.schedule = schedule;
    }

    public ArrayList<User> getFriends() {
        return friends;
    }

    // called when user accepts a friend request from friend
    public void addFriend(User friend) {
        this.friends.add(friend);
    }
    
    public void removeFriend(User friend) {
        this.friends.remove(friend);
    }

    public ArrayList<User> getRequests() {
        return requests;
    }
    
    // called by user who's been added as a friend, i.e. when x friend requests y, need to get y Student object and then call this function with y.addRequest(x)
    public void addRequest(User student) {
        requests.add(student);
    }  

    public Schedule getSchedule() {
        return schedule;
    }
    
    public void addEvent(String name, int day, int shour, int smin, int ehour, int emin) {
        Event add = new Event(name, day, shour, smin, ehour, emin);
        schedule.addEvent(add);
    }
    
    // later, add ability to edit schedule
    // public void removeEvent()
    
}
