package org.springbootcamp.test.springdata;

import java.util.ArrayList;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.data.repository.CrudRepository;
import org.springframework.lang.NonNull;

public interface CrudRepositoryFake<E, I> extends CrudRepository<E, I>, RepositoryFake<E, I> {

  @NonNull
  @Override
  default <S extends E> S save(@NonNull S entity) {
    I id = Optional.ofNullable(context().idFromEntity(entity))
      .orElseGet(context()::nextId);
    long version = Optional.ofNullable(context().versionFromEntity(entity))
      .map(v -> v + 1)
      .orElse(0L);

    S newEntity = (S) context().prepareForSave(entity, id, version);

    context().getStore().put(id, newEntity);
    return newEntity;
  }

  @Override
  default <S extends E> Iterable<S> saveAll(Iterable<S> entities) {
    return Streams.from(entities).map(this::save).collect(Collectors.toList());
  }

  @Override
  @NonNull
  default Optional<E> findById(@NonNull I id) {
    return existsById(id)
      ? Optional.of(context().getStore().get(id))
      : Optional.empty()
      ;
  }

  @Override
  default boolean existsById(I id) {
    return context().getStore().get(id) != null;
  }

  @Override
  default Iterable<E> findAll() {
    return context().getStore().values();
  }

  @Override
  default Iterable<E> findAllById(Iterable<I> ids) {
    return Streams.from(ids).map(this::findById).filter(Optional::isPresent).map(Optional::get).collect(Collectors.toList());
  }

  @Override
  default long count() {
    return context().getStore().size();
  }

  @Override
  default void deleteById(I id) {
    context().getStore().remove(id);
  }

  @Override
  default void delete(E entity) {
    deleteById(context().idFromEntity(entity));
  }

  @Override
  default void deleteAll(Iterable<? extends E> entities) {
    Streams.from(entities).forEach(this::delete);
  }

  @Override
  default void deleteAll() {
    this.deleteAll(new ArrayList<>(context().getStore().values()));
  }

}
