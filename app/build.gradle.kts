import java.util.Properties

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.hilt)
    id("kotlin-kapt")
    id("kotlin-parcelize")
}

android {
    namespace = "com.example.demonewsapp"
    compileSdk = 35

    val secretsPropertiesFile = rootProject.file("secrets.properties")
    val secretsProperties = Properties()
    secretsProperties.load(secretsPropertiesFile.inputStream())

    defaultConfig {
        applicationId = "com.example.demonewsapp"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }

        // Чтение ключа из файла
        val apiKey = secretsProperties["API_KEY"] as? String
        if (apiKey.isNullOrEmpty()) {
            throw GradleException("API_KEY не найден в secrets.properties")
        }

        // Добавление ключа в BuildConfig
        buildConfigField("String", "API_KEY", "\"$apiKey\"")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        buildConfig = true
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
    implementation(libs.androidx.datastore.preferences)
    implementation(libs.retrofit)
    implementation(libs.androidx.hilt.navigation.compose)
    implementation(libs.converter.gson)
    implementation(libs.coil.compose)
    implementation(libs.androidx.foundation)
    implementation(libs.accompanist.systemuicontroller)
    implementation(libs.androidx.paging.runtime)
    implementation(libs.androidx.paging.compose)
    implementation(libs.androidx.room.runtime)
    kapt(libs.room.compiler)
    implementation(libs.androidx.room.ktx)
    implementation(libs.splash.api)
    implementation(libs.hilt.core)
    kapt(libs.hilt.compiler)
    implementation(libs.navigation.compose)
}
