package org.springbootcamp.test.springdata.test;

import static lombok.AccessLevel.PRIVATE;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@Builder(toBuilder = true)
@NoArgsConstructor(access = PRIVATE)
@Entity
@AllArgsConstructor
@ToString
@Table(name = "DUMMY")
public class DummyEntity {

  public static DummyEntity entity(String name) {
    return DummyEntity.builder()
      .name(name)
      .build();
  }

  @Id
  private Long id;

  @Version
  private Long version;

  private String name;

}
