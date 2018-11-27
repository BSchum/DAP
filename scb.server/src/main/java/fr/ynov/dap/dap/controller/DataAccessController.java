package fr.ynov.dap.dap.controller;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.api.client.auth.oauth2.StoredCredential;

import fr.ynov.dap.dap.data.OutlookAccount;
import fr.ynov.dap.dap.data.OutlookAccountRepository;
import fr.ynov.dap.dap.helpers.AuthHelper;
import fr.ynov.dap.dap.models.Contact;
import fr.ynov.dap.dap.models.CustomEvent;
import fr.ynov.dap.dap.models.Event;
import fr.ynov.dap.dap.models.Message;
import fr.ynov.dap.dap.models.PagedResult;
import fr.ynov.dap.dap.models.TokenResponse;
import fr.ynov.dap.dap.services.google.CalendarService;
import fr.ynov.dap.dap.services.google.GmailService;
import fr.ynov.dap.dap.services.google.PeopleServices;
import fr.ynov.dap.dap.services.microsoft.OutlookService;
import fr.ynov.dap.dap.services.microsoft.OutlookServiceFactory;
import fr.ynov.dap.dap.services.microsoft.OutlookCalendarService;
import fr.ynov.dap.dap.services.microsoft.OutlookContactService;
import fr.ynov.dap.dap.services.microsoft.OutlookMailService;

@Controller
//TODO scb by Djer Bonne idée l'unique Controller (vu le peu de contenu) mais préfixe des MappingRequest ! 
public class DataAccessController{
	@Autowired
	private GmailService gmailService;
	
	@Autowired
	private CalendarService calendarService;
	
	@Autowired
	private PeopleServices peopleService;
	
	@Autowired 
	private OutlookMailService outlookMailService;
	
	@Autowired 
	private OutlookCalendarService outlookCalendarService;
	
	
	@Autowired 
	private OutlookContactService outlookContactService;
	
	
	@Autowired 
	private OutlookAccountRepository outlookRepo;
	/**
	 * 
	 * 
	 * @param userid
	 * @return Number of unread messages
	 * @throws IOException
	 * @throws GeneralSecurityException
	 */
	@RequestMapping("/mail/unread")
	@ResponseBody
	public String GetNbReadmessage(@RequestParam("userKey") String userid) throws IOException, GeneralSecurityException {
		Integer gmailUnreadMails = gmailService.getNbUnreadEmails(userid);
		Integer outlookUnreadMails = outlookMailService.getNbUnreadEmails(userid);
		if(gmailUnreadMails.equals(null)) {
			return "Fail";
		}
		Integer total = gmailUnreadMails + outlookUnreadMails;
		return total.toString();
	}
	
	/**
	 * 
	 * @param userid
	 * @return next event info
	 * @throws IOException
	 * @throws GeneralSecurityException
	 */
	@RequestMapping("/event/nextevent")
	@ResponseBody
	public CustomEvent GetNextEvent(@RequestParam("userKey") String userid) throws IOException, GeneralSecurityException {
		CustomEvent e = calendarService.getNextEvent(userid);
		CustomEvent outlookEvent = outlookCalendarService.GetNextEvent(userid);
		if(outlookEvent == null && e != null) {
			return e;
		}
		else if(e == null && outlookEvent != null) {
			return outlookEvent;
		}
		
		if(outlookEvent.getStart().before(e.getStart())) {
			return outlookEvent;
		}
		else {
			return e;	
		}
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
	@RequestMapping("/contact/nbPeople")
	@ResponseBody
	public String GetNbPeople(@RequestParam("userKey") String userid) throws IOException, GeneralSecurityException {
		Integer contactNumberGoogle = peopleService.contactNumber(userid);
		Integer contactNumberOutlook = outlookContactService.getContactNumber(userid);
		Integer result = contactNumberGoogle + contactNumberOutlook;
		return result.toString();
	}
	
	@RequestMapping("/admin/credentials")
	public ModelAndView GetAllCredentials() throws IOException, GeneralSecurityException {
		HashMap<String, StoredCredential> mapStoredCred = peopleService.GetCredentialsAsMap();
		ModelAndView mv = new ModelAndView("credentials");
		
	    List<OutlookAccount> otAccounts = (List<OutlookAccount>) outlookRepo.findAll();
		mv.addObject("credentials", mapStoredCred);
		mv.addObject("otAccounts", otAccounts);

		return mv;
	}
	
}