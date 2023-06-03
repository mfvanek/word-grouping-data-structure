rootProject.name = "word-grouping"

dependencyResolutionManagement {
    versionCatalogs {
        create("libs") {
            version("checkstyle", "10.12.0")
            version("pmd", "6.55.0")
            version("jacoco", "0.8.10")
        }
    }
}
