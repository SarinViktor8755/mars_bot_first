plugins {
    id("com.github.johnrengelman.shadow") version "8.1.1"
    id("java")
    id("application")


}

group = "org.example"
version = "1.0-SNAPSHOT"
application {
    mainClass = "org.example.Main"
}

repositories {
    mavenCentral()
}



dependencies {
    implementation ("com.github.pengrad:java-telegram-bot-api:7.2.1")

    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")

}

tasks.test {
    useJUnitPlatform()

}

buildscript {
    repositories {
        maven {
            url = uri("https://plugins.gradle.org/m2/")
        }
    }
    dependencies {
        classpath("com.github.johnrengelman:shadow:8.1.1")
    }
}

apply(plugin = "com.github.johnrengelman.shadow")


