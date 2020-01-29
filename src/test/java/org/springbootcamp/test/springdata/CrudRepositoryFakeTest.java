package org.springbootcamp.test.springdata;

import org.junit.jupiter.api.Test;
import org.springbootcamp.test.springdata.test.DummyCrudRepository;
import org.springbootcamp.test.springdata.test.DummyEntity;

import java.util.LinkedHashMap;

import static org.assertj.core.api.Assertions.assertThat;

class CrudRepositoryFakeTest {

  private final DummyCrudRepository repository = new DummyCrudRepositoryFake();

  @Test
  void findAll_initially_empty() {
    assertThat(repository.findAll()).isEmpty();
  }

  @Test
  void save_and_find_by_id() {
    assertThat(repository.findById(1L)).isEmpty();

    DummyEntity entity = repository.save(entity("xxx"));
    assertThat(entity.getId()).isEqualTo(1L);
    assertThat(entity.getVersion()).isEqualTo(0L);
    assertThat(entity.getName()).isEqualTo("xxx");

    assertThat(repository.findAll()).isNotEmpty();

    assertThat(repository.findById(1L)).isNotEmpty();
  }

  private DummyEntity entity(String name) {
    return DummyEntity.builder().name(name).build();
  }

  public static class DummyCrudRepositoryFake implements DummyCrudRepository, CrudRepositoryFake<DummyEntity, Long> {

    @Override public <S extends DummyEntity> RepositoryContext<S, Long> context() {
      return new RepositoryContext<S, Long>(IdGenerator.SEQUENCE.get(), new LinkedHashMap<>()) {
        @Override public <S1 extends S> Long idFromEntity(S1 entity) {
          return entity.getId();
        }

        @Override public <S1 extends S> Long versionFromEntity(S1 entity) {
          return entity.getVersion();
        }

        @Override public <S1 extends S> S1 prepareForSave(S1 entity, Long id, Long version) {
          return (S1) entity.toBuilder()
            .id(id)
            .version(version)
            .build();
        }
      };
    }

  }
}
