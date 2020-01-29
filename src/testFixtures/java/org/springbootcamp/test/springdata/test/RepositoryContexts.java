package org.springbootcamp.test.springdata.test;

import java.util.function.Supplier;
import org.springbootcamp.test.springdata.RepositoryContext;

public enum RepositoryContexts {
  ;

  public static final Supplier<RepositoryContext<DummyEntity, Long>> DUMMY_ENTITY = () -> RepositoryContext
    .builder(DummyEntity.class, Long.class)
    .idFromEntity(DummyEntity::getId)
    .versionFromEntity(DummyEntity::getVersion)
    .prepareForSave((e, i, v) -> e.toBuilder().id(i).version(v).build())
    .build();
}
