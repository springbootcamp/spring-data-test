package org.springbootcamp.test.springdata;

import java.util.List;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.lang.NonNull;

/**
 * @deprecated not yet implemented
 */
@Deprecated
public interface JpaRepositoryFake<E, I> extends JpaRepository<E, I>, PagingAndSortingRepository<E, I> {

  @Override
  List<E> findAll();

  @Override
  List<E> findAll(Sort sort);

  @Override
  List<E> findAllById(Iterable<I> is);

  @Override
  <S extends E> List<S> saveAll(Iterable<S> entities);

  @Override
  void flush();

  @Override
  <S extends E> S saveAndFlush(S entity);

  @Override
  void deleteInBatch(Iterable<E> entities);

  @Override
  void deleteAllInBatch();

  @Override
  E getOne(I i);

  @Override
  <S extends E> List<S> findAll(Example<S> example);

  @Override
  <S extends E> List<S> findAll(Example<S> example, Sort sort);
}
