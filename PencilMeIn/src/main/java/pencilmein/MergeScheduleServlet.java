package pencilmein;

import static com.googlecode.objectify.ObjectifyService.ofy;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import com.googlecode.objectify.ObjectifyService;

public class MergeScheduleServlet extends HttpServlet {
    
    ArrayList<Student> selectedFriends;
    
    static boolean DEBUG = true;
    
    public MergeScheduleServlet() {
        
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
        
        if(req.getParameter("merge") != null) {
            int numFriends = Integer.parseInt(req.getParameter("numFriends"));
            int numSelectedFriends = 0;
            
            if(DEBUG) {
                System.out.println("numFriends is " + numFriends);
            }
            
            ArrayList<Student> selectedFriends = new ArrayList<Student>();
            
            for(int i = 0; i < numFriends; i++) {
                if(req.getParameter(Integer.toString(i)) != null) {
                    //Adds student to list
                    selectedFriends.add(Student.getStudent(req.getParameter(Integer.toString(i))));
                    numSelectedFriends++;
                    
                    if(DEBUG) {
                        System.out.println(req.getParameter(Integer.toString(i)));
                    }
                }
            }
            //add myself to the list of selected friends
            selectedFriends.add(Student.getStudent(user));
            numSelectedFriends++;
            //sends the merged schedule back to the user side jsp
            req.setAttribute("mergedMap", Schedule.scheduleMerge(selectedFriends));
            req.setAttribute("numSelectedFriends", new Integer(numSelectedFriends));
            RequestDispatcher dispatcher = req.getRequestDispatcher("/home.jsp");
            
            try {
                dispatcher.forward(req, resp);
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (ServletException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        
        else {
            try {
                resp.sendRedirect("/home.jsp");
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
}
