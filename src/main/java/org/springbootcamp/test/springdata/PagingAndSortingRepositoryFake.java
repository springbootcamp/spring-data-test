package org.springbootcamp.test.springdata;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * @deprecated not yet implemented
 * @param <E>
 * @param <I>
 */
@Deprecated
public interface PagingAndSortingRepositoryFake<E,I> extends CrudRepositoryFake<E,I>, PagingAndSortingRepository<E,I> {

  @Override
  default Iterable<E> findAll(Sort sort) {
    throw new UnsupportedOperationException("not implemented");
  }

  @Override
  default Page<E> findAll(Pageable pageable) {
    throw new UnsupportedOperationException("not implemented");
  }
}
