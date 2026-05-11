plugins {
    id("java-library")
    id("org.jetbrains.kotlin.jvm")
}

java {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
    kotlinOptions {
        jvmTarget = "11"
    }
}

dependencies {
    compileOnly("com.android.tools.lint:lint-api:31.3.0")
    compileOnly("com.android.tools.lint:lint-checks:31.3.0")
}

tasks.withType<Jar> {
    manifest {
        attributes(
            "Lint-Registry-v2" to "com.hyundaiht.lint.KeepAnnotationRegistry"
        )
    }
}
