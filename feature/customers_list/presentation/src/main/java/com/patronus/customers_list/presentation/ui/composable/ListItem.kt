package com.patronus.customers_list.presentation.ui.composable

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.patronus.customers_list.presentation.ui.data.CustomerUiModel

@Composable
fun ListItem(
    modifier: Modifier = Modifier,
    customerUiModel: CustomerUiModel,
    onItemClick: (id: Int) -> Unit
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.Top),
        modifier = modifier
            .padding(top = 16.dp)
            .clickable {
                onItemClick(customerUiModel.id)
            }
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(12.dp, Alignment.Start)
        ) {
            CustomerPhoto(
                imageUrl = customerUiModel.photoUrl,
                initials = customerUiModel.initials
            )
            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp, Alignment.Top)
            ) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.Start)
                ) {
                    Text(
                        text = customerUiModel.name,
                        color = Color(0xff0e0e2c),
                        lineHeight = 7.06.em,
                        style = TextStyle(
                            fontSize = 17.sp,
                            fontWeight = FontWeight.Bold)
                    )
                    Text(
                        text = customerUiModel.gender,
                        color = Color(0xff8c8ca1),
                        lineHeight = 7.06.em,
                        style = TextStyle(
                            fontSize = 17.sp))
                }
                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.Start)
                ) {
                    Text(
                        text = customerUiModel.phoneNumber,
                        color = Color(0xff4a4a68),
                        lineHeight = 7.06.em,
                        style = TextStyle(
                            fontSize = 17.sp))
                    Stickers(stickers = customerUiModel.stickers)
                }
            }
        }
        Divider(
            color = Color(0xffced4da),
            modifier = Modifier
                .fillMaxWidth()
        )
    }
}

@Composable
fun CustomerPhoto(
    modifier: Modifier = Modifier,
    imageUrl: String?,
    initials: String
) {
    Box(
        modifier = modifier
            .requiredSize(size = 48.dp)
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
                fontSize = 13.sp))
    }
}
