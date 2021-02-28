package mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import models.Subscriber;

public class SubscriberRowMapper implements RowMapper<Subscriber>{

	@Override
	public Subscriber mapRow(ResultSet rs, int rowNum) throws SQLException {
		Subscriber subscriber = new Subscriber( 
												rs.getInt("id"),
												rs.getString("name"),
												rs.getString("email")
											  );

        return subscriber;
	}

}
