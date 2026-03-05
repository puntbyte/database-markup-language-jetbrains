plugins {
  id("java")
  id("org.jetbrains.kotlin.jvm") version "2.1.20"
  id("org.jetbrains.intellij.platform") version "2.10.2"
  //id("org.jetbrains.kotlin.plugin.compose") version "2.1.20"
  //id("org.jetbrains.grammarkit") version "2023.3.0.2"
}

group = "com.puntbyte"
version = "1.0-SNAPSHOT"

repositories {
  mavenCentral()

  //maven { url = uri("https://www.jetbrains.com/intellij-repository/releases") }
  //maven { url = uri("https://cache-redirector.jetbrains.com/plugins.jetbrains.com/maven") }

  intellijPlatform {
    defaultRepositories()
  }
}

val localIdePath = "C:\\Program Files\\JetBrains\\IntelliJ IDEA 2025.3.3"

// Read more: https://plugins.jetbrains.com/docs/intellij/tools-intellij-platform-gradle-plugin.html
dependencies {
  intellijPlatform {
    intellijIdea("2025.2.4")
    // Use your local IDE installation instead of downloading one
    //local(localIdePath)
    testFramework(org.jetbrains.intellij.platform.gradle.TestFrameworkType.Platform)

    // Add plugin dependencies for compilation here:

    //composeUI()
    //plugin("com.markskelton.one-dark-theme:6.2.2")

  }
}

intellijPlatform {
  pluginConfiguration {
    ideaVersion {
      sinceBuild = "252.25557"
    }

    changeNotes = """Initial version""".trimIndent()
  }
}

tasks {
  withType<JavaCompile> {
    sourceCompatibility = "21"
    targetCompatibility = "21"
  }
}

kotlin {
  compilerOptions {
    jvmTarget.set(org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_21)
  }
}

sourceSets {
  main {
    java {
      srcDirs("src/main/gen") // Ensure gen is listed
    }
  }
}
