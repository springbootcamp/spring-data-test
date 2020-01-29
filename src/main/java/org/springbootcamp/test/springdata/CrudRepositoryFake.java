package org.springbootcamp.test.springdata;

import org.springframework.data.repository.CrudRepository;
import org.springframework.lang.NonNull;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public interface CrudRepositoryFake<E, I> extends CrudRepository<E, I>, RepositoryFake<E, I> {

  @NonNull
  @Override
  default <S extends E> S save(@NonNull S entity) {
    I id = Optional.ofNullable(context().idFromEntity(entity))
      .orElseGet(context().idGenerator()::next);
    long version = Optional.ofNullable(context().versionFromEntity(entity))
      .map(v -> v + 1)
      .orElse(0L);

    S newEntity = context().prepareForSave(entity, id, version);

    context().put(id, newEntity);
    return newEntity;
  }

  @Override
  default <S extends E> Iterable<S> saveAll(Iterable<S> entities) {
    return StreamSupport.stream(entities.spliterator(), false).map(this::save).collect(Collectors.toList());
  }

  @Override
  @NonNull
  default Optional<E> findById(@NonNull I id) {
    return existsById(id)
      ? Optional.of(context().get(id))
      : Optional.empty()
      ;
  }

  @Override default boolean existsById(I id) {
    return context().get(id) != null;
  }

  @Override default Iterable<E> findAll() {
    return context().values();
  }

  @Override default Iterable<E> findAllById(Iterable<I> is) {
    return null;
  }

  @Override default long count() {
    return 0L;
  }

  @Override default void deleteById(I i) {

  }

  @Override default void delete(E entity) {

  }

  @Override default void deleteAll(Iterable<? extends E> entities) {

  }

  @Override default void deleteAll() {

  }

  //
  //  private final IdGenerator<I> idGenerator;
  //  private final Map<I, E> store = new HashMap<>();
  //
  //  protected CrudRepositoryFake(IdGenerator<I> idGenerator) {
  //    this.idGenerator = idGenerator;
  //  }
  //
  //  protected abstract <S extends E> I idFromEntity(S entity);
  //
  //  protected abstract <S extends E> Long versionFromEntity(S entity);
  //
  //  protected abstract <S extends E> S prepareForSave(S entity, I id, long version);
  //
  //  @Override
  //  public <S extends E> S save(S entity) {
  //    I id = Optional.ofNullable(idFromEntity(entity))
  //      .orElseGet(idGenerator::nextId);
  //    long version = Optional.ofNullable(versionFromEntity(entity))
  //      .map(v -> v + 1)
  //      .orElse(0L);
  //
  //    S newEntity = prepareForSave(entity, id, version);
  //
  //    store.put(id, newEntity);
  //    return newEntity;
  //  }
  //
  //  @Override
  //  @Nonnull
  //  public <S extends E> Iterable<S> saveAll(@Nonnull Iterable<S> entities) {
  //    return streamFromIterable(entities).map(this::save).collect(Collectors.toList());
  //  }
  //

  //  @Override
  //  public Iterable<E> findAll() {
  //    return store.values();
  //  }
  //
  //  @Override
  //  @Nonnull
  //  public Iterable<E> findAllById(Iterable<I> ids) {
  //    Set<I> idSet = Sets.newHashSet(ids);
  //
  //    return streamFromIterable(findAll())
  //      .filter(e -> idSet.contains(idFromEntity(e)))
  //      .collect(Collectors.toList());
  //  }
  //
  //  @Override
  //  public long count() {
  //    return store.size();
  //  }
  //
  //  @Override
  //  public void deleteById(I id) {
  //    store.remove(id);
  //  }
  //
  //  @Override
  //  public void delete(@Nonnull E entity) {
  //    deleteById(idFromEntity(entity));
  //  }
  //
  //  @Override
  //  public void deleteAll(@Nonnull Iterable<? extends E> entities) {
  //    streamFromIterable(entities).forEach(this::delete);
  //  }
  //
  //  @Override
  //  public void deleteAll() {
  //    store.clear();
  //  }
  //
  //  @Override
  //  public String toString() {
  //    return String.format("%s{store=%s}", this.getClass().getSimpleName(), store);
  //  }
  //
  //  @SuppressWarnings("all")
  //  public static <E> Stream<? extends E> streamFromIterable(Iterable<? extends E> entities) {
  //    return Streams.stream(entities);
  //  }

}
