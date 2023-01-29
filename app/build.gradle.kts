plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-parcelize")
}

android {
    namespace = "aej.dino.netflixcloneapps"
    compileSdk = ConfigData.compileSdk

    val appVersion = ConfigData.getAppVersion(file("version.properties"), gradle)
    defaultConfig {
        applicationId = "aej.dino.netflixcloneapps"
        minSdk = ConfigData.minSdk
        targetSdk = ConfigData.targetSdk
        versionCode = appVersion.first
        versionName = appVersion.second

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility(JavaVersion.VERSION_1_8)
        targetCompatibility(JavaVersion.VERSION_1_8)
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = Versions.composeVersion
    }
    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    implementation(project(":Core"))
    addCommonLibraries()
    addComposeLibraries()
}