package fi.sycks.surveytool.interfaces;

import org.springframework.data.repository.CrudRepository;
import fi.sycks.surveytool.domain.Vastaus;

public interface VastausRepository extends CrudRepository<Vastaus, Long>{
}
