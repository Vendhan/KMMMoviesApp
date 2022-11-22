plugins {
    with(Dependencies.Plugins) {
        kotlin(MULTIPLATFORM)
        kotlin(COCOAPODS)
        id(ANDROID_LIB)
        id(KOTLIN_X_SERIALIZATION)
        id(SQL_DELIGHT)
    }
}

version = "1.0"

android {
    compileSdk = 32
    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    defaultConfig {
        minSdk = 26
        targetSdk = 32
    }
}

kotlin {
    android()
    iosX64()
    iosArm64()
    iosSimulatorArm64()

    kotlin.targets.withType(org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget::class.java) {
        // export correct artifact to use all classes of library directly from Swift
        binaries.withType(org.jetbrains.kotlin.gradle.plugin.mpp.Framework::class.java).all {
            export("dev.icerock.moko:mvvm-core:0.13.1")
        }
        binaries.all {
            binaryOptions["memoryModel"] = "experimental"
        }
    }

    cocoapods {
        summary = "Some description for the Shared Module"
        homepage = "Link to the Shared Module homepage"
        ios.deploymentTarget = "14.1"
        podfile = project.file("../iosApp/Podfile")
        framework {
            baseName = "shared"
        }
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                with(Dependencies.Common) {
                    implementation(COROUTINES)
                    implementation(KOIN_CORE)
                    implementation(KTOR_JSON)
                    implementation(KTOR_LOGGING)
                    implementation(KTOR_CONTENT_NEGOTIATION)
                    implementation(SQL_DELIGHT_COROUTINES_EXT)
                    api(MOKO_MVVM)
                }
                with(Dependencies.ClassPaths) {
                    implementation(KOTLIN_SERIALIZATION)
                }
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
            }
        }
        val androidMain by getting {
            dependencies {
                with(Dependencies.Android) {
                    implementation(ANDROID_MATERIAL)
                    implementation(KOIN_ANDROID)
                    implementation(LIFECYCLE_VIEWMODEL)
                    implementation(SQL_DELIGHT)
                    implementation(KTOR)
                    implementation(KTOR_OKHTTP)
                }
            }
        }
        val androidTest by getting
        val iosX64Main by getting
        val iosArm64Main by getting
        val iosSimulatorArm64Main by getting
        val iosMain by creating {
            dependencies {
                with(Dependencies.IOS) {
                    implementation(KTOR)
                    implementation(SQL_DELIGHT)
                }
            }
            dependsOn(commonMain)
            iosX64Main.dependsOn(this)
            iosArm64Main.dependsOn(this)
            iosSimulatorArm64Main.dependsOn(this)
        }
        val iosX64Test by getting
        val iosArm64Test by getting
        val iosSimulatorArm64Test by getting
        val iosTest by creating {
            dependsOn(commonTest)
            iosX64Test.dependsOn(this)
            iosArm64Test.dependsOn(this)
            iosSimulatorArm64Test.dependsOn(this)
        }
    }
}

sqldelight {
    database("MoviesDB") {
        packageName = "com.vendhan.showtime.db"
    }
}
