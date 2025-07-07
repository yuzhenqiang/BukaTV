package org.yzq.bukatv

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
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.yzq.bukatv.ui.screens.MainScreen
import org.yzq.bukatv.ui.screens.MediaLibraryScreen
import org.yzq.bukatv.ui.screens.SettingsScreen

sealed class Screen(
    val label: String,
    val icon: ImageVector,
    val content: @Composable () -> Unit
) {
    object Main : Screen("主屏幕", Icons.Default.PlayArrow, { MainScreen() })
    object MediaLibrary : Screen("媒体库", Icons.Default.Search, { MediaLibraryScreen() })
    object Settings : Screen("设置", Icons.Default.Settings, { SettingsScreen() })
}

@Composable
@Preview
fun App() {
    val screens = listOf(
        Screen.Main,
        Screen.MediaLibrary,
        Screen.Settings
    )
    // 定义可变状态
    var screenTab by remember { mutableStateOf(0) }

    MaterialTheme {
        Scaffold(
            modifier = Modifier
                .fillMaxSize(),
            bottomBar = {
                NavigationBar {
                    screens.forEachIndexed { index, screen ->
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
                screens[screenTab].content()
            }
        }
    }
}