package org.springbootcamp.test.springdata;

import java.util.stream.Stream;
import java.util.stream.StreamSupport;

enum Streams {
  ;

  public static <T> Stream<T> from(Iterable<T> iterable) {
    return iterable != null
      ? StreamSupport.stream(iterable.spliterator(), false)
      : Stream.empty();
  }
}
