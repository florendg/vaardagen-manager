plugins {
    war
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("jakarta.platform:jakarta.jakartaee-api:10.0.0")
    implementation("com.fasterxml.jackson.core:jackson-databind:2.18.0")
    implementation("com.fasterxml.jackson.datatype:jackson-datatype-jsr310:2.18.0")
    implementation(project(":api"))
    implementation(project(":vaardagen-persistence"))
    implementation(project(":simple-calculator"))
}

java {
    toolchain {
        languageVersion = libs.versions.jvmToolChain.map {
            JavaLanguageVersion.of(it)
        }
        vendor = JvmVendorSpec.ADOPTIUM
    }
    modularity.inferModulePath.set(true)
}


testing {
    suites {
        dependencies {
            testImplementation("com.fasterxml.jackson.core:jackson-databind:2.16.0")
            testImplementation("com.fasterxml.jackson.datatype:jackson-datatype-jsr310:2.16.0")
            testImplementation("io.rest-assured:rest-assured:5.5.0")
        }
        val test by getting(JvmTestSuite::class) {
            useJUnitJupiter("5.10.0")
        }
        val integrationTest by registering(JvmTestSuite::class) {
            dependencies {
                implementation("io.rest-assured:rest-assured:5.5.0")
                implementation(project(":api"))
            }
            testType = TestSuiteType.INTEGRATION_TEST
            useJUnitJupiter("5.10.0")
            sources {
                java {
                    setSrcDirs(listOf("src/it/java"))
                }
            }
        }
    }
}

