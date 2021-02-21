package repos;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import mappers.MessageRowMapper;
import models.Message;

@Repository
public class MessageRepo {
	
	@Autowired
	private JdbcTemplate jdbct;
	
	public void saveMessage(Message message) {
		jdbct.update("INSERT INTO public.messages(content)\n"
				+ "	VALUES ('" + message.getContent() + "');");
	}
	
	public Message getMessageById(Integer id) {
		String sql = "SELECT content FROM public.messages\n"
				+ "	WHERE id=" + id + ";";
		Message message = jdbct.query(sql, new MessageRowMapper()).get(0);
		
		return message;
	}
	
	public List<Message> getMessages() {
		String sql = "SELECT content FROM public.messages;";
		
		List<Message> messages = jdbct.query(sql, new MessageRowMapper());
		
		return messages;
		
	}

}
