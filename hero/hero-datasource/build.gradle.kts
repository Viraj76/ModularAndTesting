plugins {
    id("java-library")
    alias(libs.plugins.jetbrains.kotlin.jvm)
    id("io.ktor.plugin") version "2.3.11"

    kotlin("plugin.serialization") version "2.0.0"
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}


dependencies {

    implementation(project(":hero:hero-domain"))
    implementation(libs.ktor.server.core)
    implementation(libs.ktor.serialization.kotlinx.json) // impe one
    implementation(libs.ktor.client.core) // imp one
    implementation(libs.ktor.client.android)
    implementation(libs.firebase.crashlytics.buildtools) // imp one
    implementation(libs.ktor.client.content.negotiation)

}
