import com.github.gradle.node.pnpm.task.PnpmTask

plugins {
  id("com.github.node-gradle.node") version "7.1.0"
  war
}

repositories {
  mavenCentral()
}

node {
  download = true
  version="26.4.0"
}

java {
  toolchain {
    languageVersion = libs.versions.jvmToolChain.map {
      JavaLanguageVersion.of(it)
    }
    vendor = JvmVendorSpec.ADOPTIUM
  }
}

dependencies {
  providedCompile("jakarta.platform:jakarta.jakartaee-api:10.0.0")
}

tasks {

  register<PnpmTask>("pnpmUpgrade") {
    group = "angular"
    args = listOf("upgrade")
  }

  register<PnpmTask>("angularBuild") {
    group = "angular"
    dependsOn("pnpmUpgrade")
    args = listOf("run", "build")
  }

  register<PnpmTask>("angularTest") {
    group = "verification"
    dependsOn("pnpmInstall")
    args = listOf("test")
  }

  register<Copy>("deploy") {
    group = "build"
    dependsOn("war")
    from("${layout.buildDirectory.get()}/libs/${project.name}.war")
    into("${System.getenv("JBOSS_HOME")}/standalone/deployments")
  }

  register("cleanDist") {
    doLast {
      file("${layout.projectDirectory}/dist").deleteRecursively()
    }
  }
}

tasks.clean {
  dependsOn("cleanDist")
}

tasks.test {
  dependsOn("angularTest")
}

tasks.war {
  dependsOn("angularBuild")
  from("${layout.projectDirectory}/dist/${project.name}")
}
