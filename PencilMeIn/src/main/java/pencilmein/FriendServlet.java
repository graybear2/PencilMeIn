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
    
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        User user = UserServiceFactory.getUserService().getCurrentUser();  
        
        //Redirect user if they're not logged in
        if(user == null) {
            try {
                resp.sendRedirect("/index.jsp");
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        
        Student student = Student.getStudent(user);
        String friend_email = req.getParameter("email");
        Student friend = Student.getStudent(friend_email);
        friend.addRequest(student.getUser());
        friend.saveEntityNow();
        resp.sendRedirect("/friends.jsp");
    }
}
