import com.github.gradle.node.yarn.task.YarnTask

plugins {
  id("com.github.node-gradle.node") version "7.1.0"
  war
}

repositories {
  mavenCentral()
}

node {
  download = true
  version="22.14.0"
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

  register<YarnTask>("yarnUpgrade") {
    group = "angular"
    args = listOf("upgrade")
  }

  register<YarnTask>("angularBuild") {
    group = "angular"
    dependsOn("yarnUpgrade")
    args = listOf("run", "build")
  }

  register<YarnTask>("angularTest") {
    group = "verification"
    dependsOn("yarn")
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
