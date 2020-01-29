package org.springbootcamp.test.springdata;

import org.springframework.data.repository.Repository;

public interface RepositoryFake<E, I> extends Repository<E, I> {

  <S extends E> RepositoryContext<S, I> context();

}
