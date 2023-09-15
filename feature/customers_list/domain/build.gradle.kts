plugins {
    id("java-library")
    alias(libs.plugins.org.jetbrains.kotlin.jvm)
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}
dependencies {
    implementation(project(":feature:customers_list:api"))
    implementation(project(":core:network"))
    implementation(libs.koin.core)
    implementation(libs.coroutines)

    testImplementation(libs.junit)
    testImplementation(libs.mockk)
}
