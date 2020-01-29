package org.springbootcamp.test.springdata;

import java.util.Map;
import org.springbootcamp.test.springdata.impl.RepositoryContextBuilder;

/**
 * @param <E> type of entity
 * @param <I> type of id
 */
public interface RepositoryContext<E, I> {

  static <E, I> RepositoryContextBuilder<E, I> builder(Class<E> entityType, Class<I> idType) {
    return new RepositoryContextBuilder<E, I>(entityType, idType);
  }

  I nextId();

  I idFromEntity(E entity);

  Long versionFromEntity(E entity);

  <S extends E> S prepareForSave(S entity, I id, Long version);

  Map<I, E> getStore();
}
