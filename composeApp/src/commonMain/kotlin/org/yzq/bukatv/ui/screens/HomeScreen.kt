package org.yzq.bukatv.ui.screens

import androidx.compose.foundation.layout.Box
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavController
import kotlinx.serialization.Serializable

@Serializable
object HomeRoute

sealed class SubScreen(
    val label: String,
    val icon: ImageVector,
    val content: @Composable (navController: NavController) -> Unit
) {
    object Main : SubScreen("主屏幕", Icons.Default.PlayArrow, { navController -> MainScreen(navController) })
    object MediaLibrary : SubScreen("媒体库", Icons.Default.Search, { navController ->  MediaLibraryScreen(navController) })
    object Settings : SubScreen("设置", Icons.Default.Settings, { navController -> SettingsScreen(navController) })
}

@Composable
fun HomeScreen(navController: NavController) {
    val subScreens = listOf(
        SubScreen.Main,
        SubScreen.MediaLibrary,
        SubScreen.Settings
    )
    // 定义可变状态
    var screenTab by remember { mutableStateOf(0) }

    MaterialTheme {
        Scaffold(
            modifier = Modifier
                .fillMaxSize(),
            bottomBar = {
                NavigationBar {
                    subScreens.forEachIndexed { index, screen ->
                        NavigationBarItem(
                            selected = screenTab == index,
                            onClick = { screenTab = index },
                            icon = { Icon(screen.icon, contentDescription = screen.label) },
                            label = { Text(screen.label) }
                        )
                    }
                }
            }
        ) {
            Box(modifier = Modifier.fillMaxSize()) {
                subScreens[screenTab].content(navController)
            }
        }
    }
}