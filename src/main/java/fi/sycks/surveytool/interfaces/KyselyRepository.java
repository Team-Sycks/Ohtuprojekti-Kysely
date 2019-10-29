package fi.sycks.surveytool.interfaces;

import org.springframework.data.repository.CrudRepository;

import fi.sycks.surveytool.domain.Kysely;

public interface KyselyRepository extends CrudRepository<Kysely, Long>{
}
