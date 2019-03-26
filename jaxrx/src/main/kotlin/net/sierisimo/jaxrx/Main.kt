@file:JvmName("Main")

package net.sierisimo.jaxrx

import org.apache.catalina.startup.Tomcat
import java.io.File

fun main() {
    val webappDirLocation = "web"

    val tomcat = Tomcat().also {
        it.setPort(8080)

        val context = it.addWebapp("", File(webappDirLocation).absolutePath)
        val configFile = File("$webappDirLocation/WEB-INF/web.xml");
        context.configFile = configFile.toURI().toURL()

        it.start()
        it.server.await()
    }
}