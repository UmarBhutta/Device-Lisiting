package com.patronus.common.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp

@Composable
fun Stickers(stickers: List<String>) {
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(items = stickers) {
            Sticker(sticker = it)
        }
    }
}

@Composable
fun Sticker(sticker: String) {

    val isBam = sticker.contentEquals("BAN", true)
    val color = remember {
        if (isBam) {
            Color(0xFFFCDDE4)
        } else Color(0xFFECF1F4)
    }
    val textColor = remember {
        if (isBam) {
            Color(0xFFF4587B)
        } else Color(0xFF8C8CA1)
    }
    Box(
        modifier = Modifier
            .background(color = color, shape = RoundedCornerShape(size = 4.dp))
            .padding(start = 4.dp, top = 2.dp, end = 4.dp, bottom = 2.dp)
    ) {
        Text(
            modifier = Modifier.align(Alignment.Center),
            text = sticker,
            color = textColor,
            lineHeight = 9.23.em,
            style = TextStyle(
                fontSize = 13.sp)
        )
    }
}