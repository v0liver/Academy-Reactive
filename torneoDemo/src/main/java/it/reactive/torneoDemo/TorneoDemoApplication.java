package it.reactive.torneoDemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@EnableWebMvc
public class TorneoDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(TorneoDemoApplication.class, args);
	}

}
