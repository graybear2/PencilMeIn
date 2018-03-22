package pencilmein;

import java.util.ArrayList;
import java.util.HashMap;

import com.google.appengine.api.users.User;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Index;

import pencilmein.Student;

@Entity
public class Student {
    @Index User user;
    @Index Schedule schedule;
    @Index HashMap<User, Student> friends;
    @Index ArrayList<Student> requests;
    
    public Student(User u) {
        user = u;
        schedule = new Schedule();
        friends = new HashMap<User, Student>();
        requests = new ArrayList<Student>();
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

    public HashMap<User, Student> getFriends() {
        return friends;
    }

    // called when user accepts a friend request from friend
    public void addFriend(Student friend) {
        this.friends.put(friend.getUser(), friend);
    }
    
    public void removeFriend(Student friend) {
        this.friends.remove(friend.getUser());
    }

    public ArrayList<Student> getRequests() {
        return requests;
    }
    
    // called by user who's been added as a friend, i.e. when x friend requests y, need to get y Student object and then call this function with y.addRequest(x)
    public void addRequest(Student student) {
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
