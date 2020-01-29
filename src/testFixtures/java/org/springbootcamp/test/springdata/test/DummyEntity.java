package org.springbootcamp.test.springdata.test;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;

import static lombok.AccessLevel.PRIVATE;

@Getter
@Builder(toBuilder = true)
@NoArgsConstructor(access = PRIVATE)
@Entity
@AllArgsConstructor
@ToString
@Table(name = "DUMMY")
public class DummyEntity {

  @Id
  private Long id;

  @Version
  private Long version;

  private String name;

}
