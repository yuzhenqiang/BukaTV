package org.yzq.bukatv

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.kamel.image.KamelImage
import io.kamel.image.asyncPainterResource
import org.jetbrains.compose.ui.tooling.preview.Preview

data class MediaItem(val name: String, val date: String, val posterUrl: String);

@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Preview
fun MainScreen() {
    val mediaList = listOf<MediaItem>(
        MediaItem(
            name = "肖申克的救赎",
            date = "1995",
            posterUrl = "https://img3.doubanio.com/view/photo/s_ratio_poster/public/p480747492.webp"
        ),
        MediaItem(
            name = "教父",
            date = "1972",
            posterUrl = "https://img9.doubanio.com/view/photo/s_ratio_poster/public/p616779645.webp"
        ),
        MediaItem(
            name = "教父2",
            date = "1974",
            posterUrl = "https://img3.doubanio.com/view/photo/s_ratio_poster/public/p2194138787.webp"
        ),
        MediaItem(
            name = "辛德勒的名单",
            date = "1994",
            posterUrl = "https://img3.doubanio.com/view/photo/s_ratio_poster/public/p492406163.webp"
        ),
        MediaItem(
            name = "十二怒汉",
            date = "1957",
            posterUrl = "https://img3.doubanio.com/view/photo/s_ratio_poster/public/p2173577632.webp"
        ),
        MediaItem(
            name = "寄生虫",
            date = "2019",
            posterUrl = "https://img1.doubanio.com/view/photo/s_ratio_poster/public/p2561439800.webp"
        ),
    )

    // 定义可变状态

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Scaffold(topBar = {
            TopAppBar(
                title = { Text("主屏幕") })
        }, content = { innerPadding ->
            Column(
                modifier = Modifier.padding(innerPadding).fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Text(
                    "最近添加",
                    fontSize = 20.sp,
                    textAlign = TextAlign.Start,
                    modifier = Modifier.fillMaxWidth().padding(15.dp, 0.dp)
                )
                Spacer(modifier = Modifier.height(10.dp))
                LazyRow(
                    modifier = Modifier.fillMaxWidth().padding(15.dp, 0.dp),
                    overscrollEffect = null,
                    horizontalArrangement = Arrangement.spacedBy(15.dp)
                ) {
                    items(mediaList) { item ->
                        Column(
                            modifier = Modifier.width(100.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            KamelImage(
                                { asyncPainterResource(item.posterUrl) },
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(160.dp)
                                    .clip(RoundedCornerShape(12.dp)),
                                contentDescription = item.name,
                                contentScale = ContentScale.Crop,
                                onFailure = { exception ->
                                    Text("加载失败", color = Color.Red)
                                },
                                onLoading = {
                                    CircularProgressIndicator(
                                        modifier = Modifier.size(
                                            24.dp
                                        )
                                    )
                                })
                            Text(item.name)
                            Text(item.date)
                        }
                    }
                }
            }
        })
    }
}
