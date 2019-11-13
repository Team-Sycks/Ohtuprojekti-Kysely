package fi.sycks.surveytool.interfaces;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import fi.sycks.surveytool.domain.Kysymys;
import fi.sycks.surveytool.domain.Vastaaja;
import fi.sycks.surveytool.domain.Vastaus;

public interface VastausRepository extends CrudRepository<Vastaus, Long>{
	
	List<Vastaus> findByVastaaja(Vastaaja vastaaja);
	List<Vastaus> findByVastaajaAndKysymys(Vastaaja vastaaja, Kysymys kysymys);
}
