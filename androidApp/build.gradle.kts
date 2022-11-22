plugins {
    id("com.android.application")
    kotlin("android")
    id("kotlin-kapt")
}

android {
    compileSdk = 32
    defaultConfig {
        applicationId = "com.vendhan.kmmmovies.android"
        minSdk = 26
        targetSdk = 32
        versionCode = 1
        versionName = "1.0"
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.3.0"
    }
}

dependencies {
    implementation(project(":shared"))
    with(Dependencies.Android) {
        implementation(KOIN_ANDROID)
        implementation(KOIN_COMPOSE)
        implementation(COIL)
        with(Dependencies.Android.Compose) {
            implementation(COMPOSE_ACTIVITY)
            implementation(COMPOSE_UI)
            implementation(COMPOSE_MATERIAL)
            implementation(COMPOSE_UI_TOOLING)
            implementation(COMPOSE_NAVIGATION)
        }
    }
}
