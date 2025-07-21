package org.yzq.bukatv

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.bukatv.db.Database
import org.yzq.bukatv.lib.LocalDatabase
import org.yzq.bukatv.lib.LocalNavController
import org.yzq.bukatv.ui.screens.AddMediaSourceRoute
import org.yzq.bukatv.ui.screens.AddMediaSourceScreen
import org.yzq.bukatv.ui.screens.HomeRoute
import org.yzq.bukatv.ui.screens.HomeScreen
import org.yzq.bukatv.ui.screens.MediaSourceRoute
import org.yzq.bukatv.ui.screens.MediaSourceScreen


@Composable
fun App(db: Database) {
    val navController = rememberNavController()

    CompositionLocalProvider(
        LocalDatabase provides db,
        LocalNavController provides navController
    ) {
        NavHost(navController = navController, startDestination = HomeRoute) {
            composable<HomeRoute> { HomeScreen() }
            composable<MediaSourceRoute> { MediaSourceScreen() }
            composable<AddMediaSourceRoute> { AddMediaSourceScreen() }
        }
    }
}