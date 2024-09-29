package org.yournews

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import org.yournews.ui.MainScreen

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "YourNews",
    ) {
        MainScreen()
    }
}
