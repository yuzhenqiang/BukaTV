package org.yzq.bukatv

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import org.yzq.bukatv.ui.screens.HomeRoute
import org.yzq.bukatv.ui.screens.HomeScreen
import org.yzq.bukatv.ui.screens.MediaSourceRoute
import org.yzq.bukatv.ui.screens.MediaSourceScreen


@Composable
fun App() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = HomeRoute) {
        composable<HomeRoute> { HomeScreen(navController) }
        composable<MediaSourceRoute> { MediaSourceScreen(navController) }
    }
}