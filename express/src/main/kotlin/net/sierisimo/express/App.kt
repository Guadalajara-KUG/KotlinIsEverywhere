package net.sierisimo.express

external fun require(module: String): dynamic

fun main() {
    println("Hello Node")

    val express = require("express")
    val app = express()

    app.get("/hello") { req, res ->
        res.type("text/plain")
        res.send("Hello Express Server!")
    }

    app.listen(9000) {
        println("Server running :: port 9000")
    }
}