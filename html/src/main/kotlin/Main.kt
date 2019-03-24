import kotlinx.html.div
import kotlinx.html.dom.create
import org.w3c.dom.HTMLElement
import kotlin.browser.document
import kotlin.js.Date

fun main() {
    val rootDiv = document.getElementById("root") as HTMLElement
    rootDiv.append(buildNav())
}

fun buildNav(): HTMLElement {
    return document.create.div("nav-grid") {
        div("nav-grid-item-logo to-center") {
            +"LOGO"
        }

        div("nav-grid-item-end to-right") {
            text(Date().toDateString())
            attributes["sier"] = "Hello"
        }
    }
}