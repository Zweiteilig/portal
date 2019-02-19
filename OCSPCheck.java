import java.util.*;
import java.lang.*;
import java.net.*;
import java.sql.*;
import java.io.*;

public class OCSPCheck
{
static String VERBOSITY = "TRUE";

Properties ocspProp = new Properties();

java.util.ArrayList ocspResponderList = null;

//-------------------------
// The "OCSPCheck" 
//	constructors
// OCSPCheck.okay.Thursday1120am.txt
// November 6th, 2018
//-------------------------
public OCSPCheck(){    
    cout("OCSPCheck 1.0");
    cout( new java.util.Date().toString() );
    this.ocspResponderList = loadConfiguredResponders( );
    if( this.ocspResponderList== null||this.ocspResponderList.size()==0)
    cout("OCSP response config is empty.");
    return;
}

private static void cout( String Msg ){
  System.out.println("["+Msg+"]");  
  return;
}

//-------------------------
// methods
//-------------------------
public String getWestRes( ){
    if( ocspProp != null && ocspProp.getProperty("WEST") != null) 
    cout(ocspProp.getProperty("WEST") );
    return ocspProp.getProperty("WEST") ;
}


public String getEastRes( ){
    if( ocspProp != null && ocspProp.getProperty("EAST") != null) 
    cout(ocspProp.getProperty("EAST") );
    return ocspProp.getProperty("EAST") ;
}


public String getCntrlRes( ){
    if( ocspProp != null && ocspProp.getProperty("CNTRL") != null) 
    cout(ocspProp.getProperty("CNTRL") );
    return ocspProp.getProperty("CNTRL") ;
}


private java.util.ArrayList loadConfiguredResponders( ){
    long nthin = -1;
    InputStream input = null;    
    java.util.ArrayList thisConfiguredList = new java.util.ArrayList( );

    	try {
		input = new FileInputStream("adminocsp.properties");

          if(input !=null && input.available()>nthin)
          {  
            // load a properties file
		    ocspProp.load(input);
            thisConfiguredList.add(ocspProp.getProperty("WEST"));
            thisConfiguredList.add(ocspProp.getProperty("EAST"));
            thisConfiguredList.add(ocspProp.getProperty("CNTRL"));          
            VERBOSITY = ocspProp.getProperty("VERBOSE").
                toString().trim();
          }
          else
          {
            return thisConfiguredList;
          }
        } catch (IOException ex) {
		    cout("There was an error reading adminocsp.properties. Check the config file.");
            System.exit(0);
	    } finally {
		    if (input != null) {
			try {
				input.close();
			    } catch (IOException e) {e.printStackTrace();}
		    }
	    }

        cout(thisConfiguredList.toString());

return thisConfiguredList;        
}


private int listConfiguredResponders( ){
java.util.ArrayList thisConfiguredList = null;
if(thisConfiguredList==null) return 0;
return thisConfiguredList.size();        
}


private int listTheseResponders( java.util.ArrayList these ){
    java.util.ArrayList thisList = null;
    if(thisList == null) return 0;
    return thisList.size();    
}


/**
 * Collect the detail and price for a given set of stocks.
 *
 * @param symbols the list of stock tickers
 */
 public static String getWelcomeResponse( java.lang.String responder ){
	String welcome = ""; 
	// The symbol (ticker 
	//	name) is the most
	//	important part of 
	//	the URL;
	if(responder == null || responder.equals(""))
	{
		welcome = "";
	    return welcome;
	}
	 
    String str = null;
	java.net.URL url = null;
    BufferedReader in = null;
    String successWelcome = "";	    
       
        try
        {
            url = new java.net.URL(responder);
            
            in = new BufferedReader(new InputStreamReader(url.openStream())	);
            
            while((str=in.readLine()) != null)
            {
	            welcome += str ;
                
                if(in.read() != -1)
                { 
                    welcome += "\n"; 
                }				
            }
			
			in.close();
		}
		catch (MalformedURLException mfue){    
        System.out.println( "\n\t<MalformedURLException>");
		}
		catch (IOException ioe)    {   
        System.out.println( "\nIOException: " + responder);
    }
    
    successWelcome = welcome;
    if(successWelcome.indexOf("Welcome") > -1) return "WELCOME";
    if(VERBOSITY.equals("TRUE")) cout("\n|DEBUG\n" +successWelcome);
    return successWelcome ;
 }

 
// -- --- -----
// Main driver:
// -- --- -----
public static void main (String[] cmd) throws java.lang.Exception{
		OCSPCheck oCSPCheck = new OCSPCheck( ); 
        java.util.ArrayList tickerz = new java.util.ArrayList( );	  
        System.out.println(OCSPCheck.getWelcomeResponse(oCSPCheck.getWestRes()) + " WEST");
        System.out.println(OCSPCheck.getWelcomeResponse(oCSPCheck.getEastRes()) + " EAST");
        System.out.println(OCSPCheck.getWelcomeResponse(oCSPCheck.getCntrlRes()) + " CENTRAL");
        
  return;
}

};


class ConsoleCommander
{
  private String[] consoleArgs = null;

  public ConsoleCommander(){
      System.out.println("[ConsoleCommander with no args]"); 
      return;
  }

  public ConsoleCommander(String[] cArgs){
      System.out.println("[ConsoleCommander "+cArgs.length+" args]"); 

      if(cArgs.length < 1) consoleArgs = new 
        String[cArgs.length];

        for ( int f = 0; f < cArgs.length; f++ )
        {
            System.out.println("["+cArgs[f]+"]");            
        }

      return;      
  }

   public java.util.List getArguments() {
       java.util.List argsList = null;

        //If there are command elements, then 
        //  initialize the list and add the 
        //  elements;
        if(consoleArgs.length>0) 
        argsList = new java.util.ArrayList();
        else return argsList;

        for ( int f = 0; f < argsList.size(); f++ )
        {
            argsList.add(consoleArgs[f]);
        }

       return argsList;
   }
};