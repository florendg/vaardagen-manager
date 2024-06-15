plugins {
    `java-library`
    `jvm-test-suite`
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("jakarta.platform:jakarta.jakartaee-api:10.0.0")
    implementation(project(":api"))
}

testing {
    suites {
        val test by getting(JvmTestSuite::class) {
            useJUnitJupiter()
        }
    }
}
java {
    toolchain {
        languageVersion = libs.versions.jvmToolChain.map {
            JavaLanguageVersion.of(it)
        }
        vendor = JvmVendorSpec.ADOPTIUM
    }
}