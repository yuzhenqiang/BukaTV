package org.yzq.bukatv

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.bukatv.db.DatabaseDriverFactory
import com.bukatv.db.createDatabase

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "BukaTV",
    ) {
        val factory = DatabaseDriverFactory()
        val database = createDatabase(factory)
        App(database)
    }
}