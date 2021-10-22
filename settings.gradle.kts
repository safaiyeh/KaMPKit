pluginManagement {
    repositories {
        mavenCentral()
        gradlePluginPortal()
    }
}
include(":common")
include(":app", ":shared")
rootProject.name = "KaMPKit"

enableFeaturePreview("VERSION_CATALOGS")