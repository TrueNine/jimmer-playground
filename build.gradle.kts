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
