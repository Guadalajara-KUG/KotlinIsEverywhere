#!/usr/bin/env kscript

//DEPS com.github.ajalt:clikt:1.7.0

//ENTRY ArgumentsKt

import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.parameters.options.option
import com.github.ajalt.clikt.parameters.types.int
import com.github.ajalt.clikt.parameters.options.default
import com.github.ajalt.clikt.parameters.options.prompt

class ArgumentsCommand : CliktCommand() {
    val count: Int by option(help="Number of greetings").int().default(1)
    val name: String by option(help="The person to greet").prompt("Your name")

    override fun run() {
        for (i in 1..count) {
            echo("Hello $name!")
        }
    }
}

fun main(args: Array<String>) = ArgumentsCommand().main(args)