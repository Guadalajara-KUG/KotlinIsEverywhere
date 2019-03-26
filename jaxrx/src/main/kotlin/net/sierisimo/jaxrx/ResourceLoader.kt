package net.sierisimo.jaxrx

import javax.ws.rs.core.Application

class ResourceLoader : Application() {
    override fun getClasses() = hashSetOf(HelloWorldController::class.java)
}