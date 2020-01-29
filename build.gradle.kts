import io.freefair.gradle.plugins.lombok.tasks.GenerateLombokConfig

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

tasks {
  withType<JavaCompile> {
    sourceCompatibility = Versions.java
    targetCompatibility = Versions.java
  }
  withType<GenerateLombokConfig> {
    enabled = false
  }

  withType<Test> {
    useJUnitPlatform()
  }
}

