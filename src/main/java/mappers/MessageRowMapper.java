package mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import models.Message;

public class MessageRowMapper implements RowMapper<Message>{

	@Override
	public Message mapRow(ResultSet rs, int rowNum) throws SQLException {
		Message message = new Message( rs.getString("content") );

		return message;
	}

}
