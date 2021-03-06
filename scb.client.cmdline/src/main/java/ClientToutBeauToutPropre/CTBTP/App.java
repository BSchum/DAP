package ClientToutBeauToutPropre.CTBTP;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URL;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws IOException
    {	
    	if(args.length > 3) {
    		System.out.println("Il ne peux y avoir plus de 3 parametre : *action* *userKey* ou *action* *accountName* *userKey*");
    		return;
    	}
    	
        if(args[0].equals("view")) {
        	URL url = new URL("http://localhost:8080/mail/unread?userKey="+args[1]);
        	String r = HttpHelper.ProcessGet(url);
        	System.out.println("Nombre de mail non lu:"+r);
        }else if(args[0].equals("add")) {
        	URI uri = URI.create("http://localhost:8080/account/add/"+args[1]+"?userKey="+args[2]);
        	Desktop.getDesktop().browse(uri);
        }else if(args[0].equals("contacts")) {
        	URL url = new URL("http://localhost:8080/contact/nbPeople?userKey="+args[1]);
        	System.out.println("Nombre de contacts lié a votre compte google"+HttpHelper.ProcessGet(url));
        }else if(args[0].equals("event")) {
        	URL url = new URL("http://localhost:8080/event/nextevent?userKey="+args[1]);
        	System.out.println(HttpHelper.ProcessGet(url));
        }else if(args[0].equals("addGAcc")){
        	if(args[3] == null) {
        		System.out.println("addGAcc prend comme parametre : *action* *accountName* *userKey*");
        		return; 
        	}
        	URL url = new URL("http://localhost:8080/user/add/"+args[1]+"?userKey="+args[3]);
        	System.out.println(HttpHelper.ProcessGet(url));
        }
        else {
        	URL url = new URL("http://localhost:8080/mail/unread?userKey="+args[0]);
        	String r = HttpHelper.ProcessGet(url);
        	System.out.println("Nombre de mail non lu:"+r);
        }
    }
    
}
