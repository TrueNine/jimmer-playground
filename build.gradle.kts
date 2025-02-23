import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
  java
  alias(libs.plugins.org.springframework.boot)
  alias(libs.plugins.org.jetbrains.kotlin.jvm)
  alias(libs.plugins.org.jetbrains.kotlin.kapt)
  alias(libs.plugins.org.jetbrains.kotlin.plugin.spring)
  alias(libs.plugins.com.google.devtools.ksp)
  alias(libs.plugins.io.spring.dependency.management)
}

group = "org.example"
version = "1.0"

dependencies {
  ksp(libs.org.babyfish.jimmer.jimmer.ksp)
  implementation(libs.org.babyfish.jimmer.jimmer.spring.boot.starter)
  implementation(libs.org.flywaydb.flyway.core)

  implementation("org.springframework.boot:spring-boot-starter-web")
  implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
  implementation("org.jetbrains.kotlin:kotlin-reflect")

  developmentOnly("org.springframework.boot:spring-boot-devtools")

  runtimeOnly("com.h2database:h2")
  runtimeOnly("org.postgresql:postgresql")

  testImplementation("org.springframework.boot:spring-boot-starter-test")
  testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")
  testRuntimeOnly("org.junit.platform:junit-platform-launcher")
  testImplementation(libs.org.jetbrains.kotlin.kotlin.test.junit5)
}


repositories {
  mavenCentral()
  maven { url = uri("https://repo.spring.io/milestone") }
}

java {
  sourceCompatibility = JavaVersion.toVersion(libs.versions.java.get())
  toolchain {
    languageVersion.set(JavaLanguageVersion.of(libs.versions.java.get()))
  }
}

kapt {
  correctErrorTypes = true
  keepJavacAnnotationProcessors = true
}

kotlin {
  compilerOptions {
    jvmTarget = JvmTarget.fromTarget(libs.versions.java.get())
    freeCompilerArgs = listOf(
      "-Xjsr305=strict", "-Xjvm-default=all", "-verbose", "-Xjdk-release=${libs.versions.java.get()}", "-jvm-target=${libs.versions.java.get()}"
    )
  }
  jvmToolchain(libs.versions.java.get().toInt())
}

tasks.withType<Test> {
  useJUnitPlatform()
}
