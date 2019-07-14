package br.com.estagio.testesgradle.user.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import br.com.estagio.testesgradle.user.model.User;

@Repository
public interface UserRepository extends MongoRepository<User, String> {


}
