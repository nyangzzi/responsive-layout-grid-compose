buildscript {
    dependencies {
        classpath("com.github.dcendents:android-maven-gradle-plugin:2.1")
    }
}

plugins {
    id (Plugins.ANDROID_APPLICATION) version Versions.AGP apply false
    id (Plugins.ANDROID_LIBRARY) version Versions.AGP apply false
    id (Plugins.KOTLIN_ANDROID) version Versions.KOTLIN apply false
}