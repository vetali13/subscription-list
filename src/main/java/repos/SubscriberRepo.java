package repos;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import mappers.MessageRowMapper;
import mappers.SubscriberRowMapper;
import models.Message;
import models.Subscriber;

@Repository
public class SubscriberRepo {

	@Autowired
	private JdbcTemplate jdbct;
	
	public List<Subscriber> getSubscribers() {
		String sql = "SELECT id,name,email FROM public.subsrcribers;";
		
		List<Subscriber> subscribers = jdbct.query(sql, new SubscriberRowMapper());
		return subscribers;
	}
	
	public List<String> getSubscribersAsString() {
		List<String> subscribersAsString = new ArrayList<>();
		for (var subscriber : getSubscribers()) {
			subscribersAsString.add(subscriber.toString());
		}
		
		return subscribersAsString;
	}
	
	public void save(Subscriber subscriber) {
		jdbct.update("INSERT INTO public.subsrcribers( name, email ) "
				+ "VALUES ('"+ 
				subscriber.getName() + "','" + 
				subscriber.getEmail() + "');");
	}
	
	public void updateName(String email, String newName) {
		jdbct.update("UPDATE public.subsrcribers\r\n" + 
				"	SET name='" + newName + "'\r\n" + 
				"	WHERE email='" + email + "';");
	}
	
	public void removeById(Integer id) {
		jdbct.update("DELETE FROM public.subsrcribers\r\n" + 
				"	WHERE id=" + id + ";");
	}
	
	public void updateEmail(Integer id, String newEmail) {
		jdbct.update("UPDATE public.subsrcribers\r\n" + 
				"	SET email='" + newEmail + "'\r\n" + 
				"	WHERE id=" + id + ";");
	}
	
	public Subscriber getSubscriberById(Integer id) {
		String sql = "SELECT id,name,email FROM public.subsrcribers\n"
				+ "	WHERE id=" + id + ";";
		Subscriber subscriber = jdbct.query(sql, new SubscriberRowMapper()).get(0);
		
		return subscriber;
	}
	
}
