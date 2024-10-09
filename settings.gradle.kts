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

rootProject.name = "Gas and Safety"
include(":app")
include(":analytics")
include(":databases")
include(":splash")
include(":core:assets")
include(":core:common")
include(":core:framework")
include(":home:domain")
include(":home:data")
include(":home:presentation")
include(":cart:domain")
include(":cart:data")
include(":cart:presentation")
include(":address:domain")
include(":address:data")
include(":address:presentation")
include(":order:domain")
include(":order:data")
include(":order:presentation")
include(":setting")
include(":history:data")
include(":history:domain")
include(":history:presentation")
