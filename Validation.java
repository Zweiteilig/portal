import java.io.IOException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Validation extends HttpServlet
{

 /**
  * 
  */
 private static final long serialVersionUID = 1L; 

  public void init( ServletConfig config ) throws ServletException 
  {
    // Reference the SAP Logger, here;
    System.out.println( ".Validation." );
    //It would be nice to log a message.
  }

  public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
  {
    String user = request.getParameter("username");
    String pass = request.getParameter("password");
    if(user.equalsIgnoreCase("Howard")&& pass.equalsIgnoreCase("Hello"))
    {
      System.out.println("POST Login: " + user);
      response.sendRedirect("loginSuccess.jsp");
    }
    return; 
  }

};
