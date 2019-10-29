package fi.sycks.surveytool.interfaces;

import org.springframework.data.repository.CrudRepository;

import fi.sycks.surveytool.domain.Kysymys;

public interface KysymysRepository extends CrudRepository<Kysymys, Long>{
}
