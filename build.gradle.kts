buildscript {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }
    dependencies {
        with(Dependencies.ClassPaths) {
            classpath(KOTLIN_GRADLE_PLUGIN)
            classpath(ANDROID_GRADLE_PLUGIN)
            classpath(SQL_DELIGHT)
        }
        classpath(kotlin("serialization", version = Versions.KOTLIN))
    }
}

allprojects {
    repositories {
        google()
        mavenCentral()
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}
