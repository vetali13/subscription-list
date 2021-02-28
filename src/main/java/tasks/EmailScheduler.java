package tasks;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import models.Message;
import models.Subscriber;
import repos.MessageRepo;

@Component
public class EmailScheduler {
	
	@Autowired
	MessageRepo mr;
	
	 @Autowired
	 private JavaMailSender emailSender;
	
	@Scheduled(fixedDelay = 60*1000, initialDelay = 10000)
	public void sendEmail() {
		Map<Subscriber,Message> tuple = mr.getNextUnsentMessage();
		
		if ( !tuple.isEmpty() ) {
			tuple.forEach((k,v) -> {
				try {
					SimpleMailMessage message = new SimpleMailMessage();
					message.setFrom("vetali13@gmail.com");
					message.setTo( k.getEmail() );
					message.setSubject("Test email");
					message.setText( v.getContent() );
					emailSender.send(message);
					mr.markMessageAsSent(k.getId(), v.getId());
				} catch (Exception e) {
					e.printStackTrace();
				}
				
			});
		}
		
	}

}
