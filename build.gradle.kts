buildscript {
    dependencies {
        classpath("com.github.dcendents:android-maven-gradle-plugin:2.1")
        //classpath("org.jetbrains.dokka:dokka-gradle-plugin:1.7.10")
    }
}

allprojects {
    group = "com.github.nyangzzi"
}

plugins {
    id (Plugins.ANDROID_APPLICATION) version Versions.AGP apply false
    id (Plugins.ANDROID_LIBRARY) version Versions.AGP apply false
    id (Plugins.KOTLIN_ANDROID) version Versions.KOTLIN apply false
    id("org.jetbrains.dokka") version "1.7.10"
}