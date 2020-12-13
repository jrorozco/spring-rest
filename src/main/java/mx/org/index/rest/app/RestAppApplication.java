package mx.org.index.rest.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.github.javafaker.Faker;

import mx.org.index.rest.app.entities.User;
import mx.org.index.rest.app.repository.UserRepository;

@SpringBootApplication
public class RestAppApplication implements ApplicationRunner {
	
	
	
	@Autowired
	private UserRepository repository;
	
	public static void main(String[] args) {
		SpringApplication.run(RestAppApplication.class, args);
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		Faker faker= new Faker();
		
		for (int i = 0; i < 100000; i++) {
			User user = new User();
			user.setUsername(faker.name().username());
			user.setPassword(faker.lordOfTheRings().character());
			user.setProfile(null);
			repository.save(user);
		}
		
		
	}

}
