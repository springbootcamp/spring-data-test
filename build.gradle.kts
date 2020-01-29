plugins {
  id("java-library")
  id("io.freefair.lombok")
  id("java-test-fixtures")
}

apply {
  from("${rootProject.rootDir}/gradle/repositories.gradle.kts")
}

dependencies {
  implementation("org.springframework.boot:spring-boot-starter-data-jpa:2.2.4.RELEASE")
  implementation("jakarta.persistence:jakarta.persistence-api:2.2.3")

  // Use JUnit Jupiter API for testing.
  testImplementation("org.junit.jupiter:junit-jupiter-api:5.4.2")
  testImplementation("org.assertj:assertj-core:3.14.0")
  testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.4.2")
  testFixturesImplementation("org.springframework.boot:spring-boot-starter-data-jpa:2.2.4.RELEASE")
}

val test by tasks.getting(Test::class) {
  // Use junit platform for unit tests
  useJUnitPlatform()
}
