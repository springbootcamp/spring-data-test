package org.springbootcamp.test.springdata.impl;

import static java.util.Objects.requireNonNull;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Function;
import org.springbootcamp.test.springdata.IdGenerator;
import org.springbootcamp.test.springdata.RepositoryContext;

public class RepositoryContextBuilder<E, I> {

  private final Class<E> entityType;
  private final Class<I> idType;

  private IdGenerator<I> idGenerator;
  private Map<I, E> store = new LinkedHashMap<>();
  private Function<E, I> idFromEntity;
  private Function<E, Long> versionFromEntity;
  private PrepareForSave<E, I> prepareForSave;

  @SuppressWarnings("unchecked")
  public RepositoryContextBuilder(Class<E> entityType, Class<I> idType) {
    this.entityType = entityType;
    this.idType = idType;

    if (Long.class.equals(idType)) {
      idGenerator = (IdGenerator<I>) IdGenerator.SEQUENCE.get();
    } else if (String.class.equals(idType)) {
      idGenerator = (IdGenerator<I>) IdGenerator.UUID.get();
    }
  }

  public RepositoryContextBuilder<E, I> idGenerator(IdGenerator<I> idGenerator) {
    this.idGenerator = idGenerator;
    return this;
  }

  public RepositoryContextBuilder<E, I> idFromEntity(Function<E, I> idFromEntity) {
    this.idFromEntity = idFromEntity;
    return this;
  }

  public RepositoryContextBuilder<E, I> versionFromEntity(Function<E, Long> versionFromEntity) {
    this.versionFromEntity = versionFromEntity;
    return this;
  }

  public RepositoryContextBuilder<E, I> prepareForSave(PrepareForSave<E, I> prepareForSave) {
    this.prepareForSave = prepareForSave;
    return this;
  }


  public RepositoryContext<E, I> build() {
    requireNonNull(idGenerator, "idGenerator has to be set");
    requireNonNull(store, "store has to be set");
    requireNonNull(idFromEntity, "idFromEntity has to be set");
    requireNonNull(versionFromEntity, "versionFromEntity has to be set");
    requireNonNull(prepareForSave, "prepareForSave has to be set");

    return new RepositoryContext<E, I>() {
      @Override
      public I nextId() {
        return idGenerator.next();
      }

      @Override
      public I idFromEntity(E entity) {
        return idFromEntity.apply(entity);
      }

      @Override
      public Long versionFromEntity(E entity) {
        return versionFromEntity.apply(entity);
      }

      @Override
      @SuppressWarnings("unchecked")
      public <S extends E> S prepareForSave(S entity, I id, Long version) {
        return (S) prepareForSave.apply(entity, id, version);
      }

      @Override
      public Map<I, E> getStore() {
        return store;
      }
    };
  }

  @FunctionalInterface
  public interface PrepareForSave<E, I> {

    E apply(E entity, I id, Long version);

  }
}
