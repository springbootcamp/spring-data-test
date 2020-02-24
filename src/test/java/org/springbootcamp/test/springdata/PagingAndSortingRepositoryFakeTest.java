package org.springbootcamp.test.springdata;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springbootcamp.test.springdata.test.DummyEntity.entity;

import java.util.Arrays;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springbootcamp.test.springdata.impl.AbstractPagingAndSortingRepositoryFake;
import org.springbootcamp.test.springdata.test.DummyEntity;
import org.springbootcamp.test.springdata.test.DummyPagingAndSortingRepository;
import org.springbootcamp.test.springdata.test.RepositoryContexts;
import org.springframework.data.domain.Sort;

class PagingAndSortingRepositoryFakeTest {

  private final DummyPagingAndSortingRepository repository = new DummyPagingAndSortingRepositoryFake();

  @Test
  @Disabled("not implemented")
  void findAll_sorted() {
    Sort sort = Sort.by("name").descending();
    assertThat(repository.findAll(sort)).isEmpty();

    repository.saveAll(Arrays.asList(entity("foo"), entity("bar"), entity("ey")));

    assertThat(repository.findAll(sort)).extracting("name").containsExactly("foo","ey","bar");
  }

  //
//  /**
//   * Returns all entities sorted by the given options.
//   *
//   * @param sort
//   * @return all entities sorted by the given options
//   */
//  Iterable<T> findAll(Sort sort);
//
//  /**
//   * Returns a {@link Page} of entities meeting the paging restriction provided in the {@code Pageable} object.
//   *
//   * @param pageable
//   * @return a page of entities
//   */
//  Page<T> findAll(Pageable pageable);


  public static class DummyPagingAndSortingRepositoryFake extends AbstractPagingAndSortingRepositoryFake<DummyEntity, Long> implements
    DummyPagingAndSortingRepository {

    public DummyPagingAndSortingRepositoryFake() {
      super(RepositoryContexts.DUMMY_ENTITY.get());
    }
  }
}
