package pencilmein;

import java.util.ArrayList;
import java.util.HashMap;

import com.google.appengine.api.users.User;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;

import pencilmein.Student;

@Entity
public class Student {
    @Id User user;
    @Index Schedule schedule;
    @Index HashMap<User, Student> friends;
    @Index ArrayList<Student> requests;
    
    public Student(User u, Schedule s) {
        user = u;
        schedule = s;
        friends = new HashMap<User, Student>();
        requests = new ArrayList<Student>();
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Schedule getSchedule() {
        return schedule;
    }

    // make fancier schedule editing later
    public void setSchedule(Schedule schedule) {
        this.schedule = schedule;
    }

    public HashMap<User, Student> getFriends() {
        return friends;
    }

    public void addFriendRequest(Student friend) {
        this.friends.put(friend.getUser(), friend);
    }
    
    public void removeFriend(Student friend) {
        this.friends.remove(friend.getUser());
    }

    public ArrayList<Student> getRequests() {
        return requests;
    }
    
    public void addRequest(Student student) {
        requests.add(student);
    }
    
}
