package org.yzq.bukatv.ui.screens

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import kotlinx.serialization.Serializable
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.yzq.bukatv.lib.LocalNavController

@Serializable
object MediaSourceRoute

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun MediaSourceScreen() {
    val navController = LocalNavController.current

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("媒体来源") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.Default.ArrowBackIosNew,
                            contentDescription = "返回"
                        )
                    }
                },
                actions = {
                    IconButton(onClick = { navController.navigate(AddMediaSourceRoute) }) {
                        Icon(
                            imageVector = Icons.Default.Add,
                            contentDescription = "新增媒体来源"
                        )
                    }
                }
            )
        },
        content = {
            Text("Content")
        }
    )
}