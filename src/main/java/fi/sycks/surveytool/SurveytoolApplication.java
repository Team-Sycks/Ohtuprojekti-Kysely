package fi.sycks.surveytool;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import fi.sycks.surveytool.domain.Kysely;
import fi.sycks.surveytool.domain.Kysymys;
import fi.sycks.surveytool.domain.User;
import fi.sycks.surveytool.domain.UserRepository;
import fi.sycks.surveytool.interfaces.KyselyRepository;
import fi.sycks.surveytool.interfaces.KysymysRepository;

@SpringBootApplication
public class SurveytoolApplication {

	public static void main(String[] args) {
		SpringApplication.run(SurveytoolApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner kyselyDemo(KyselyRepository kyselyRepository, 
			KysymysRepository kysymysRepository, UserRepository userRepository) {
		
		return (args) -> {
			Kysely kysely = kyselyRepository.save(new Kysely("Testi kysely", Kysely.STATUS_NOT_DEPLOYED));
			kysymysRepository.save(new Kysymys("Ikä?", Kysymys.TYPE_NUMBER, kysely));
			kysymysRepository.save(new Kysymys("Lempi ruoka?", Kysymys.TYPE_SHORT_TEXT, kysely));
			//password admin
			User user1 = new User("admin", "$2a$10$0MMwY.IQqpsVc1jC8u7IJ.2rT8b0Cd3b3sfIBGV2zfgnPGtT4r0.C", "ADMIN");
			userRepository.save(user1);
		};
		
	}
}
