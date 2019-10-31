package fi.sycks.surveytool.interfaces;

import org.springframework.data.repository.CrudRepository;

import fi.sycks.surveytool.domain.Kysely;
import fi.sycks.surveytool.domain.Vastaaja;

public interface VastaajaRepository extends CrudRepository<Vastaaja, Long>{
}
