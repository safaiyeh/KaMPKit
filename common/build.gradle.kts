plugins {
    kotlin("multiplatform")
    id("com.android.library")
}

version = "1.0.0"

android {
    compileSdk = libs.versions.compileSdk.get().toInt()
    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    defaultConfig {
        minSdk = libs.versions.minSdk.get().toInt()
        targetSdk = libs.versions.targetSdk.get().toInt()
    }
}

kotlin {
    android()
    ios()
    js(IR) {
        binaries.library()
        nodejs()
    }

    sourceSets {
        val commonMain by getting
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test-common"))
                implementation(kotlin("test-annotations-common"))
            }
        }
        val mobileMain by creating {
            dependsOn(commonMain)
        }
        val mobileTest by creating {
            dependsOn(commonTest)
        }
        val androidMain by getting {
            dependsOn(mobileMain)
        }
        val androidTest by getting {
            dependsOn(mobileTest)
            dependencies {
                implementation(kotlin("test-junit"))
                implementation("junit:junit:4.13.2")
            }
        }
        val iosMain by getting {
            dependsOn(mobileMain)
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
    sourceSets {
        all {
            languageSettings.apply {
                useExperimentalAnnotation("kotlinx.serialization.ExperimentalSerializationApi")
                useExperimentalAnnotation("kotlin.js.ExperimentalJsExport")
                useExperimentalAnnotation("kotlin.RequiresOptIn")
                useExperimentalAnnotation("kotlin.ExperimentalMultiplatform")
            }
        }
    }
}
