import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

buildscript {
    repositories {
        mavenCentral()
    }

    dependencies {
        classpath(kotlin("gradle-plugin", "1.3.21"))
    }
}

plugins {
    java
    kotlin("jvm") version "1.3.21"

    application
}

group = "net.sierisimo"
version = "0.0.1"

application {
    mainClassName = "net.sierisimo.jvm.Main"
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

tasks {
    withType<KotlinCompile> {
        kotlinOptions.jvmTarget = JavaVersion.VERSION_1_8.toString()
    }

    withType<JavaExec> {
        systemProperties(System.getProperties().map { it.key.toString() to it.value }.toMap())
    }
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib-jdk8", "1.3.21"))

    val junitJupiterVersion = "5.3.1"
    testImplementation("org.junit.jupiter:junit-jupiter-api:$junitJupiterVersion")
    testImplementation("org.junit.platform:junit-platform-runner:1.2.0")
    testImplementation("org.junit.jupiter:junit-jupiter-params:$junitJupiterVersion")

    testRuntime("org.junit.jupiter:junit-jupiter-engine:$junitJupiterVersion")

    testImplementation("org.jetbrains.kotlin:kotlin-test-junit:1.3.21")
}