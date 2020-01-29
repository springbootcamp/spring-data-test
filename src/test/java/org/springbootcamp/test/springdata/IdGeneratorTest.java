package org.springbootcamp.test.springdata;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class IdGeneratorTest {

  @Test
  void alternating_idGenerator() {
    IdGenerator<Boolean> generator = new IdGenerator<Boolean>() {
      boolean value = false;

      @Override public Boolean next() {
        return value = !value;
      }
    };

    assertThat(generator.next()).isTrue();
    assertThat(generator.next()).isFalse();
    assertThat(generator.next()).isTrue();
  }

  @Test
  void sequence_idGenerator() {
    IdGenerator<Long> generator = IdGenerator.SEQUENCE.get();

    assertThat(generator.next()).isEqualTo(1L);
    assertThat(generator.next()).isEqualTo(2L);
    assertThat(generator.next()).isEqualTo(3L);
  }
}
