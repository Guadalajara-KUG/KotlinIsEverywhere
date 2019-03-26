buildscript {
    val kotlinVersion = "1.3.21"
    repositories { jcenter() }

    dependencies {
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlinVersion")
        classpath("org.jetbrains.kotlin:kotlin-serialization:$kotlinVersion")
    }
}

plugins {
    id("kotlin2js") version "1.3.21"
    id("kotlinx-serialization") version "1.3.21"
}

val kotlinVersion = "1.3.21"

group = "net.sierisimo"
version = "0.0.1"

repositories {
    mavenCentral()
    maven("https://kotlin.bintray.com/kotlinx")
}

tasks {
    compileKotlin2Js {
        kotlinOptions {
            outputFile = "${sourceSets.main.get().output.resourcesDir}/output.js"
            sourceMap = true
        }
    }

    val unpackKotlinJsStdlib by registering {
        group = "build"
        description = "Unpack the Kotlin JavaScript standard library"

        val outputDir = file("$buildDir/$name")

        inputs.property("compileClasspath", configurations.compileClasspath.get())
        outputs.dir(outputDir)

        doLast {
            val kotlinStdLibJar = configurations.compileClasspath.get().single {
                it.name.matches(Regex("kotlin-stdlib-js-.+\\.jar"))
            }

            copy {
                includeEmptyDirs = false

                from(zipTree(kotlinStdLibJar))
                into(outputDir)
                include("**/*.js")
                exclude("META-INF/**")
            }

            configurations.compileClasspath.get().forEach {
                copy {
                    includeEmptyDirs = false

                    from(zipTree(it.absolutePath))
                    into(outputDir)
                    include {
                        val path = it.path

                        path.endsWith(".js") || (path.startsWith("META-INF/resources/") || !path.startsWith("META-INF/"))
                    }
                }
            }
        }
    }

    val assembleWeb by registering(Copy::class) {
        group = "build"
        description = "Assemble the web application"
        includeEmptyDirs = false
        from(unpackKotlinJsStdlib)
        from(sourceSets.main.get().output) {
            exclude("**/*.kjsm")
        }
        into("$buildDir/js")
    }

    assemble {
        dependsOn(assembleWeb)//, cleanUnpacked)
    }
}

dependencies {
    implementation(kotlin("stdlib-js", version = kotlinVersion))
    implementation(group = "org.jetbrains.kotlinx", name = "kotlinx-serialization-runtime-js", version = "0.10.0")

    testImplementation(kotlin("test-js", version = kotlinVersion))
}