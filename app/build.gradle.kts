plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.google.devtools.ksp")
    id("androidx.navigation.safeargs")
}
android {
    namespace = "com.gohand.criminalintent"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.gohand.criminalintent"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
        viewBinding = true
    }
}

dependencies {
    implementation("androidx.appcompat:appcompat:1.7.0")
    implementation("androidx.core:core-ktx:1.13.1")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    // Material Design
    implementation("com.google.android.material:material:1.12.0")

    // Architectural Components
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.8.1")

    // Room
    implementation("androidx.room:room-runtime:2.6.1")
    ksp("androidx.room:room-compiler:2.6.1")

    // Kotlin Extensions and Coroutines support for Room
    implementation("androidx.room:room-ktx:2.6.1")

    // Coroutines
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.3")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.3")

    // Coroutine Lifecycle Scopes
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.8.1")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.8.1")

    // Navigation Components
    implementation("androidx.navigation:navigation-fragment-ktx:2.7.7")
    implementation("androidx.navigation:navigation-ui-ktx:2.7.7")

    // Glide
    implementation("com.github.bumptech.glide:glide:4.11.0")
    ksp("com.github.bumptech.glide:compiler:4.11.0")

    // Google Maps Location Services
    implementation("com.google.android.gms:play-services-location:21.3.0")
    implementation("com.google.android.gms:play-services-maps:18.2.0")

    // Dagger Core
    implementation("com.google.dagger:dagger:2.51")
    ksp("com.google.dagger:dagger-compiler:2.25.2")

    // Dagger Android
    api("com.google.dagger:dagger-android:2.28.1")
    api("com.google.dagger:dagger-android-support:2.28.1")
    ksp("com.google.dagger:dagger-android-processor:2.23.2")

    // Easy Permissions
    implementation("pub.devrel:easypermissions:3.0.0")

    // Timber
    implementation("com.jakewharton.timber:timber:4.7.1")

    // MPAndroidChart
    implementation("com.github.PhilJay:MPAndroidChart:v3.1.0")

    implementation("androidx.lifecycle:lifecycle-extensions:2.2.0")
}
