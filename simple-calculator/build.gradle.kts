plugins {
    `java-library`
}

repositories {
    mavenCentral();
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

dependencies {
    implementation(project(":api"))
    testImplementation("org.junit.jupiter:junit-jupiter:5.10.0")
}

tasks {
    test {
        useJUnitPlatform()
    }
}