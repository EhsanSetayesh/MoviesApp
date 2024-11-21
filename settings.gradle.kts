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
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

/**
 * Enables direct module dependency addition without specifying the full path.
 * This method simplifies the addition of project modules to a build script by allowing
 * the use of a shorthand notation. Instead of using the complete path, modules can
 * be added using a simplified syntax.
 *
 * For example, to add the module ':core:common', you can use:
 * {@code implementation(projects.core.common)}
 *
 * This approach enhances readability and reduces the complexity of build scripts,
 * making it easier to manage project dependencies.
 */
enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

rootProject.name = "MoviesApp"
include(":app")
include(":core:netwok")
include(":core:test:unit")
include(":core:test:ui")
include(":feature:movieslist")
include(":feature:moviedetails")
include(":core:common")
include(":core:featureflag")
include(":shared:movies:common")
include(":core:designsystem:compose")
include(":core:designsystem:resource")
include(":feature:navigation")
