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

repositories {
    mavenCentral()
    maven("https://kotlin.bintray.com/kotlinx")
}

tasks {
    val outDir = "$buildDir/out"
    val appName = "app.js"
    compileKotlin2Js {
        kotlinOptions {
            outputFile = "$outDir/$appName"
            sourceMap = true
            moduleKind = "commonjs"
        }
    }

    val runExpress by registering(Exec::class) {
        workingDir = File(outDir)

        commandLine = listOf("node", appName)
    }
}

dependencies {
    implementation(kotlin("stdlib-js", version = kotlinVersion))
    implementation(group = "org.jetbrains.kotlinx", name = "kotlinx-serialization-runtime-js", version = "0.10.0")

    testImplementation(kotlin("test-js", version = kotlinVersion))
}