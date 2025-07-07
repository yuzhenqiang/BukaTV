package org.yzq.bukatv.ui.widgets

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import io.kamel.image.KamelImageBox
import io.kamel.image.asyncPainterResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.yzq.bukatv.model.MediaItemData

@Composable
@Preview
fun MediaItemWidget(data: MediaItemData) {
    val posterShape =  RoundedCornerShape(12.dp)
    Column(
        modifier = Modifier.width(100.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        KamelImageBox(
            resource = { asyncPainterResource(data.posterUrl) },
            modifier = Modifier
                .fillMaxWidth()
                .height(160.dp)
                .shadow(elevation = 1.dp, shape = posterShape, clip = false)
                .background(Color.LightGray, shape = posterShape),
            onLoading = {
                CircularProgressIndicator(
                    modifier = Modifier.size(
                        24.dp
                    )
                )
            },
            onFailure = { exception ->
                Text("加载失败", color = Color.Red)
            },
            onSuccess = { painter ->
                Image(
                    painter = painter,
                    contentDescription = data.name,
                    modifier = Modifier.fillMaxSize().clip(posterShape),
                    alignment = Alignment.Center,
                    contentScale = ContentScale.Crop
                )
            }
        )
        Text(data.name)
        Text(data.date)
    }
}