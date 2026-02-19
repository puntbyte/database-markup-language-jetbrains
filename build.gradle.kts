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

// Read more: https://plugins.jetbrains.com/docs/intellij/tools-intellij-platform-gradle-plugin.html
dependencies {
    intellijPlatform {
        intellijIdea("2025.2.4")
        testFramework(org.jetbrains.intellij.platform.gradle.TestFrameworkType.Platform)

        // Add plugin dependencies for compilation here:

        //composeUI()
        plugin("com.markskelton.one-dark-theme:6.2.2")

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

// Configure Grammar-Kit generation
/*grammarKit {
    grammarKitRelease.set("2023.3.0.2")

    // Version of IntelliJ to build against for the parser generation
    jflexRelease.set("1.9.1")
}*/

tasks {
    /*generateLexer {
        sourceFile.set(file("src/main/kotlin/com/puntbyte/dbml/grammar/dbml.flex"))
        targetOutputDir.set(file("src/main/gen/com/puntbyte/dbml/lexer"))
        //targetClass.set("DbmlLexer")
        purgeOldFiles.set(true)
    }

    generateParser {
        sourceFile.set(file("src/main/kotlin/com/puntbyte/dbml/grammar/dbml.bnf"))
        targetRootOutputDir.set(file("src/main/gen"))
        pathToParser.set("com/puntbyte/dbml/parser/DbmlParser.java")
        pathToPsiRoot.set("com/puntbyte/dbml/psi")
        purgeOldFiles.set(true)
    }*/

    withType<JavaCompile> {
        sourceCompatibility = "21"
        targetCompatibility = "21"
    }

    /*withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
        // Ensure generation happens before compilation
        dependsOn(generateLexer, generateParser)
    }*/
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
