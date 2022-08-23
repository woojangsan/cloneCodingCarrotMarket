package springc1.clonecoding;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class ClonecodingApplication {

	public static void main(String[] args) {
		SpringApplication.run(ClonecodingApplication.class, args);
	}

}
