buildscript {
    val kotlinVersion = "1.3.21"
    repositories { jcenter() }

    dependencies {
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlinVersion")
    }
}

plugins {
    id("kotlin2js") version "1.3.21"
    id("kotlinx-serialization") version "1.3.21"

    id("com.github.salomonbrys.gradle.sass") version "1.2.0"
}

val kotlinVersion = "1.3.21"

group = "net.sierisimo"
version = "0.0.1"

repositories {
    mavenCentral()
    jcenter()
    maven("https://kotlin.bintray.com/kotlinx")
}

tasks {
    sass {
        local()
    }

    sassCompile {
        outputDir = file("$buildDir/web")
    }

    compileKotlin2Js {
        kotlinOptions {
            outputFile = "${sourceSets.main.get().output.resourcesDir}/output.js"
            sourceMap = true
        }
    }

    val unpackKotlinJsStdlib1 by registering {
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

    val assembleHTML by registering(Copy::class) {
        group = "build"
        description = "Assemble the web application"
        includeEmptyDirs = false
        from(unpackKotlinJsStdlib1)
        from(sourceSets.main.get().output) {
            exclude("**/*.kjsm")
        }
        into("$buildDir/html")
    }

    assemble {
        dependsOn(sassCompile, assembleHTML)
    }
}

dependencies {
    implementation(kotlin("stdlib-js", version = kotlinVersion))
    implementation(group = "org.jetbrains.kotlinx", name = "kotlinx-html-js", version = "0.6.12")

    testImplementation(kotlin("test-js", version = kotlinVersion))
}