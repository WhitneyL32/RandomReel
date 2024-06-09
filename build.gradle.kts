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
    id("com.android.application") version "8.4.1" apply false
    id("org.jetbrains.kotlin.android") version "1.9.23" apply false
}

allprojects {
    repositories {
        google()
        mavenCentral()
    }
}
