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
include(":core")
include(":adminaddproducts")
include(":adminhome")
include(":adminorders")
include(":adminproductdetails")
include(":adminproducts")
include(":userbuy")
include(":usercart")
include(":userorderhistory")
include(":userhome")
include(":useraddress")
include(":common")
