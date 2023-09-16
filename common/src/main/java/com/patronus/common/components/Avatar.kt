package com.patronus.common.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter

@Composable
fun Avatar(
    modifier: Modifier = Modifier.requiredSize(size = 48.dp),
    imageUrl: String?,
    initials: String
) {
    Box(
        modifier = modifier
    ) {
        Canvas(modifier = Modifier.fillMaxSize()) {
            drawCircle(SolidColor(Color(0xfff4f5f8)))
        }
        imageUrl?.let {
            Image(
                painter = rememberAsyncImagePainter(imageUrl),
                contentDescription = "Photo",
                modifier = Modifier
                    .fillMaxSize()
                    .clip(shape = RoundedCornerShape(300.dp)))
        } ?: Text(
            modifier = Modifier.align(Alignment.Center),
            text = initials,
            style = TextStyle(
                fontSize = 22.sp,
                lineHeight = 26.4.sp,
                fontWeight = FontWeight(700),
                color = Color(0xFF8C8CA1),
            )
        )

    }
}