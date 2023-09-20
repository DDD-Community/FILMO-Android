@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    id("filmo.android.library")
    id("filmo.android.hilt")
}

android {
    namespace = "com.ddd.filmo.core.module"
    defaultConfig {
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }
    buildFeatures {
        aidl = false
        buildConfig = false
        renderScript = false
        shaders = false
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(project(mapOf("path" to ":feature-film:data")))
    implementation(project(mapOf("path" to ":feature-film:domain")))
    implementation(project(mapOf("path" to ":feature-scene:data")))
    implementation(project(mapOf("path" to ":feature-scene:domain")))
    implementation(project(":feature-login:data"))
    implementation(project(":feature-login:domain"))
    implementation(project(":core-network"))
    testImplementation(libs.junit)
    implementation(libs.firebase.firestore.ktx)
    implementation(libs.retrofit)
    implementation(libs.okhttp)
    implementation(libs.okhttp.loggingInterceptor)
    implementation(libs.retrofit.serializationConverter)
    implementation(libs.serializationConverter)

}