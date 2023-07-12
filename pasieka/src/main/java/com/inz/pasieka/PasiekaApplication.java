package com.inz.pasieka;

import com.inz.pasieka.tmpPakiet.security.entities.UzytkownikAplikacji;
import com.inz.pasieka.tmpPakiet.security.services.UzytkownikAplikacjiService;
import org.modelmapper.ModelMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.client.RestTemplate;

import java.util.HashSet;

@SpringBootApplication
@EnableScheduling

public class PasiekaApplication {

	public static void main(String[] args) {
		SpringApplication.run(PasiekaApplication.class, args);
	}

	/*@Bean
	CommandLineRunner run(UzytkownikAplikacjiService service){
		return args -> {
			service.saveUzytkownikAplikacji(new UzytkownikAplikacji(null, "test1@zz.pl","pass", null, null, new HashSet<>(), null));
			service.addRolaToUzytkownikAplikacji("test1@zz.pl", "ROLE_ADMIN");
			service.saveUzytkownikAplikacji(new UzytkownikAplikacji(null, "test2@zz.pl","pass", null, null, new HashSet<>(), null));
			service.addRolaToUzytkownikAplikacji("test2@zz.pl", "ROLE_USER");
		};
	}*/

	@Bean
	public ModelMapper modelMapper(){
		return new ModelMapper();
	}

	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}
}
