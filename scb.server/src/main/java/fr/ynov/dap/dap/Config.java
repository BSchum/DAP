package fr.ynov.dap.dap;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.context.annotation.Configuration;

import com.google.api.services.calendar.CalendarScopes;
import com.google.api.services.gmail.GmailScopes;
import com.google.api.services.people.v1.PeopleServiceScopes;


@Configuration
public class Config{
    String credentialPath = "/credentials.json";
    String tokenPath = "tokens";
    String appName = "Gmail API Java Quickstart";
    
    //TODO scb by Djer évite de mélanger des conf "developpeur" et des conf "administrateur systeme"

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