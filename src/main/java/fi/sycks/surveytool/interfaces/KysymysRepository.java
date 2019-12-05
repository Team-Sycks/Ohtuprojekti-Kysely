package fi.sycks.surveytool.interfaces;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import fi.sycks.surveytool.domain.Kysely;
import fi.sycks.surveytool.domain.Kysymys;

public interface KysymysRepository extends CrudRepository<Kysymys, Long>{
	List<Kysymys> findByKysely(Kysely kysely);
	Kysymys findByKysymysid(Long kysymysid);

}
