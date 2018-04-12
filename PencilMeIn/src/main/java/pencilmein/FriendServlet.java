package pencilmein;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import com.googlecode.objectify.ObjectifyService;
import static com.googlecode.objectify.ObjectifyService.ofy;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FriendServlet extends HttpServlet {
	HttpServletRequest req;
	HttpServletResponse resp;
	
    public FriendServlet() {
        
    }
    
    static {
        ObjectifyService.register(Student.class);
    }
    
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
    	resp.sendRedirect("/friends.jsp");
    }
    
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
    	this.req = req;
    	this.resp = resp;
    	User user = UserServiceFactory.getUserService().getCurrentUser();  
        
        //Redirect user if they're not logged in
        if(user == null) {
            try {
                resp.sendRedirect("/index.jsp");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    	
    	if(req.getParameter("email") != null)
    		requestFriend(user);
    	else if(req.getParameter("accept") != null)
    		acceptRequest(Student.getStudent(user), Student.getStudent(req.getParameter("accept")));
    	else if(req.getParameter("decline") != null)
    		declineRequest(Student.getStudent(user), Student.getStudent(req.getParameter("decline")));
        
        try {
			req.getRequestDispatcher("/friends.jsp").forward(req, resp);
		} catch (ServletException e) {
			System.out.println("FriendServlet exception");
		}    	
    }
    
    public void requestFriend(User user) throws IOException{        
        String friend_email = req.getParameter("email");
        Student friend = Student.getStudent(friend_email);
        if(friend == null) {
        	req.setAttribute("message", friend_email + " is not registered on Pencil Me In");
        }
        else if(friend_email.equals(user.getEmail())) {
        	req.setAttribute("message", "You're automatically friends with yourself!");        	
        }
        else if(friend.getRequests().contains(user)) {
        	req.setAttribute("message", friend.getUser().getNickname() + " has already received your request");
        }
        else if(friend.getFriends().contains(user)) {
        	req.setAttribute("message", "You are already friends with " + friend.getUser().getNickname());
        }
        else{
        	Student student = Student.getStudent(user);
        	if(student.getRequests().contains(friend.getUser())){
        		acceptRequest(student, friend);
        	}
	        else {
		        friend.addRequest(user);
		        friend.save();
	        	req.setAttribute("message", "Request to " + friend.getUser().getNickname() + " sent successfully");
	        }
        }
    }
    
    public void acceptRequest(Student acc, Student ask) {
    	acc.removeRequest(ask.getUser());
    	acc.addFriend(ask.getUser());
    	ask.addFriend(acc.getUser());
    	
    	acc.save();
    	ask.save();
    }
    
    public void declineRequest(Student dec, Student ask) {
    	dec.removeRequest(ask.getUser());
    	dec.save();
    }
}
