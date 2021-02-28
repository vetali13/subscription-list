package repos;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import mappers.MessageRowMapper;
import models.Message;
import models.Subscriber;

@Repository
public class MessageRepo {
	
	@Autowired
	private JdbcTemplate jdbct;
	
	@Autowired
	private SubscriberRepo sr;
	
	public void saveMessage(Message message) {
		jdbct.update("INSERT INTO public.messages(content)\n"
				+ "	VALUES ('" + message.getContent() + "');");
	}
	
	public Message getMessageById(Integer id) {
		String sql = "SELECT id,content FROM public.messages\n"
				+ "	WHERE id=" + id + ";";
		Message message = jdbct.query(sql, new MessageRowMapper()).get(0);
		
		return message;
	}
	
	public List<Message> getMessages() {
		String sql = "SELECT id,content FROM public.messages;";
		
		List<Message> messages = jdbct.query(sql, new MessageRowMapper());
		
		return messages;
		
	}
	
	public Map<Subscriber,Message> getNextUnsentMessage() {
		
		Map<Subscriber,Message> tuple = new HashMap<>();
		
		String sql = "SELECT subscriber_id,message_id FROM public.subscriber_message\n"
				+ "	WHERE is_sent = false LIMIT 1 OFFSET 0";
		
		SqlRowSet result = jdbct.queryForRowSet(sql);
		
		result.first();
		
		tuple.put( 	sr.getSubscriberById( result.getInt( "subscriber_id" ) ), 
					getMessageById( result.getInt( "message_id" ) ));
		
		return tuple;
	}
	
	public void markMessageAsSent(int subscriber_id, int message_id) {
		
		String sql = "UPDATE public.subscriber_message\r\n" + 
				"	SET is_sent=true\r\n" + 
				"	WHERE subscriber_id = " + subscriber_id + "\r\n" + 
				"	AND message_id = " + message_id + "\r\n" + 
				"	AND is_sent = false;";
		
		jdbct.update(sql);
	}
}