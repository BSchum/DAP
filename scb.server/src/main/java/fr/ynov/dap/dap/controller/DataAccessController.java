package fr.ynov.dap.dap.controller;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import fr.ynov.dap.dap.models.CustomEvent;
import fr.ynov.dap.dap.services.google.CalendarService;
import fr.ynov.dap.dap.services.google.GmailService;
import fr.ynov.dap.dap.services.google.PeopleServices;

@RestController
//TODO scb by Djer Bonne idée l'unique Controller (vu le peu de contenu) mais préfixe des MappingRequest ! 
public class DataAccessController{
	
	@Autowired
	private GmailService gmailService;
	
	@Autowired
	private CalendarService calendarService;
	
	@Autowired
	private PeopleServices peopleService;
	/**
	 * 
	 * 
	 * @param userid
	 * @return Number of unread messages
	 * @throws IOException
	 * @throws GeneralSecurityException
	 */
	@RequestMapping("/nbUnread")
	public String GetNbReadmessage(@RequestParam("userKey") String userid) throws IOException, GeneralSecurityException {
		Integer nbUnreadEmails = gmailService.getNbUnreadEmails(userid);
		if(nbUnreadEmails.equals(null)) {
			return "Fail";
		}
		return nbUnreadEmails.toString();
	}
	
	/**
	 * 
	 * @param userid
	 * @return next event info
	 * @throws IOException
	 * @throws GeneralSecurityException
	 */
	@RequestMapping("/event")
	public List<CustomEvent> GetNextEvent(@RequestParam("userKey") String userid) throws IOException, GeneralSecurityException {
		List<CustomEvent> e = calendarService.getNextEvent(userid);
		return e;
	}
	
	/**
	 * 
	 * @return Homepage
	 */
	@RequestMapping("/")
	public String hello() {
		return "Welcome to Data Access Project!";
	}
	
	/**
	 * 
	 * @param userid
	 * @return Return contact numbers
	 * @throws IOException
	 * @throws GeneralSecurityException
	 */
	@RequestMapping("/nbPeople")
	public String GetNbPeople(@RequestParam("userKey") String userid) throws IOException, GeneralSecurityException {
		Integer i = peopleService.contactNumber(userid);
		return i.toString();
	}
}