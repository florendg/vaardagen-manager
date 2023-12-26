
val angularBuild by tasks.registering(Exec::class) {
    group = "angular"
    commandLine("yarn", "ng", "build","--base-href", "/${project.name}/")
    workingDir("${project.layout.projectDirectory}")
}

val angularServe by tasks.registering(Exec::class) {
    group = "angular"
    commandLine("yarn", "ng", "serve")
    workingDir("${project.layout.projectDirectory}")
}

val angularTest by tasks.registering(Exec::class) {
    group = "angular"
    commandLine("yarn", "ng", "test")
    workingDir("${project.layout.projectDirectory}")
}