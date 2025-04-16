plugins {
    id("java-library")
    alias(libs.plugins.jetbrains.kotlin.jvm)
//    kotlin("kapt")
//    alias(libs.plugins.hilt)
}
java {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
}
kotlin {
    compilerOptions {
        jvmTarget = org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_11
    }
}
dependencies {
    implementation(libs.androidx.room.common.jvm)

//    kapt(libs.hilt.android.compiler)
}
