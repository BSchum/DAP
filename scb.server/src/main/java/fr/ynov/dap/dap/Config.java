package fr.ynov.dap.dap;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import org.springframework.context.annotation.Configuration;

import com.google.api.services.calendar.CalendarScopes;
import com.google.api.services.gmail.GmailScopes;
import com.google.api.services.people.v1.PeopleServiceScopes;

import fr.ynov.dap.dap.helpers.AuthHelper;


@Configuration
public class Config{
    String credentialPath;
    String tokenPath;
    String appName;
    
    //TODO scb by Djer évite de mélanger des conf "developpeur" et des conf "administrateur systeme"

    public Config() {
    	try {
        	loadConfig();
    	}catch(Exception e) {
    		
    	}
    }
    
    private void loadConfig() throws IOException {
        String authConfigFile = "application.properties";
        InputStream authConfigStream = Config.class.getClassLoader().getResourceAsStream(authConfigFile);

        if (authConfigStream != null) {
          Properties authProps = new Properties();
          try {
            authProps.load(authConfigStream);
            credentialPath = authProps.getProperty("credentialPath");
            tokenPath = authProps.getProperty("tokenPath");
            appName = authProps.getProperty("appName");
            
          } finally {
            authConfigStream.close();
          }
        }
        else {
          throw new FileNotFoundException("Property file '" + authConfigFile + "' not found in the classpath.");
        }
      }
	public List<String> allScopes = new ArrayList<String>(Arrays.asList(GmailScopes.GMAIL_LABELS, CalendarScopes.CALENDAR_READONLY, PeopleServiceScopes.CONTACTS_READONLY));
	
	public String getoAuth2CallbackUrl() {
		return "/oAuth2CallBack";
	}
	
	
	public int sensibleDataLastChar = 0;
	public int sensibleDataFirstChar = 0;
	
	public String getCredentialPath() {
		return credentialPath;
	}
	public void setCredentialPath(String credentialPath) {
		this.credentialPath = credentialPath;
	}
	public String getTokenPath() {
		return tokenPath;
	}
	public void setTokenPath(String tokenPath) {
		this.tokenPath = tokenPath;
	}
	public String getAppName() {
		return appName;
	}
	public void setAppName(String appName) {
		this.appName = appName;
	}
	public List<String> getAllScopes() {
		return allScopes;
	}
	public void setAllScopes(List<String> allScopes) {
		this.allScopes = allScopes;
	}
	public int getSensibleDataLastChar() {
		return sensibleDataLastChar;
	}
	public void setSensibleDataLastChar(int sensibleDataLastChar) {
		this.sensibleDataLastChar = sensibleDataLastChar;
	}
	public int getSensibleDataFirstChar() {
		return sensibleDataFirstChar;
	}
	public void setSensibleDataFirstChar(int sensibleDataFirstChar) {
		this.sensibleDataFirstChar = sensibleDataFirstChar;
	}
	
	
	
}