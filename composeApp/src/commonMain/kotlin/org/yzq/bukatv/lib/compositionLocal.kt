package org.yzq.bukatv.lib

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.navigation.NavHostController
import com.bukatv.db.Database

val LocalDatabase = staticCompositionLocalOf<Database> {
    error("No Database provided")
}

val LocalNavController = staticCompositionLocalOf<NavHostController> {
    error("No NavController provided")
}