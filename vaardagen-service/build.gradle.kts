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
}

java {
    toolchain {
        languageVersion = libs.versions.jvmToolChain.map {
            JavaLanguageVersion.of(it)
        }
        vendor = JvmVendorSpec.ADOPTIUM
    }
}


testing {
    suites {
        dependencies {
            testImplementation("com.fasterxml.jackson.core:jackson-databind:2.16.0")
            testImplementation("com.fasterxml.jackson.datatype:jackson-datatype-jsr310:2.16.0")
            testImplementation("io.rest-assured:rest-assured:5.5.0")
        }
        val test by getting(JvmTestSuite::class) {
            useJUnitJupiter()
        }
        register<JvmTestSuite>("integrationTest") {
            dependencies {
                implementation("io.rest-assured:rest-assured:5.5.0")
                implementation(project(":api"))
            }
            sources {
                java {
                    setSrcDirs(listOf("src/it/java"))
                }
            }
        }
    }
}

tasks {
    register<Copy>("deploy") {
        group = "build"
        dependsOn("war")
        from("${layout.buildDirectory.get()}/libs/${project.name}.war")
        into("${System.getenv("JBOSS_HOME")}/standalone/deployments")
    }
}

