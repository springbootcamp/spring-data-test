package org.springbootcamp.test.springdata;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springbootcamp.test.springdata.test.DummyEntity;
import org.springbootcamp.test.springdata.test.RepositoryContexts;

class RepositoryContextTest {

  private final RepositoryContext<DummyEntity, Long> context = RepositoryContexts.DUMMY_ENTITY.get();

  @Test
  void nextId_is_countingUp() {
    assertThat(context.nextId()).isEqualTo(1L);
    assertThat(context.nextId()).isEqualTo(2L);
  }
}
