package org.yzq.bukatv

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.bukatv.db.Database
import org.yzq.bukatv.ui.screens.AddMediaSourceRoute
import org.yzq.bukatv.ui.screens.AddMediaSourceScreen
import org.yzq.bukatv.ui.screens.HomeRoute
import org.yzq.bukatv.ui.screens.HomeScreen
import org.yzq.bukatv.ui.screens.MediaSourceRoute
import org.yzq.bukatv.ui.screens.MediaSourceScreen


@Composable
fun App(db: Database) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = HomeRoute) {
        composable<HomeRoute> { HomeScreen(navController) }
        composable<MediaSourceRoute> { MediaSourceScreen(navController) }
        composable< AddMediaSourceRoute> { AddMediaSourceScreen(navController) }
    }
}