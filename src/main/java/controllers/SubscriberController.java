package controllers;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import repos.SubscriberRepo;

@RestController
public class SubscriberController {
	
	@Autowired
	private SubscriberRepo sr;

	@GetMapping("/subscribers")
	public List<String> getSubscribers() {
		
		return sr.getSubscribersAsString();
	}
}
