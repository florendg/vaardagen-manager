import com.github.gradle.node.yarn.task.YarnTask

plugins {
  id("com.github.node-gradle.node") version "7.0.2"
  war
}

node {
  download = true
  version="20.17.0"
}

tasks {

  task<YarnTask>("yarnUpgrade") {
    group = "angular"
    args = listOf("upgrade")
  }

  task<YarnTask>("angularBuild") {
    group = "angular"
    dependsOn("yarnUpgrade")
    args = listOf("run", "build")
  }

  task<Copy>("copy-angular-build") {
    group = "angular"
    dependsOn("angularBuild")
    from("${layout.projectDirectory}/dist/${project.name}/browser")
    into("${layout.buildDirectory.get()}/webapp")
  }

  task<YarnTask>("angularTest") {
    group = "verification"
    dependsOn("yarn")
    args = listOf("test")
  }

  task<Copy>("deploy") {
    group = "build"
    dependsOn("war")
    from("${layout.buildDirectory.get()}/libs/${project.name}.war")
    into("${System.getenv("JBOSS_HOME")}/standalone/deployments")
  }

  task("cleanDist") {
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
  dependsOn("copy-angular-build")
  webAppDirectory.set(file("${layout.buildDirectory.get()}/webapp"))
}
