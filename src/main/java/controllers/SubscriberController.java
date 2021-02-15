package controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import models.Subscriber;
import repos.SubscriberRepo;

@RestController
public class SubscriberController {
	
	@Autowired
	private SubscriberRepo sr;

	@GetMapping("/subscribers")
	public List<String> subscribersIndex() {
		
		return sr.getSubscribersAsString();
	}
	
	@GetMapping("/subscribers/add/{email}/{name}")
	public String subscribersAdd(@PathVariable(required = true) String email, @PathVariable(required = false) String name) {
		
		sr.save(new Subscriber(name, email));
		
		return "Successfully added!";
	}
	
	@GetMapping("/subscribers/setname/{email}/{newName}")
	public String subscribersSetName(@PathVariable(required = true) String email, @PathVariable(required = false) String newName) {
		
		sr.updateName(email, newName);
		
		return "Name successfully updated!";
	}
}
