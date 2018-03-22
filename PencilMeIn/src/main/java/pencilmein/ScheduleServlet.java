package pencilmein;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import com.googlecode.objectify.ObjectifyService;
import static com.googlecode.objectify.ObjectifyService.ofy;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ScheduleServlet extends HttpServlet {
    
    public ScheduleServlet() {
        
    }
    
    static {
        ObjectifyService.register(Student.class);
    }
    
    public void doPost(HttpServletRequest req, HttpServletResponse resp) {
        UserService userService = UserServiceFactory.getUserService();
        User user = userService.getCurrentUser();  
        
        //Redirect user if they're not logged in
        if(user == null) {
            resp.sendRedirect("/index.jsp");
        }
        
        //Add a new event
        if(req.getParameter("add") != null) {
            String name = req.getParameter("name");
            String day = req.getParameter("day");
            String start = req.getParameter("start");
            String end = req.getParameter("end");
            
            Student s = ofy().load().type(Student.class).id(user.getEmail()).now();
            
            //TODO: Parse stuff into correct type for Student.addEvent();
            
            s.addEvent(name, day, start, end);
            
        }
        
        //Remove an event
        else if(req.getParameter("remove") != null) {
            //TODO: Implement event removing
        }        
    }
}
