package org.springbootcamp.test.springdata;

import org.springframework.data.repository.Repository;

public interface RepositoryFake<E, I> extends Repository<E, I> {

  RepositoryContext<E, I> context();

}
