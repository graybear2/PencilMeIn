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
import com.google.appengine.api.users.UserServiceFactory;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;
import com.googlecode.objectify.annotation.Serialize;

import pencilmein.Student;

@Entity
public class Student {
    @Id String id;
    @Index User user;
    @Index Schedule schedule;
    @Serialize ArrayList<User> friends;
    @Serialize ArrayList<User> requests;
    
    String name;
    
    public Student(String name) {
        schedule = new Schedule();
        this.name = name;
    }
    
    public Student(User u) {
        user = u;
        schedule = new Schedule();
        friends = new ArrayList<User>();
        requests = new ArrayList<User>();
        id = u.getEmail();
    }
    
    public static Student createStudent() {
    	return new Student(UserServiceFactory.getUserService().getCurrentUser());
    }
    
    public void save() {
        GoogleCloud.saveStudent(this);
    }
    
    public static Student getStudent(User user) {
        return GoogleCloud.getStudent(user);
    }
    
    public static Student getStudent(String friend_email) {
        return GoogleCloud.getStudent(friend_email);
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
    
    public void removeRequest(User student) {
    	requests.remove(student);
    }

    public Schedule getSchedule() {
        return schedule;
    }
    
    public void addEvent(Event e) {
        schedule.addEvent(e);
        GoogleCloud.saveStudent(this);
    }
    
    public void removeEvent(int eventId) {
    	if(eventId == -1)
    		System.out.println("TRYING TO REMOVE EVENT I COULDN'T FIND");
    	schedule.removeEvent(schedule.getEvent(eventId));
    	GoogleCloud.saveStudent(this);
    }
    
    public void overwriteEvent(Event e, int eventId) {
    	schedule.overwriteEvent(e, eventId);
    	GoogleCloud.saveStudent(this);
    }
    
    // later, add ability to edit schedule
    // public void removeEvent()
    
}
