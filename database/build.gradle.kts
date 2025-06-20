plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    id("androidx.room")
    id("com.google.devtools.ksp")
    id("dagger.hilt.android.plugin")
}

android {
    namespace = "com.nikhilproject.database"
    compileSdk = 35

    defaultConfig {
        minSdk = 24

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }

    room {
        schemaDirectory("$projectDir/schemas")
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    val room_version = "2.7.2"

    implementation("androidx.room:room-ktx:$room_version")
    // To use Kotlin annotation processing tool (kapt)
    ksp("androidx.room:room-compiler:$room_version")

    implementation("com.google.dagger:hilt-android:2.56.1")
//    kapt("com.google.dagger:hilt-android-compiler:2.56.1")

    implementation("androidx.hilt:hilt-navigation-compose:1.2.0")
//    kapt("androidx.hilt:hilt-compiler:1.2.0")

    ksp("com.google.dagger:hilt-android-compiler:2.56.1")
    ksp("com.google.dagger:hilt-compiler:2.56.1")
}