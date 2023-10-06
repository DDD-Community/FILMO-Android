import com.android.build.api.dsl.Packaging

/*
 * Copyright (C) 2022 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

@Suppress("DSL_SCOPE_VIOLATION") // Remove when fixed https://youtrack.jetbrains.com/issue/KTIJ-19369
plugins {
    id("filmo.android.application")
    id("filmo.android.application.compose")
    id("filmo.android.hilt")
    id("filmo.android.application.firebase")
    alias(libs.plugins.android.application)
    alias(libs.plugins.androidx.baselineprofile)
}

android {
    namespace = "com.ddd.filmo"

    defaultConfig {
        applicationId = "com.ddd.filmo"
        versionCode = 1
        versionName = "1.0"
        vectorDrawables {
            useSupportLibrary = true
        }

        // Enable room auto-migrations
//        ksp {
//            arg("room.schemaLocation", "$projectDir/schemas")
//        }
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro",
            )
        }
    }

    buildFeatures {
        compose = true
        aidl = false
        buildConfig = false
        renderScript = false
        shaders = false
    }

    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.androidxComposeCompiler.get()
    }

    packaging {
        resources.excludes.addAll(
            listOf(
                "META-INF/LICENSE.md",
                "META-INF/LICENSE-notice.md",
            ),
        )
    }
}

dependencies {
    implementation(project(":core-ui"))
    implementation(project(":feature-login:presentation"))
    implementation(project(":feature-mypage:presentation"))
    implementation(project(":feature-film:presentation"))
    implementation(project(":feature-scene:presentation"))
    implementation(project(":feature-setting:presentation"))

    // Core Android dependencies
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)

    // Hilt Dependency Injection
    implementation(libs.hilt.android)
    implementation(project(mapOf("path" to ":core-designsystem")))
    implementation(project(mapOf("path" to ":core-model")))
    implementation(project(":core-module"))
    implementation(project(mapOf("path" to ":feature-login:domain")))
    implementation(project(mapOf("path" to ":feature-film:domain")))
    implementation(libs.profileinstaller)
    "baselineProfile"(project(":app:baselineprofile"))
    kapt(libs.hilt.compiler)

    // Arch Components
    implementation(libs.androidx.lifecycle.runtime.compose)
    implementation(libs.androidx.lifecycle.viewmodel.compose)
    implementation(libs.androidx.navigation.compose)
    implementation(libs.androidx.hilt.navigation.compose)

    implementation(libs.androidx.compose.shape)
    // Compose
    val composeBom = platform(libs.androidx.compose.bom)
    implementation(composeBom)
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.compose.material3)
    implementation(libs.coil.kt.compose)
    // Tooling
    debugImplementation(libs.androidx.compose.ui.tooling)
    implementation(libs.toolbar.compose)
    implementation(libs.reveal.core)
    implementation(libs.reveal.shape)
    implementation(libs.reveal.shape)
    implementation(libs.reveal.compat.android)
}
