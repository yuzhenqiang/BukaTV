package org.yzq.bukatv

import androidx.compose.ui.window.ComposeUIViewController
import com.bukatv.db.DatabaseDriverFactory
import com.bukatv.db.createDatabase

val factory = DatabaseDriverFactory() // 自动使用 NativeSqliteDriver
val database = createDatabase(factory)

fun MainViewController() = ComposeUIViewController { App(database) }