package pl.zimi.hotelchallenge;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.config.EnableWebFlux;

@SpringBootApplication
@Configuration
@EnableWebFlux
public class HotelchallengeApplication {

	public static void main(String[] args) {
		SpringApplication.run(HotelchallengeApplication.class, args);
	}

}
