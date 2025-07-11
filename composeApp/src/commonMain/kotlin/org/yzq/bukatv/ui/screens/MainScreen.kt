package org.yzq.bukatv.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.yzq.bukatv.model.MediaItemData
import org.yzq.bukatv.ui.widgets.MediaItemWidget
import org.yzq.bukatv.ui.widgets.TitleRows

val mediaList = listOf<MediaItemData>(
    MediaItemData(
        name = "肖申克的救赎",
        date = "1995",
        posterUrl = "https://img3.doubanio.com/view/photo/s_ratio_poster/public/p480747492.webp"
    ),
    MediaItemData(
        name = "教父",
        date = "1972",
        posterUrl = "https://img9.doubanio.com/view/photo/s_ratio_poster/public/p616779645.webp"
    ),
    MediaItemData(
        name = "教父2",
        date = "1974",
        posterUrl = "https://img3.doubanio.com/view/photo/s_ratio_poster/public/p2194138787.webp"
    ),
    MediaItemData(
        name = "辛德勒的名单",
        date = "1994",
        posterUrl = "https://img3.doubanio.com/view/photo/s_ratio_poster/public/p492406163.webp"
    ),
    MediaItemData(
        name = "十二怒汉",
        date = "1957",
        posterUrl = "https://img3.doubanio.com/view/photo/s_ratio_poster/public/p2173577632.webp"
    ),
    MediaItemData(
        name = "寄生虫",
        date = "2019",
        posterUrl = "https://img1.doubanio.com/view/photo/s_ratio_poster/public/p2561439800.webp"
    ),
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Preview
fun MainScreen() {
    // 定义可变状态
    var scrollState = rememberScrollState()
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Scaffold(topBar = {
            TopAppBar(
                title = { Text("主屏幕") })
        }, content = { innerPadding ->
            Column(
                modifier = Modifier.padding(innerPadding).fillMaxSize().verticalScroll(scrollState),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                TitleRows(title = "最近添加", showEntry = true) {
                    items(mediaList) { item -> MediaItemWidget(item) }
                }
                Spacer(modifier = Modifier.height(20.dp))
                TitleRows(title = "未观看的") {
                    items(mediaList) { item -> MediaItemWidget(item) }
                }
            }
        })
    }
}
