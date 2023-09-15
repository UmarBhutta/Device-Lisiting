pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "Device Listing"
include(":app")
include(":common")
include(":core")
include(":core:network")
include(":feature")
include(":feature:customers_list")
include(":feature:customers_list:api")
include(":feature:customers_list:domain")
include(":feature:customers_list:presentation")

include(":feature:customer_details")
include(":feature:customer_details:api")
include(":feature:customer_details:domain")
