package controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import models.Message;
import repos.MessageRepo;
import repos.SubscriberRepo;

@RestController
public class AdminController {
	
	@Autowired
	private SubscriberRepo sr;
	
	@Autowired
	private MessageRepo mr;

	@PostMapping("/admin/subscribers/remove")
	public String adminSubscribersRemove(@RequestParam(required=true) Integer id) {
		
		sr.removeById(id);
		
		return "Successfully removed!";
	}
	
	@PostMapping("/admin/subscribers/send")
	public String adminSubscribersSendMessage(@RequestParam("message") String message) {
		
		return message;
	}
	
	@PostMapping("/admin/subscribers/setemail")
	public String adminSubscribersSetEmail(@RequestParam("id") Integer id, @RequestParam("email") String newEmail) {
		
		sr.updateEmail(id, newEmail);
		
		return "Successfully updated email address!";
	}
	
	@GetMapping("/admin/subscribers/getmessages")
	public List<Message> adminSubscribersGetMessages() {
		
		return mr.getMessages();
		
	}
	
	
}
