plugins {
  id("angular")
  war
}

tasks {
  task<Copy>("copy-angular-build") {
    group = "angular"
    dependsOn("angularBuild")
    from("${layout.projectDirectory}/dist/${project.name}")
    into("${layout.buildDirectory.get()}/webapp")
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

tasks.war {
  dependsOn("copy-angular-build")
  webAppDirectory.set(file("${layout.buildDirectory.get()}/webapp"))
}
