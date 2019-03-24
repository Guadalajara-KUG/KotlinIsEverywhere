package net.sierisimo.js.app

import net.sierisimo.js.data.User
import net.sierisimo.js.doms.Events
import org.w3c.dom.HTMLInputElement
import kotlin.browser.document
import kotlinx.serialization.json.Json as JsonSerial

fun setupForm() {
    val button = document.getElementById("my-button")
    button?.addEventListener(Events.CLICK, {
        val user = getUserInfo()

        println("Hey: $user, you actually clicked!")

        printSerialized(user)
    })
}

fun getUserInfo(): User = User(nameInput.value, ageInput.value.toInt())

private val nameInput: HTMLInputElement by lazy {
    document.getElementById("my-name-box") as HTMLInputElement
}

private val ageInput: HTMLInputElement by lazy {
    document.getElementById("my-age-box") as HTMLInputElement
}

private fun printSerialized(user: User) {
    val serial = User.serializer()
    val jsonTxt = JsonSerial.indented.stringify(serial, user)

    println("JSON Indented --> $jsonTxt")

    val kotlinFromJson = JsonSerial.parse(serial, jsonTxt)
    println("Getting my object back --> $kotlinFromJson")

    val objFromJson: User = JSON.parse(jsonTxt)
    println("Works with native: $objFromJson")
}