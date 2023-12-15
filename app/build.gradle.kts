plugins {
    id("com.android.application")
}

android {
    namespace = "be.ehb.charging_points_brussel"
    compileSdk = 34

    defaultConfig {
        applicationId = "be.ehb.charging_points_brussel"
        minSdk = 26
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
}

dependencies {

    implementation("com.squareup.okhttp3:okhttp:4.11.0")                // <-- for rest api dependency

    implementation("androidx.navigation:navigation-fragment:2.7.5")     // <-- for navigation dependency
    implementation("androidx.navigation:navigation-ui:2.7.5")           // <-- for navigation dependency
    val room_version = "2.6.1"                                          // <-- room dependency

    implementation("androidx.room:room-runtime:$room_version")          // <-- room dependency
    annotationProcessor("androidx.room:room-compiler:$room_version")    // <-- room dependency

    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.10.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
}