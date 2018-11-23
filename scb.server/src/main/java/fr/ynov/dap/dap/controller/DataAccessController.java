package fr.ynov.dap.dap.controller;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.google.api.client.auth.oauth2.StoredCredential;

import fr.ynov.dap.dap.models.CustomEvent;
import fr.ynov.dap.dap.services.google.CalendarService;
import fr.ynov.dap.dap.services.google.GmailService;
import fr.ynov.dap.dap.services.google.PeopleServices;

@Controller
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
	@ResponseBody
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
	@ResponseBody
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
		return "welcome";
	}
	
	/**
	 * 
	 * @param userid
	 * @return Return contact numbers
	 * @throws IOException
	 * @throws GeneralSecurityException
	 */
	@RequestMapping("/nbPeople")
	@ResponseBody
	public String GetNbPeople(@RequestParam("userKey") String userid) throws IOException, GeneralSecurityException {
		Integer i = peopleService.contactNumber(userid);
		return i.toString();
	}
	
	@RequestMapping("/credentials")
	public ModelAndView GetAllCredentials() throws IOException, GeneralSecurityException {
		HashMap<String, StoredCredential> mapStoredCred = peopleService.GetCredentialsAsMap();
		ModelAndView mv = new ModelAndView("credentials");
		mv.addObject("credentials", mapStoredCred);
		return mv;
	}
	
}