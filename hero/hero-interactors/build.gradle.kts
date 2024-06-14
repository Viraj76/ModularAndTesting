plugins {
    id("java-library")
    alias(libs.plugins.jetbrains.kotlin.jvm)
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

dependencies{
    implementation(project(":core"))
    implementation(project(":hero:hero-datasource"))
    implementation(project(":hero:hero-domain"))

    implementation(libs.kotlinx.coroutines.flow)
    implementation(libs.kotlinx.coroutines.core)

}