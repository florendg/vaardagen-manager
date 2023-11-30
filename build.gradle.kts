plugins {
    application
}

repositories {
    mavenCentral();
}

val appClassName = "dev.vultureweb.vaardagen.App"
val appModuleName = "dev.vultureweb.vaardagen"
val javaVersion  = libs.versions.jvmToolChain.map {
    JavaLanguageVersion.of(it)
}
val javaFxVersion = libs.versions.javaFxVersion.get()
val compiler = javaToolchains.compilerFor {
    languageVersion.set(javaVersion)
}
val buildDir = project.layout.buildDirectory.get()

var currentOS = org.gradle.nativeplatform.platform.internal.DefaultNativePlatform.getCurrentOperatingSystem()
var platform = ""
if (currentOS.isMacOsX) {
    platform = "mac"
} else if (currentOS.isLinux) {
    platform = "linux"
} else if (currentOS.isWindows) {
    platform = "win"
}

dependencies {
    implementation(project(":api"))
    implementation("org.openjfx:javafx-base:${javaFxVersion}:${platform}")
    implementation("org.openjfx:javafx-controls:${javaFxVersion}:${platform}")
    implementation("org.openjfx:javafx-graphics:${javaFxVersion}:${platform}")
    implementation("org.openjfx:javafx-fxml:${javaFxVersion}:${platform}")
}

application {
    // Define the main class for the application.
    mainModule.set(appModuleName)
    mainClass.set(appClassName)
    if(platform.equals("mac")) {
        applicationDefaultJvmArgs = listOf("-Dsun.java2d.metal=true")
    }
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
        into("${buildDir}/modules")
    }

    task<Exec>("package") {
        dependsOn(listOf("build", "copyDependencies"))
        val jdkHome = compiler.get().metadata.installationPath.asFile.absolutePath
        commandLine("${jdkHome}/bin/jpackage")
        args(listOf(
                "-n", "vaardagenManager",
                "-p", "$buildDir/modules"+File.pathSeparator+"${buildDir}/libs",
                "-d", "$buildDir/installer",
                "-m", "${appModuleName}/${appClassName}",
                "--verbose"))
    }

}