import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.springframework.boot.gradle.tasks.bundling.BootJar

plugins {
    kotlin("jvm") version "1.3.71"
    id("jacoco")
}

buildscript {

    val kotlinVersion = "1.3.71"
    val springBootVersion = "2.2.4.RELEASE"

    repositories {
        mavenCentral()
        maven(url = "https://repo.spring.io/snapshot")
        maven(url = "https://repo.spring.io/milestone")
    }

    dependencies {
        classpath("org.springframework.boot:spring-boot-gradle-plugin:${springBootVersion}")
        classpath(kotlin("gradle-plugin"))
        classpath(kotlin("allopen", kotlinVersion))
        classpath(kotlin("noarg", kotlinVersion))
    }
}

subprojects {
    group = "io.parrotsoftware.pos"
}

jacoco {
    toolVersion = "0.8.5"
}

allprojects {

    apply(plugin = "kotlin")
    apply(plugin = "kotlin-spring")
    apply(plugin = "kotlin-allopen")
    apply(plugin = "jacoco")
    apply(plugin = "org.springframework.boot")
    apply(plugin = "io.spring.dependency-management")
    apply(plugin = "org.springframework.boot")

    repositories {
        jcenter()
        mavenCentral()
        maven(url = "https://repo.spring.io/snapshot")
        maven(url = "https://repo.spring.io/milestone")
    }

    java.sourceCompatibility = JavaVersion.VERSION_1_8

    dependencies {
        implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
        implementation(kotlin("reflect"))
        implementation(kotlin("stdlib-jdk8"))
        implementation("org.springframework.boot:spring-boot-starter-data-jpa:2.2.2.RELEASE")
        implementation("org.springframework.boot:spring-boot-starter-web")
        implementation("org.springframework.boot:spring-boot-starter")
        implementation("io.github.openfeign:feign-httpclient:10.4.0")
        implementation("org.springframework.cloud:spring-cloud-starter-openfeign:2.2.0.RELEASE")
        implementation("org.springframework.boot:spring-boot-starter-jetty")
        implementation("org.springframework.data:spring-data-commons:2.2.4.RELEASE")

        testImplementation("org.springframework.boot:spring-boot-starter-test")
        testImplementation ("com.nhaarman.mockitokotlin2:mockito-kotlin:2.1.0")
        testImplementation("org.mock-server:mockserver-netty:5.10.0")

        implementation("com.lemmingapex.trilateration:trilateration:1.0.2")
        implementation("com.google.code.gson:gson:2.8.6")
    }

    tasks.withType<KotlinCompile> {
        kotlinOptions {
            freeCompilerArgs = listOf("-Xjsr305=strict")
            jvmTarget = "1.8"
        }
    }

    tasks.jacocoTestReport {
        reports {
            xml.isEnabled = true
        }
    }

    tasks.getByName<Jar>("jar") {
        enabled = true
    }

    tasks.getByName<BootJar>("bootJar") {
        mainClassName = "io.parrotsoftware.pos.app.ApplicationKt"
    }

    tasks {
        "test"(Test::class) {
            useJUnitPlatform()
        }
    }
}