package pencilmein;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import com.googlecode.objectify.ObjectifyService;
import static com.googlecode.objectify.ObjectifyService.ofy;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FriendServlet extends HttpServlet {
    
    public FriendServlet() {
        
    }
    
    static {
        ObjectifyService.register(Student.class);
    }
    
    public void doPost(HttpServletRequest req, HttpServletResponse resp) {
        UserService userService = UserServiceFactory.getUserService();
        User user = userService.getCurrentUser();  
        
        //Redirect user if they're not logged in
        if(user == null) {
            try {
                resp.sendRedirect("/index.jsp");
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        
        Student student = ofy().load().type(Student.class).id(user.getEmail()).now();
        String friend_email = req.getParameter("email");
        Student friend = ofy().load().type(Student.class).id(friend_email).now();
        friend.addRequest(student.getUser());
        
         
    }
}
