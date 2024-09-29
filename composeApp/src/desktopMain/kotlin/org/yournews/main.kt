package org.yournews

import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowPosition
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import org.yournews.ui.MainScreen
import java.awt.GraphicsEnvironment

fun main() = application {
    val graphicsEnvironment = GraphicsEnvironment.getLocalGraphicsEnvironment()
    val screenDevices = graphicsEnvironment.screenDevices

        Window(
            onCloseRequest = ::exitApplication,
            title = "YourNews",
            state = rememberWindowState(
                position = WindowPosition(
                    screenDevices[0].defaultConfiguration.bounds.x.dp,
                    screenDevices[0].defaultConfiguration.bounds.y.dp
                ),
                size = DpSize(
                    screenDevices[0].defaultConfiguration.bounds.width.dp,
                    screenDevices[0].defaultConfiguration.bounds.height.dp
                )
            )
        ) {
            MainScreen()
        }
}
