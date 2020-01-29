package org.springbootcamp.test.springdata.impl;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springbootcamp.test.springdata.CrudRepositoryFake;
import org.springbootcamp.test.springdata.RepositoryContext;

@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class AbstractCrudRepositoryFake<E, I> implements CrudRepositoryFake<E, I> {

  private final RepositoryContext<E, I> context;

  @Override
  public RepositoryContext<E, I> context() {
    return context;
  }
}
