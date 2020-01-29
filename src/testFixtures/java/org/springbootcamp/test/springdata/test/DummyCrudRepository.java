package org.springbootcamp.test.springdata.test;

import org.springframework.data.repository.CrudRepository;

public interface DummyCrudRepository extends CrudRepository<DummyEntity, Long> {
}
