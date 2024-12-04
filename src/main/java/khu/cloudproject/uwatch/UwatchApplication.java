package khu.cloudproject.uwatch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class UwatchApplication {

	public static void main(String[] args) {
		SpringApplication.run(UwatchApplication.class, args);
	}

}
