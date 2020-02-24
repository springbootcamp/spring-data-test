package org.springbootcamp.test.springdata.impl;

import org.springbootcamp.test.springdata.PagingAndSortingRepositoryFake;
import org.springbootcamp.test.springdata.RepositoryContext;

public abstract class AbstractPagingAndSortingRepositoryFake<E, I> extends AbstractCrudRepositoryFake<E, I> implements
  PagingAndSortingRepositoryFake<E, I> {

  protected AbstractPagingAndSortingRepositoryFake(RepositoryContext<E, I> context) {
    super(context);
  }

}
