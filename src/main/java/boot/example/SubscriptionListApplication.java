package boot.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan({"controllers", "repos", "models", "mappers"})
@SpringBootApplication
public class SubscriptionListApplication {

	public static void main(String[] args) {
		SpringApplication.run(SubscriptionListApplication.class, args);
	}

}
