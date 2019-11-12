package fi.sycks.surveytool.interfaces;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import fi.sycks.surveytool.domain.Kysely;
import fi.sycks.surveytool.domain.Kysymys;
import fi.sycks.surveytool.domain.Monivalinta;

public interface MonivalintaRepository extends CrudRepository<Monivalinta, Long>{

	List<Monivalinta> findByKysymys(Kysymys kysymys);
	
}
