plugins {
    war
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("jakarta.platform:jakarta.jakartaee-api:9.1.0")
    implementation(project(":api"))
    implementation(project(":simple-calculator"))
}


testing {
    suites {
        dependencies {
            testImplementation("com.fasterxml.jackson.core:jackson-databind:2.16.0")
            testImplementation("com.fasterxml.jackson.datatype:jackson-datatype-jsr310:2.16.0")
        }
        val test by getting(JvmTestSuite::class) {
            useJUnitJupiter("5.9.0")
        }
    }
}

