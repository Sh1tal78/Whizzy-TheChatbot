plugins {
    id("com.android.application")
}




android {

    packagingOptions {
        resources {
            // Exclude duplicate files
            excludes.add("META-INF/DEPENDENCIES")
        }
    }
    namespace = "com.example.chatbotapplication"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.chatbotapplication"
        minSdk = 24
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        // Fetch the OpenAI API key from local.properties
        val apiKey = project.findProperty("apiKey") ?: ""
        buildConfigField("String", "API_KEY", "\"$apiKey\"")
    }

    buildFeatures {
        buildConfig = true // Enable BuildConfig
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

dependencies {
    // Android core libraries
    implementation("androidx.appcompat:appcompat:1.7.0")
    implementation("com.google.android.material:material:1.12.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")

    // Lottie for animations
    implementation("com.airbnb.android:lottie:6.5.2")

    // Circle Image View library
    implementation("de.hdodenhof:circleimageview:3.1.0")

    // OkHttp and Retrofit for network calls (OpenAI API integration)
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation("com.squareup.okhttp3:logging-interceptor:4.9.0")

    // Gson for JSON parsing
    implementation("com.google.code.gson:gson:2.10")

    // Testing libraries
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.2.1")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.6.1")

    // Volley for network requests (if needed)
    implementation("com.android.volley:volley:1.2.1")
    implementation("com.squareup.okhttp3:okhttp:4.12.0")
    implementation("com.google.ai.client.generativeai:generativeai:0.2.2")
    // Include the Gax library for Google API extensions
    implementation ("com.google.api:gax:2.1.0")
    implementation ("com.google.api:gax-grpc:2.1.0")

    // Include Google's Guava library for ListenableFuture


    // Google API Client Libraries
    implementation ("com.google.api:gax:2.7.1") // Check for the latest version

    // Google Guava for concurrency utilities
    implementation ("com.google.guava:guava:31.0.1-android") // Use the latest or -android variant for Android compatibility

    // If Google Cloud Gemini is a real service and has a client library, include it


}
