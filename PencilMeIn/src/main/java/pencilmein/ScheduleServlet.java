package pencilmein;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import com.googlecode.objectify.ObjectifyService;
import static com.googlecode.objectify.ObjectifyService.ofy;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ScheduleServlet extends HttpServlet {
    
    ArrayList<Day> days;
    
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
            String startString = req.getParameter("start");
            String endString = req.getParameter("end");
            
            
            //Convert to military times
            int day, shour, smin, ehour, emin;

            String[] startParts = startString.split(":");
            shour = Integer.parseInt(startParts[0]);
            smin = Integer.parseInt(startParts[1]);
            
            String[] endParts = endString.split(":");
            ehour = Integer.parseInt(endParts[0]);
            emin = Integer.parseInt(endParts[1]);
            
            
            if (startString.contains("pm") && (shour != 12)) {
                shour += 12;
            }
            else if (startString.contains("am") && shour == 12) {
                 shour = 0;
            }
            
            if (endString.contains("pm") && (ehour != 12)) {
                ehour += 12;
            }
            else if (endString.contains("am") && ehour == 12) {
                 ehour = 0;
            }
            
            //Read all selected days
            if(req.getParameter("sunday") != null) {
                days.add(Day.SUNDAY);
            }
            
            if(req.getParameter("monday") != null) {
                days.add(Day.MONDAY);
            }
            
            if(req.getParameter("tuesday") != null) {
                days.add(Day.TUESDAY);
            }
            
            if(req.getParameter("wednesday") != null) {
                days.add(Day.WEDNESDAY);
            }
            
            if(req.getParameter("thursday") != null) {
                days.add(Day.THURSDAY);
            }
            
            if(req.getParameter("friday") != null) {
                days.add(Day.FRIDAY);
            }
            
            if(req.getParameter("saturday") != null) {
                days.add(Day.SATURDAY);
            }
            
            System.out.println(name);
            System.out.println(startString);
            System.out.println(endString);
            
            for(Day d : days) {
                System.out.println(d.toString());
            }
 
            Event e = new Event(name, days, shour, smin, ehour, emin);
            
            Student s = ofy().load().type(Student.class).id(user.getEmail()).now();    
            s.addEvent(e);
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
