package org.springbootcamp.test.springdata;

import java.util.concurrent.atomic.AtomicLong;
import java.util.function.Supplier;

public interface IdGenerator<I> {

  Supplier<IdGenerator<Long>> SEQUENCE = () -> new IdGenerator<Long>() {
    private final AtomicLong idGenerator = new AtomicLong(1);

    @Override
    public Long next() {
      return idGenerator.getAndIncrement();
    }
  };

  Supplier<IdGenerator<String>> UUID = () -> java.util.UUID.randomUUID()::toString;

  I next();
}
