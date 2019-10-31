package fi.sycks.surveytool;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import fi.sycks.surveytool.domain.Kysely;
import fi.sycks.surveytool.domain.Kysymys;
import fi.sycks.surveytool.domain.Vastaaja;
import fi.sycks.surveytool.domain.Vastaus;
import fi.sycks.surveytool.interfaces.KyselyRepository;
import fi.sycks.surveytool.interfaces.KysymysRepository;
import fi.sycks.surveytool.interfaces.VastaajaRepository;
import fi.sycks.surveytool.interfaces.VastausRepository;

@RunWith(SpringRunner.class)
@DataJpaTest


public class KysymysRepositoryTest {
@Autowired
private KysymysRepository kysymysRepository;
@Autowired
private KyselyRepository kyselyRepository;
@Autowired
private VastaajaRepository vastaajaRepository;
@Autowired
private VastausRepository vastausRepository;

@Test
public void createNewKysely() {
	Kysely kysely = new Kysely("Testikysely", "Ylhäällä");
	kyselyRepository.save(kysely);
	assertThat(kysely.getKyselyid()).isNotNull();
	
}

@Test
public void createNewKysymys() {
	Kysymys kysymys = new Kysymys("Onko kiki hyvin?", "short_text", new Kysely());
	kysymysRepository.save(kysymys);
	assertThat(kysymys.getKysymysid()).isNotNull();
	
	
	
}
@Test
public void findByVastaajaShouldReturnVastaus() {
    Vastaaja vastaaja = new Vastaaja("1");
    vastaajaRepository.save(vastaaja);
    
	Kysely kysely = new Kysely("Testikysely2", "Ylhäällä");
	kyselyRepository.save(kysely);
	
    Kysymys kysymys1 = kysymysRepository.save(new Kysymys("Onko ok?", "short_text", kysely));
    kysymysRepository.save(kysymys1);
    
    Vastaus vastaus = new Vastaus("Kiki on hyvin", vastaaja, kysymys1);
    vastausRepository.save(vastaus);
    
    List<Vastaus> vas = vastausRepository.findByVastaaja(vastaaja);
    assertThat(vas).hasSize(1);
}
@Test
public void findSpecificVastaaja() {
	Vastaaja vastaaja1 = new Vastaaja("1");
	Vastaaja vastaaja2 = new Vastaaja("2");
	vastaajaRepository.save(vastaaja1);
	vastaajaRepository.save(vastaaja2);
	assertThat(vastaaja1.getVastaajaid()).isNotNull();
	assertThat(vastaaja2.getVastaajaid()).isNotNull();
	
	Kysely kysely1 = new Kysely("Teksikysely3", "Ylhäällä");
	kyselyRepository.save(kysely1);
	assertThat(kysely1.getDeployattu()).isEqualTo("Ylhäällä");
	
	Kysymys kysymys1 = new Kysymys("Onko JUnit-testaus hyödyllistä?", "short_text", kysely1);
	kysymysRepository.save(kysymys1);
	
	
	Kysymys kysymys2 = new Kysymys("Onko kona?", "short_text", kysely1);
	kysymysRepository.save(kysymys2);
	
	
	Vastaus vastaus1 = new Vastaus("JUnit on iha ok mut ootko kattonu", vastaaja1, kysymys1);
	Vastaus vastaus2 = new Vastaus("En tykkää JUnitista", vastaaja2, kysymys1);
	Vastaus vastaus3 = new Vastaus("NYT ON KONA", vastaaja1, kysymys2);
	Vastaus vastaus4 = new Vastaus("Kona korner isännyys", vastaaja2, kysymys2);
	vastausRepository.save(vastaus1);
	vastausRepository.save(vastaus2);
	vastausRepository.save(vastaus3);
	vastausRepository.save(vastaus4);
	
}
}
