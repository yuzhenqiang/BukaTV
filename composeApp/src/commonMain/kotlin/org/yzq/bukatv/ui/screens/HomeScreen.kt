package org.yzq.bukatv.ui.screens

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import kotlinx.serialization.Serializable

@Serializable
object HomeRoute

sealed class SubScreen(
    val route: String,
    val label: String,
    val icon: ImageVector,
    val content: @Composable () -> Unit
) {
    object Main : SubScreen("main", "主屏幕", Icons.Default.PlayArrow, { MainScreen() })
    object MediaLibrary : SubScreen("media_library", "媒体库", Icons.Default.Search, { MediaLibraryScreen() })
    object Settings : SubScreen("settings", "设置", Icons.Default.Settings, { SettingsScreen() })
}

@Composable
fun HomeScreen() {
    val tabNavController = rememberNavController()

    val subScreens = listOf(
        SubScreen.Main,
        SubScreen.MediaLibrary,
        SubScreen.Settings
    )

    MaterialTheme {
        Scaffold(
            modifier = Modifier
                .fillMaxSize(),
            bottomBar = {
                NavigationBar {
                    val navBackStackEntry by tabNavController.currentBackStackEntryAsState()
                    val currentRoute = navBackStackEntry?.destination?.route
                    subScreens.forEachIndexed { index, screen ->
                        NavigationBarItem(
                            selected = currentRoute == screen.route,
                            icon = { Icon(screen.icon, contentDescription = screen.label) },
                            label = { Text(screen.label) },
                            onClick = {
                                if (currentRoute != screen.route) {
                                    tabNavController.navigate(screen.route) {
                                        popUpTo(tabNavController.graph.id) {
                                            saveState = true
                                        }
                                        launchSingleTop = true
                                        restoreState = true
                                    }
                                }
                            }
                        )
                    }
                }
            }
        ) {
            NavHost(
                tabNavController,
                startDestination = SubScreen.Main.route,
                enterTransition = { EnterTransition.None },
                exitTransition = { ExitTransition.None },
                popEnterTransition = { EnterTransition.None },
                popExitTransition = { ExitTransition.None }
            ) {
                subScreens.forEach { screen ->
                    composable(screen.route) { screen.content() }
                }
            }
        }
    }
}