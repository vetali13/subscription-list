package repos;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import models.Subscriber;

@Repository
public class SubscriberRepo {

	@Autowired
	private JdbcTemplate jdbct;
	
	public List<Subscriber> getSubscribers() {
		String sqlEmails = "SELECT email FROM public.subsrcribers;";
		String sqlNames = "SELECT name FROM public.subsrcribers;";
		
		List<String> subscriberEmails = jdbct.queryForList(sqlEmails, String.class);
		List<String> subscriberNames = jdbct.queryForList(sqlEmails, String.class);
		
		List<Subscriber> subscribers = new ArrayList<>();
		
		for (int i = 0; i < subscriberEmails.size(); i++ ) {
			Subscriber subscriber = new Subscriber(subscriberNames.get(i), subscriberEmails.get(i));
			subscribers.add(subscriber);
		}
		
		return subscribers;
	}
	
	public List<String> getSubscribersAsString() {
		List<String> subscribersAsString = new ArrayList<>();
		for (var subscriber : getSubscribers()) {
			subscribersAsString.add(subscriber.toString());
		}
		
		return subscribersAsString;
	}
}
