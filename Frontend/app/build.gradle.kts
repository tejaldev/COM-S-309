plugins {
    id("com.android.application")
}

android {
    namespace = "com.example.globegatherer"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.globegatherer"
        minSdk = 33
        targetSdk = 33
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
}

dependencies {

    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.10.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("com.google.android.gms:play-services-maps:18.2.0")
    implementation("com.google.android.play:core:1.10.3")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    implementation("com.android.volley:volley:1.2.1")
    implementation("com.google.android.material:material:1.10.0")
    implementation ("org.java-websocket:Java-WebSocket:1.5.1")
    implementation ("com.google.api-client:google-api-client:1.30.9")
    implementation ("com.google.api-client:google-api-client:1.33.0")
    implementation ("com.google.oauth-client:google-oauth-client:1.33.1")
    implementation ("com.google.apis:google-api-services-calendar:v3-rev305-1.25.0")

}