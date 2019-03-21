@file:JvmName("Main")

package net.sierisimo.jvm

fun main(args: Array<String>) {
    printProperties()
    printArgs(args)
}

fun printProperties() {
    val props = System.getProperties()

    props.propertyNames().asSequence().forEach {
        println("$it --> ${props[it]}")
    }
}

fun printArgs(args: Array<String>) {
    println(args.joinToString(separator = "\n", prefix = "\n\nARGS:{\n", postfix = "}\n"))
}