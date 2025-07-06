package org.yzq.bukatv

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowForward
import androidx.compose.material.icons.automirrored.rounded.ArrowForwardIos
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.PlayLesson
import androidx.compose.material.icons.filled.SettingsApplications
import androidx.compose.material.icons.filled.SettingsCell
import androidx.compose.material.icons.filled.Source
import androidx.compose.material.icons.filled.Tune
import androidx.compose.material.icons.filled.VideoSettings
import androidx.compose.material.icons.rounded.ArrowCircleRight
import androidx.compose.material.icons.rounded.ArrowForward
import androidx.compose.material.icons.rounded.Info
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.ui.tooling.preview.Preview

data class SettingItem(val name: String, val icon: ImageVector, val onClick: () -> Unit);

@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Preview
fun SettingsScreen() {

    val settings = listOf<SettingItem>(
        SettingItem(name = "通用", icon = Icons.Default.Tune, onClick = {}),
        SettingItem(name = "文件来源", icon = Icons.Default.Source, onClick = {}),
        SettingItem(name = "播放设置", icon = Icons.Default.VideoSettings, onClick = {}),
        SettingItem(name = "关于", icon = Icons.Rounded.Info, onClick = {}),
    );
    Box(
        modifier = Modifier.fillMaxSize()
    ) {

        Scaffold(topBar = {
            TopAppBar(
                title = { Text("设置") })
        }, content = { innerPadding ->
            Column(
                modifier = Modifier.padding(innerPadding).fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                settings.forEachIndexed { index, item ->
                    Box(modifier = Modifier.fillMaxWidth().clickable { item.onClick() }) {
                        Row(
                            modifier = Modifier.padding(15.dp, 10.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                item.icon,
                                contentDescription = item.name,
                            )
                            Spacer(modifier = Modifier.width(10.dp))
                            Text(item.name, modifier = Modifier.weight(1f))
                            Icon(
                                Icons.AutoMirrored.Rounded.ArrowForwardIos,
                                contentDescription = item.name,
                            )
                        }
                    }
                    Box(modifier = Modifier.fillMaxWidth().height(1.dp).background(Color.Gray))
                }
            }
        })
    }
}