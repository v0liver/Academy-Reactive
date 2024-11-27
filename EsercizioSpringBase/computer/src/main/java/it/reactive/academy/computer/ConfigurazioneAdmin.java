package it.reactive.academy.computer;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;


@Configuration()
@Profile("ADMIN")
public class ConfigurazioneAdmin {

  @Bean
  public Tastiera getTastiera(){
      return  new Tastiera();
  }
}
