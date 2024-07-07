pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
        maven {
            url = uri("https://jitpack.io")
        }
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven {
            url = uri("https://jitpack.io")
        }
    }
}

rootProject.name = "LPG"
include(":app")
include(":analytics")
include(":framework")
include(":entities")
include(":di")
include(":history")
include(":core")
include(":history:data")
include(":history:domain")
include(":history:usecase")
include(":cart")
include(":cart:data")
include(":cart:domain")
include(":cart:usecase")
include(":home")
include(":home:data")
include(":home:domain")
include(":home:usecase")
include(":buy")
include(":buy:domain")
include(":buy:data")
include(":buy:usecase")
include(":profile")
include(":profile:data")
include(":profile:domain")
include(":profile:usecase")
