allprojects {
    group = "com.github.nyangzzi"
}

plugins {
    id (Plugins.ANDROID_APPLICATION) version Versions.AGP apply false
    id (Plugins.ANDROID_LIBRARY) version Versions.AGP apply false
    id (Plugins.KOTLIN_ANDROID) version Versions.KOTLIN apply false
    id("org.jetbrains.dokka") version "1.7.10"
    id("maven-publish") // Add maven-publish plugin
    java
    `java-library`
}

publishing {
    publications {
        create<MavenPublication>("maven-public") {
            groupId = "com.msg"
            artifactId = "library"
            version = "1.0.0"
            from(components["java"])
        }
    }
}