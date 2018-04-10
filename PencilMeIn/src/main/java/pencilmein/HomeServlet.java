package pencilmein;

import com.googlecode.objectify.ObjectifyService;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HomeServlet extends HttpServlet {
    
    public HomeServlet() {
        
    }
    
    static {
        ObjectifyService.register(Student.class);
    }
    
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException{
    		resp.sendRedirect("/home.jsp");
    }
    
    public void doPost(HttpServletRequest req, HttpServletResponse resp) {
        
     
    }
}
