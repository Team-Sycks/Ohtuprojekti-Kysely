package fi.sycks.surveytool;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import fi.sycks.surveytool.domain.Kysely;
import fi.sycks.surveytool.domain.Kysymys;
import fi.sycks.surveytool.domain.Monivalinta;
import fi.sycks.surveytool.domain.User;
import fi.sycks.surveytool.domain.Vastaaja;
import fi.sycks.surveytool.domain.Vastaus;
import fi.sycks.surveytool.interfaces.KyselyRepository;
import fi.sycks.surveytool.interfaces.KysymysRepository;
import fi.sycks.surveytool.interfaces.MonivalintaRepository;
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
			VastausRepository vastausRepository, VastaajaRepository vastaajaRepository,
			MonivalintaRepository monivalintaRepository) {
		
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
			vastausRepository.save(new Vastaus("Makaroonilaatikko", vastaaja2, ruokaKysymys));
			Vastaaja vastaaja4 = vastaajaRepository.save(new Vastaaja("21.10.2019"));
			vastausRepository.save(new Vastaus("12", vastaaja4, ikäKysymys));
			vastausRepository.save(new Vastaus("Pizza", vastaaja4, ruokaKysymys));
			
			
			//Kysely 2	
			Kysely kysely2 = kyselyRepository.save(new Kysely("Koulukysely", Kysely.STATUS_DEPLOYED));
			Kysymys vuosiKysymys = kysymysRepository.save(new Kysymys("Kauan olet opiskellut Haaga-Heliassa?", Kysymys.TYPE_NUMBER, kysely2));
			Kysymys keskiarvoKysymys = kysymysRepository.save(new Kysymys("Keskiarvosi?", Kysymys.TYPE_SHORT_TEXT, kysely2));
			Kysymys mielipideKysymys = kysymysRepository.save(new Kysymys("Mitä mieltä olet Haaga-Heliasta?", Kysymys.TYPE_MULTICHOICE, kysely2));
			Kysymys mielipide2Kysymys = kysymysRepository.save(new Kysymys("Mielestäni koodaaminen on kivaa?", Kysymys.TYPE_MULTICHOICE, kysely2));
		
			//DiginTestiKyselynKysymykset
			Kysely kysely3 = kyselyRepository.save(new Kysely("DiginKysely", Kysely.STATUS_DEPLOYED));
			Kysymys digiKysymys1 = kysymysRepository.save(new Kysymys("Kuinka tyytyväinen olet tämän hetkiseen haaga-helian sivustoon?", Kysymys.TYPE_MULTICHOICE, kysely3));
			Kysymys digiKysymys2 = kysymysRepository.save(new Kysymys("Kuinka tyytyväinen olet tämän hetkiseen haaga-helian sivuston palveluiden navigointiin?", Kysymys.TYPE_MULTICHOICE, kysely3));
			Kysymys digiKysymys3 = kysymysRepository.save(new Kysymys("Löydätkö HH:n pääsivustolta kaiken tarvitsemasi?", Kysymys.TYPE_MULTICHOICE, kysely3));
			Kysymys digiKysymys4 = kysymysRepository.save(new Kysymys("Kuinka helppokäyttöiseksi koet HH:n sivut?", Kysymys.TYPE_MULTICHOICE, kysely3));
			Kysymys digiKysymys5 = kysymysRepository.save(new Kysymys("Haluaisitko etusivulle yhtenäisen portaalin, josta löytyy kaikki haaga-helian eri käyttöjärjestelmät? (Esim. Moodle, Mynet, Outlook)", Kysymys.TYPE_MULTICHOICE, kysely3));
			Kysymys digiKysymys6 = kysymysRepository.save(new Kysymys("Mitä haluaisit portaalista löytyvän lisäksi? (Esim. opiskelijajärjestöt, kalenteri)", Kysymys.TYPE_SHORT_TEXT, kysely3));
			
			//DigiKysymys1 valinnat
			monivalintaRepository.save(new Monivalinta("Todella tyytyväinen", 0, digiKysymys1));
			monivalintaRepository.save(new Monivalinta("Tyytyväinen", 1, digiKysymys1));
			monivalintaRepository.save(new Monivalinta("Menettelee", 2, digiKysymys1));
			monivalintaRepository.save(new Monivalinta("Välttävä", 3, digiKysymys1));
			monivalintaRepository.save(new Monivalinta("Tyytymätön", 4, digiKysymys1));
			
			Vastaaja vastaaja5 = vastaajaRepository.save(new Vastaaja("3.12.2019"));
			vastausRepository.save(new Vastaus("Todella tyytyväinen", vastaaja5, digiKysymys1));

			//DigiKysymys2 valinnat
			monivalintaRepository.save(new Monivalinta("Todella tyytyväinen", 0, digiKysymys2));
			monivalintaRepository.save(new Monivalinta("Tyytyväinen", 1, digiKysymys2));
			monivalintaRepository.save(new Monivalinta("Menettelee", 2, digiKysymys2));
			monivalintaRepository.save(new Monivalinta("Välttävä", 3, digiKysymys2));
			monivalintaRepository.save(new Monivalinta("Tyytymätön", 4, digiKysymys2));
			
			//DigiKysymys3 valinnat
			monivalintaRepository.save(new Monivalinta("Kyllä", 0, digiKysymys3));
			monivalintaRepository.save(new Monivalinta("En",1,digiKysymys3));
			monivalintaRepository.save(new Monivalinta("Puutteellisesti", 2, digiKysymys3));
			
			//DigiKysymys2 valinnat
			monivalintaRepository.save(new Monivalinta("Todella helppokäyttöiseksi", 0, digiKysymys4));
			monivalintaRepository.save(new Monivalinta("Osittain helppokäyttöiseksi", 1, digiKysymys4));
			monivalintaRepository.save(new Monivalinta("Osittain hankalakäyttöiseksi", 2, digiKysymys4));
			monivalintaRepository.save(new Monivalinta("Todella hankalakäyttöiseksi", 3, digiKysymys4));
			
			//DigiKysymys5 valinnat
			monivalintaRepository.save(new Monivalinta("Kyllä", 0, digiKysymys5));
			monivalintaRepository.save(new Monivalinta("En", 1, digiKysymys5));
			
			Vastaaja vastaaja3 = vastaajaRepository.save(new Vastaaja("31.10.2019"));
			//vastausRepository.save(new Vastaus("2 vuotta", vastaaja3, vuosiKysymys));
			//vastausRepository.save(new Vastaus("3.6", vastaaja3, keskiarvoKysymys));

			//password admin
			User user1 = new User("admin", "$2a$10$0MMwY.IQqpsVc1jC8u7IJ.2rT8b0Cd3b3sfIBGV2zfgnPGtT4r0.C", "ADMIN");
			userRepository.save(user1);
		};
		
	}
}
