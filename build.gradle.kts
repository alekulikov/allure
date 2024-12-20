import org.gradle.plugins.ide.idea.model.IdeaLanguageLevel

plugins {
    idea
    java
    id("io.qameta.allure") version ("2.12.0")
}

idea {
    project {
        languageLevel = IdeaLanguageLevel(17)
    }
    module {
        isDownloadJavadoc = true
        isDownloadSources = true
    }
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

allure {
    report {
        version.set("2.29.0")
    }
    adapter {
        aspectjWeaver.set(true)
        frameworks {
            junit5 {
                adapterVersion.set("2.29.0")
            }
        }
    }
}

group = "guru.qa"
repositories {
    mavenLocal()
    mavenCentral()
}

dependencies {
    testImplementation("org.junit.jupiter:junit-jupiter:5.10.2")
    testImplementation("com.codeborne:selenide:7.5.1")
    testImplementation("ch.qos.logback:logback-classic:1.5.6")
    testImplementation("io.qameta.allure:allure-selenide:2.29.0")
}

tasks.withType<JavaCompile> {
    options.encoding = "UTF-8"
}

tasks.withType<Test> {
    useJUnitPlatform()
}