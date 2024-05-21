plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.kotlin.android.ksp)
    alias(libs.plugins.dagger.hilt.android)
}

android {
    namespace = "com.arcadone.pokestorie"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.arcadone.pokestorie"
        minSdk = 30
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            signingConfig = signingConfigs.getByName("debug")
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
    implementation(libs.junit)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui)
    debugImplementation(libs.androidx.ui.graphics)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.tooling.preview)
    debugImplementation(libs.androidx.ui.test.manifest)
    implementation(libs.accompanist.pager)
    implementation(libs.accompanist.systemuicontroller)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.material3)
    implementation(libs.androidx.navigation)
    implementation(libs.coil)
    implementation(libs.coil.svg)
    implementation(libs.coil.gif)
    implementation(libs.retrofit)
    implementation(libs.retrofit.converter.jackson)
    implementation(libs.fasterxml.jackson)
    implementation(libs.retrofit.converter.scalars)
    implementation(libs.converter.gson)
    implementation(libs.okhttp)
    implementation(libs.logging.interceptor)
    implementation(libs.hilt.android)
    ksp(libs.hilt.compiler)
    implementation(libs.hilt.navigation)

    testImplementation(libs.mockito.core)
    testImplementation(libs.mockito.kotlin)
    testImplementation(libs.core.testing)
    testImplementation(libs.kotlinx.coroutines.test)
    androidTestImplementation(libs.android.test.core)
    androidTestImplementation(libs.mockwebserver)
    androidTestImplementation(libs.converter.gson)
}

