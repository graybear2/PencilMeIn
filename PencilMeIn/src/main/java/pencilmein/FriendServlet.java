package pencilmein;

import javax.servlet.http.*;

import java.io.IOException;
import java.util.Date;
import java.util.logging.Logger;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import com.googlecode.objectify.ObjectifyService;

import pencilmein.Student;

import static com.googlecode.objectify.ObjectifyService.ofy;


public class FriendServlet  extends HttpServlet{
	
	static{
		ObjectifyService.register(Student.class);
	}
	public void doPost(HttpServletRequest req, HttpServletResponse resp) 
		      throws IOException {
		
		UserService userService = UserServiceFactory.getUserService();
        User user = userService.getCurrentUser();
        
        //Redirect user if they're not logged in
	    if(user == null) {
	        resp.sendRedirect("/index.jsp");
	    }
	    
	    //Student s = ofy().load().type(Student.class).id(user);
        
	    String content = req.getParameter("content");
        
        String title = req.getParameter("title");
        Date date = new Date();

        //BlogPost blogpost = new BlogPost(user, title, content, blogName);
        //ofy().save().entity(blogpost).now();   // synchronous
        
        resp.sendRedirect("/home.jsp");

	}
}
