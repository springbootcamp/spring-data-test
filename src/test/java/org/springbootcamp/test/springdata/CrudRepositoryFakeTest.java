package org.springbootcamp.test.springdata;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.springbootcamp.test.springdata.impl.AbstractCrudRepositoryFake;
import org.springbootcamp.test.springdata.test.DummyCrudRepository;
import org.springbootcamp.test.springdata.test.DummyEntity;
import org.springbootcamp.test.springdata.test.RepositoryContexts;

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
    assertThat(repository.findById(2L)).isEmpty();
  }


  @Test
  void save_and_find_all() {
    Iterable<DummyEntity> entities = repository.saveAll(Arrays.asList(entity("foo"), entity("bar")));
    assertThat(entities).hasSize(2);

    assertThat(repository.findAll()).extracting("name").containsExactlyInAnyOrder("foo", "bar");
  }

  @Test
  void existsById() {
    assertThat(repository.existsById(1L)).isFalse();
    repository.save(entity("foo"));
    assertThat(repository.existsById(1L)).isTrue();
  }

  @Test
  void findAllById() {
    assertThat(repository.existsById(1L)).isFalse();
    assertThat(repository.existsById(2L)).isFalse();

    repository.saveAll(Arrays.asList(entity("foo"), entity("bar")));

    assertThat(repository.findAllById(Arrays.asList(1L, 2L))).hasSize(2);
  }

  @Test
  void count() {
    assertThat(repository.count()).isEqualTo(0L);
    repository.save(entity("foo"));
    assertThat(repository.count()).isEqualTo(1L);
  }

  @Test
  void deleteById() {
    long id = repository.save(entity("foo")).getId();
    assertThat(repository.findById(id)).isNotEmpty();
    repository.deleteById(1L);
    assertThat(repository.findById(id)).isEmpty();
  }

  @Test
  void delete() {
    DummyEntity entity = repository.save(entity("foo"));
    assertThat(repository.findById(entity.getId())).isNotEmpty();
    repository.delete(entity);
    assertThat(repository.findById(entity.getId())).isEmpty();
  }

  @Test
  void deleteAll() {
    repository.saveAll(Arrays.asList(entity("foo"), entity("bar")));
    assertThat(repository.findAll()).hasSize(2);

    repository.deleteAll();

    assertThat(repository.findAll()).isEmpty();
  }

  @Test
  void findByName() {
    assertThat(repository.findByName("foo")).isEmpty();

    repository.save(entity("foo"));

    assertThat(repository.findByName("foo")).isNotEmpty();
  }

  private DummyEntity entity(String name) {
    return DummyEntity.builder().name(name).build();
  }


  public static class DummyCrudRepositoryFake extends AbstractCrudRepositoryFake<DummyEntity, Long> implements DummyCrudRepository {

    public DummyCrudRepositoryFake() {
      super(RepositoryContexts.DUMMY_ENTITY.get());
    }

    @Override
    public Optional<DummyEntity> findByName(String name) {
      return Streams.from(findAll()).filter(it -> name.equals(it.getName())).findFirst();
    }
  }
}
