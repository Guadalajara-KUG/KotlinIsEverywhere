tasks {
    val runReact by registering(Exec::class) {
        commandLine("npm", "start")
    }
}