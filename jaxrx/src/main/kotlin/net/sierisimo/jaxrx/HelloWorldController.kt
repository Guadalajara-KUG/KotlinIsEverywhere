package net.sierisimo.jaxrx

import javax.ws.rs.GET
import javax.ws.rs.Path
import javax.ws.rs.Produces
import javax.ws.rs.core.MediaType


@Path("/hello")
class HelloWorldController {
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    fun sayHello(): String = "Hello World from Tomcat Embedded with Jersey!"

}