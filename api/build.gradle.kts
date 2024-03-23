plugins {
    `java-library`
}

java {
    toolchain {
        languageVersion = libs.versions.jvmToolChain.map {
            JavaLanguageVersion.of(it)
        }
        vendor = JvmVendorSpec.ORACLE
    }
    modularity.inferModulePath.set(true)
}