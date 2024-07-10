buildscript {
    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        classpath("com.android.tools.build:gradle:8.4.1")
        classpath("com.google.gms:google-services:4.3.10") // Updated to match the plugin version
    }
}

plugins {
    java
    id("com.android.application") version "8.4.1" apply false
    id("org.jetbrains.kotlin.android") version "1.9.23" apply false
    id("com.google.gms.google-services") version "4.4.2" apply false
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(17))
    }
}

allprojects {
    repositories {
        google()
        mavenCentral()
    }
}
