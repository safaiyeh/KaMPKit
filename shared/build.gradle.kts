import org.jetbrains.kotlin.gradle.plugin.mpp.Framework
import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget

plugins {
    kotlin("multiplatform")
    kotlin("native.cocoapods")
    id("kotlinx-serialization")
    id("com.android.library")
    id("com.squareup.sqldelight")
}

android {
    compileSdk = libs.versions.compileSdk.get().toInt()
    defaultConfig {
        minSdk = libs.versions.minSdk.get().toInt()
        targetSdk = libs.versions.targetSdk.get().toInt()
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    lint {
        isWarningsAsErrors = true
        isAbortOnError = true
    }
}

version = "1.0"

android {
    configurations {
        create("androidTestApi")
        create("androidTestDebugApi")
        create("androidTestReleaseApi")
        create("testApi")
        create("testDebugApi")
        create("testReleaseApi")
    }
}

kotlin {
    android()
    ios()
    js(IR) {
        binaries.library()
        nodejs()
    }

    version = "1.1"

    sourceSets {
        all {
            languageSettings.apply {
                useExperimentalAnnotation("kotlin.RequiresOptIn")
                useExperimentalAnnotation("kotlinx.coroutines.ExperimentalCoroutinesApi")
            }
        }
    }
    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(libs.coroutines.core)
                implementation(libs.sqlDelight.coroutinesExt)
                implementation(libs.touchlab.stately)
                implementation(libs.multiplatformSettings.common)
                api(libs.touchlab.kermit)
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(libs.bundles.shared.commonTest)
            }
        }
        val mobileMain by creating {
            dependsOn(commonMain)
            dependencies {
                implementation(libs.bundles.ktor.common)
                implementation(libs.koin.core)
                implementation(libs.kotlinx.dateTime)
            }
        }
        val mobileTest by creating {
            dependsOn(commonTest)
        }
        val androidMain by getting {
            dependsOn(mobileMain)
            dependencies {
                implementation(libs.sqlDelight.android)
                implementation(libs.ktor.client.okHttp)
            }
        }
        val androidTest by getting {
            dependsOn(mobileTest)
            dependencies {
                implementation(libs.bundles.shared.androidTest)
            }
        }
        val iosMain by getting {
            dependsOn(mobileMain)
            dependencies {
                implementation(libs.sqlDelight.native)
                implementation(libs.ktor.client.ios)
                implementation(libs.coroutines.core)
                val coroutineCore = libs.coroutines.core.get()
                implementation("${coroutineCore.module.group}:${coroutineCore.module.name}:${coroutineCore.versionConstraint.displayName}") {
                    version {
                        strictly(libs.versions.coroutines.native.get())
                    }
                }
            }
        }
        val iosTest by getting {
            dependsOn(mobileTest)
        }
        val jsMain by getting
        val jsTest by getting {
            dependencies {
                implementation(kotlin("test-js"))
            }
        }
    }

    sourceSets.matching { it.name.endsWith("Test") }
        .configureEach {
            languageSettings.useExperimentalAnnotation("kotlin.time.ExperimentalTime")
        }

    cocoapods {
        summary = "Common library for the KaMP starter kit"
        homepage = "https://github.com/touchlab/KaMPKit"
    }

    // Configure the framework which is generated internally by cocoapods plugin
    targets.withType<KotlinNativeTarget> {
        binaries.withType<Framework> {
            isStatic = false // SwiftUI preview requires dynamic framework
            export(libs.touchlab.kermit)
            transitiveExport = true
        }
    }
}

sqldelight {
    database("KaMPKitDb") {
        packageName = "co.touchlab.kampkit.db"
    }
}
