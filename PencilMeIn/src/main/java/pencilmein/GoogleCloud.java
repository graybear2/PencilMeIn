package pencilmein;

import static com.googlecode.objectify.ObjectifyService.ofy;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import com.google.appengine.api.users.User;

public final class GoogleCloud implements CloudProxy {
    
    private GoogleCloud() {
        
    }
    
    public static synchronized void saveStudent(Student s) {
        ofy().save().entity(s).now();
    }
    
    public static synchronized Student getStudent(String friend_email) {
        Student student = (Student) ofy().load().type(Student.class).id(friend_email).now();
        if(student == null)
                return null;
        
        return student;
    }
    
    public static synchronized Student getStudent(User user) {
        if(user == null) 
                return null;
        
        Student student = (Student) ofy().load().type(Student.class).id(user.getEmail()).now();
        return student;
    }
}
