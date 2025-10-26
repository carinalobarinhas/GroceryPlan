// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.kotlin.compose)
}

buildscript {
    configurations.getByName("classpath").resolutionStrategy {
        force("com.squareup:javapoet:1.13.0")
    }
}

allprojects {
    configurations.configureEach {
        resolutionStrategy.eachDependency {
            if (requested.group == "com.squareup" && requested.name == "javapoet") {
                useVersion("1.13.0")
                because("Hilt plugin needs JavaPoet >= 1.13.0 for ClassName.canonicalName().")
            }
        }
    }
}