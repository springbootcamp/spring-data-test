package org.springbootcamp.test.springdata;

import java.util.HashMap;

public abstract class RepositoryContext<E, I> {

  private final IdGenerator<I> idGenerator;
  private final HashMap<I, E> store;

  protected RepositoryContext(IdGenerator<I> idGenerator, HashMap<I, E> store) {
    this.idGenerator = idGenerator;
    this.store = store;
  }

  protected abstract <S extends E> I idFromEntity(S entity);

  protected abstract <S extends E> Long versionFromEntity(S entity);

  protected abstract <S extends E> S prepareForSave(S entity, I id, Long version);

  public IdGenerator<I> idGenerator() {
    return idGenerator;
  }

  public E get(I id) {
    return store.get(id);
  }

  public boolean exists(I id) {
    return store.containsKey(id);
  }

  public <S extends E> void put(I id, S entity) {
    store.put(id, entity);
  }

  public Iterable<E> values() {
    return store.values();
  }

  @Override public String toString() {
    return "RepositoryContext{" +
      "store=" + store +
      '}';
  }
}
