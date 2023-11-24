plugins {
    application
}

repositories {
    mavenCentral();
}

dependencies {
    implementation(project(":api"))
}

val appClassName = "dev.vultureweb.vaardagen.App"
val appModuleName = "dev.vultureweb.vaardagen"
val javaVersion  = libs.versions.jvmToolChain.map {
    JavaLanguageVersion.of(it)
}
val compiler = javaToolchains.compilerFor {
    languageVersion.set(javaVersion)
}

application {
    // Define the main class for the application.
    mainModule.set(appModuleName)
    mainClass.set(appClassName)
}

java {
    toolchain {
        languageVersion = javaVersion
        vendor = JvmVendorSpec.ADOPTIUM
    }
    modularity.inferModulePath.set(true)
}

tasks {
    task<Copy>("copyDependencies") {
        from(configurations.runtimeClasspath)
        into("$buildDir/modules")
    }

    task<Exec>("package") {
        dependsOn(listOf("build", "copyDependencies"))
        val jdkHome = compiler.get().metadata.installationPath.asFile.absolutePath
        commandLine("${jdkHome}/bin/jpackage")
        args(listOf(
                "-n", "vaardagenManager",
                "-p", "$buildDir/modules"+File.pathSeparator+"$buildDir/libs",
                "-d", "$buildDir/installer",
                "-m", "${appModuleName}/${appClassName}"))
    }

}