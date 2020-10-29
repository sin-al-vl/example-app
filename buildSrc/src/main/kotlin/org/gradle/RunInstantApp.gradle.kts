package org.gradle

open class RunInstantApp: Exec() {

    @Input
    @Option(option = "androidSdk", description = "Android SDK path")
    lateinit var androidSdkPath: String

    @Input
    @Option(option = "apksPath", description="App apk splits path")
    lateinit var apksPath: String

    @Input
    @Optional
    @Option(option = "url", description = "Url to launch Instant App")
    var url: String? = null

    @Override
    override fun exec() {
        commandLine = if(url != null) {
            listOf(getIaPath(), "--debug", "run", apksPath, "-u", url)
        } else {
            listOf(getIaPath(), "--debug", "run", apksPath)
        }

        println("Running ia:\n${commandLine.joinToString(" ")}")

        super.exec()
    }

    private fun getIaPath() = "${androidSdkPath}/extras/google/instantapps/ia"
}