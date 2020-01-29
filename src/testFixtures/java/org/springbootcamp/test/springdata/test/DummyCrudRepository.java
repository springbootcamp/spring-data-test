package org.springbootcamp.test.springdata.test;

import java.util.Optional;
import org.springframework.data.repository.CrudRepository;

public interface DummyCrudRepository extends CrudRepository<DummyEntity, Long> {

  Optional<DummyEntity> findByName(String name);
}
