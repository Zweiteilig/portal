import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/*
 * Prepend header instruction to pages.
 * @author hwestmoreland (as itsadmin)
*/
public class XFrameFilter implements Filter 
{

  private String mode = "DENY";

  public XFrameFilter()
  {
      // Reference the SAP Logger, here;
      System.out.println( ".XFrameFilter." );
      //It would be nice to log a message.
      return;
  }


  public void init(FilterConfig filterConfig) throws ServletException 
  {
    String configMode = filterConfig.getInitParameter("mode");

    // Reference the SAP Logger, here;
    System.out.println( ".XFrameFilter. init configMode: " + 
      configMode );
      //It would be nice to log a message.
      
    if ( configMode != null ) {
        mode = configMode;
    }    

	  return;
  }


  public void destroy() 
  {
        return;
  }


  public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException 
  {
         HttpServletResponse res = (HttpServletResponse)response;
         res.addHeader("X-FRAME-OPTIONS", mode );   
         filterChain.doFilter(request, response);
	  return;
  }

};