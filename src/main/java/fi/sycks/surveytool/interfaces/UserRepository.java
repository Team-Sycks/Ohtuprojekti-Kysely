package fi.sycks.surveytool.interfaces;

import org.springframework.data.repository.CrudRepository;

import fi.sycks.surveytool.domain.User;

public interface UserRepository extends CrudRepository <User, Long> {
	User findByUsername(String username);

}
