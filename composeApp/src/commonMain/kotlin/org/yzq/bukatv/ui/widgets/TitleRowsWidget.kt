package org.yzq.bukatv.ui.widgets

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun TitleRows(title: String, modifier: Modifier = Modifier, showEntry: Boolean = false, entryText: String = "查看全部", onEntryClick: (() -> Unit)? = null, content: LazyListScope.() -> Unit) {
    Column(
        modifier = Modifier.fillMaxWidth().then(modifier),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Row(modifier = Modifier.fillMaxWidth().padding(15.dp, 0.dp), verticalAlignment = Alignment.Bottom) {
            Text(
                title,
                fontSize = 20.sp,
                textAlign = TextAlign.Start,
                modifier = Modifier.weight(1f)
            )
            if(showEntry) Text(
                entryText,
                modifier = Modifier.clickable {
                    if (onEntryClick != null) {
                        onEntryClick()
                    }
                },
                fontSize = 16.sp,
                color = Color.Blue
            )
        }
        Spacer(modifier = Modifier.height(10.dp))
        LazyRow(
            modifier = Modifier.fillMaxWidth(),
            contentPadding = PaddingValues(horizontal = 15.dp),
            overscrollEffect = null,
            horizontalArrangement = Arrangement.spacedBy(15.dp),
            content = content
        )
    }
}
