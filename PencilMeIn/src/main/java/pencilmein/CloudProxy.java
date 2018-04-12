package pencilmein;

import static com.googlecode.objectify.ObjectifyService.ofy;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import com.google.appengine.api.users.User;

public final class CloudProxy {
    
    private CloudProxy() {
        
    }
    
    public static synchronized void saveStudent(Student s) {
        s.friendBytes = serialize(s.friends);
        s.requestBytes = serialize(s.requests);
        ofy().save().entity(s).now();
    }
    
    private static synchronized byte[] serialize(Object obj) {
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
    
    public static synchronized Student getStudent(String friend_email) {
        Student student = ofy().load().type(Student.class).id(friend_email).now();
        if(student == null)
                return null;
        deserializeStudent(student);
        return student;
    }
    
    public static synchronized Student getStudent(User user) {
        if(user == null) 
                return null;
        
        Student student = ofy().load().type(Student.class).id(user.getEmail()).now();
        if(student == null)
                return null;
        deserializeStudent(student);
        return student;
    }
    
    private static synchronized void deserializeStudent(Student s) {
            s.friends = deserialize(s.friendBytes);
            s.requests = deserialize(s.requestBytes);
    }
    
    @SuppressWarnings("unchecked")
    private static synchronized ArrayList<User> deserialize(byte[] data) {
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
}
