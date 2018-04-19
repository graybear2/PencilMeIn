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
            try {
                resp.sendRedirect("/index.jsp");
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        
        //Add a new event
        if(req.getParameter("add") != null) {
            String name = req.getParameter("name");
            String dayString = req.getParameter("day");
            String startString = req.getParameter("start");
            String endString = req.getParameter("end");
            
            int day, shour, smin, ehour, emin;
            
            //TODO: Parse stuff into correct type for Student.addEvent();
            dayString.toLowerCase();
            
            switch(dayString) {
                case "monday":
                    day = 1;
                    break;
                case "tuesday":
                    day = 2;
                    break;
                case "wednesday":
                    day = 3;
                    break;
                case "thursdday":
                    day = 4;
                    break;
                case "friday":
                    day = 5;
                    break;
                case "saturday":
                    day = 6;
                    break;
                case "sunday":
                    day = 7;
                    break;
                default:
                    day = -1;
                    //throw new IllegalArgumentException("Invalid day of the week: " + dayOfWeekArg);
            }
            
            String[] startParts = startString.split(":");
            shour = Integer.parseInt(startParts[0]);
            smin = Integer.parseInt(startParts[1]);
            
            String[] endParts = endString.split(":");
            ehour = Integer.parseInt(endParts[0]);
            emin = Integer.parseInt(endParts[1]);
            
            Student s = ofy().load().type(Student.class).id(user.getEmail()).now();    
            s.addEvent(name, day, shour, smin, ehour, emin);
        }
        
        //Remove an event
        else if(req.getParameter("remove") != null) {
            //TODO: Implement event removing
        }
        
        try {
            resp.sendRedirect("/schedinput.jsp");
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
