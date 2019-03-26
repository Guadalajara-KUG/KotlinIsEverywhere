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

group = "net.sierisimo.jaxrx"
version = "0.0.1"

application {
    mainClassName = "net.sierisimo.jaxrx.Main"
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib-jdk8", "1.3.21"))

    //implementation(group = "com.sun.jersey", name = "jersey-server", version = "1.8"))
    implementation(group = "javax.ws.rs", name = "javax.ws.rs-api", version = "2.1.1")
    implementation(group = "org.glassfish.jersey.core", name = "jersey-server", version = "2.25.1")
    implementation(group = "org.glassfish.jersey.containers", name = "jersey-container-servlet", version = "2.25.1")

    implementation(group = "org.apache.tomcat.embed", name = "tomcat-embed-core", version = "8.5.39")
}