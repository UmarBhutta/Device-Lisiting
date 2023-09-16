plugins {
    id("java-library")
    alias(libs.plugins.org.jetbrains.kotlin.jvm)
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_7
    targetCompatibility = JavaVersion.VERSION_1_7
}
dependencies {
    implementation(project(":feature:customer_details:api"))
    implementation(project(":core:network"))
    implementation(libs.koin.core)
    implementation(libs.coroutines)

    testImplementation(libs.junit)
    testImplementation(libs.mockk)
}
