package fi.sycks.surveytool;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import fi.sycks.surveytool.domain.Kysely;
import fi.sycks.surveytool.domain.Kysymys;
import fi.sycks.surveytool.domain.User;
import fi.sycks.surveytool.domain.Vastaaja;
import fi.sycks.surveytool.domain.Vastaus;
import fi.sycks.surveytool.interfaces.KyselyRepository;
import fi.sycks.surveytool.interfaces.KysymysRepository;
import fi.sycks.surveytool.interfaces.UserRepository;
import fi.sycks.surveytool.interfaces.VastaajaRepository;
import fi.sycks.surveytool.interfaces.VastausRepository;

@SpringBootApplication
public class SurveytoolApplication {

	public static void main(String[] args) {
		SpringApplication.run(SurveytoolApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner kyselyDemo(KyselyRepository kyselyRepository, 
			KysymysRepository kysymysRepository, UserRepository userRepository, 
			VastausRepository vastausRepository, VastaajaRepository vastaajaRepository) {
		
		return (args) -> {
			
			//Testi dataa
			
			//Kysely 1
			Kysely kysely = kyselyRepository.save(new Kysely("Testi kysely", Kysely.STATUS_NOT_DEPLOYED));
			Kysymys ikäKysymys = kysymysRepository.save(new Kysymys("Ikä?", Kysymys.TYPE_NUMBER, kysely));
			Kysymys ruokaKysymys = kysymysRepository.save(new Kysymys("Lempiruoka?", Kysymys.TYPE_SHORT_TEXT, kysely));
			
			Vastaaja vastaaja = vastaajaRepository.save(new Vastaaja("31.10.2019"));
			vastausRepository.save(new Vastaus("21", vastaaja, ikäKysymys));
			vastausRepository.save(new Vastaus("Pizza", vastaaja, ruokaKysymys));
			
			Vastaaja vastaaja2 = vastaajaRepository.save(new Vastaaja("21.10.2019"));
			vastausRepository.save(new Vastaus("65", vastaaja2, ikäKysymys));
			//vastausRepository.save(new Vastaus("Makaroonilaatikko", vastaaja2, ruokaKysymys));
			Vastaaja vastaaja4 = vastaajaRepository.save(new Vastaaja("21.10.2019"));
			vastausRepository.save(new Vastaus("12", vastaaja4, ikäKysymys));
			
			//Kysely 2
			Kysely kysely2 = kyselyRepository.save(new Kysely("Koulukysely", Kysely.STATUS_DEPLOYED));
			Kysymys vuosiKysymys = kysymysRepository.save(new Kysymys("Kauan olet opiskellut Haaga-Heliassa?", Kysymys.TYPE_NUMBER, kysely2));
			Kysymys keskiarvoKysymys = kysymysRepository.save(new Kysymys("Keskiarvosi?", Kysymys.TYPE_SHORT_TEXT, kysely2));
			Kysymys mielipideKysymys = kysymysRepository.save(new Kysymys("Mitä mieltä olet Haaga-Heliasta?", Kysymys.TYPE_MULTICHOICE3, kysely2));
			Kysymys mielipide2Kysymys = kysymysRepository.save(new Kysymys("Mielestäni koodaaminen on kivaa?", Kysymys.TYPE_MULTICHOICE5, kysely2));
			
			Vastaaja vastaaja3 = vastaajaRepository.save(new Vastaaja("31.10.2019"));
			//vastausRepository.save(new Vastaus("2 vuotta", vastaaja3, vuosiKysymys));
			//vastausRepository.save(new Vastaus("3.6", vastaaja3, keskiarvoKysymys));

			//password admin
			User user1 = new User("admin", "$2a$10$0MMwY.IQqpsVc1jC8u7IJ.2rT8b0Cd3b3sfIBGV2zfgnPGtT4r0.C", "ADMIN");
			userRepository.save(user1);
		};
		
	}
}
