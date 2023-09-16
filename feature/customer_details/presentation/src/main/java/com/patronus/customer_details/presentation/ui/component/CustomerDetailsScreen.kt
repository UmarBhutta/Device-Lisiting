package com.patronus.customer_details.presentation.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Snackbar
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.patronus.common.components.Avatar
import com.patronus.common.components.Stickers

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomerDetailsScreen() {

    val isLoading = false
    val isError = false

    Scaffold(
        topBar = {
                 TopAppBar(modifier = Modifier.padding(start = 16.dp, top = 16.dp),
                     title = {},
                     navigationIcon = {
                     IconButton(
                         onClick = { /*TODO*/ }
                     ) {
                         Box(
                             modifier = Modifier
                                 .width(40.dp)
                                 .height(40.dp)
                                 .border(
                                     width = 1.dp,
                                     color = Color(0xFFCED4DA),
                                     shape = RoundedCornerShape(size = 48.dp)
                                 )
                                 .padding(start = 8.dp, top = 8.dp, end = 8.dp, bottom = 8.dp)
                         ) {
                             Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "back button")
                         }
                     }
                 }
                 )
        },
        content = { paddingValue ->
            Column(
                modifier = Modifier
                    .padding(paddingValue)
                    .fillMaxSize(),
                horizontalAlignment = Alignment.Start
            ) {
                if (isLoading) {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator()
                    }
                } else {
                    if (isError) {
                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.BottomCenter
                        ) {
                            Snackbar(
                                modifier = Modifier.padding(16.dp),
                                action = {
                                    TextButton(
                                        onClick = {

                                        }
                                    ) {
                                        Text(text = "Retry")
                                    }
                                }
                            ) {
                                Text(text = "An error occurred")
                            }
                        }
                    } else {
                            ScreenContent(modifier = Modifier.padding(vertical = 8.dp))
                    }
                }
            }
        }
    )
}

@Composable
fun ScreenContent(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
    ) {
        MapViewContainer()
        InfoContent()
    }
}

@Composable
fun InfoContent(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .padding(horizontal = 24.dp)
            .fillMaxWidth(),
        horizontalAlignment = Alignment.Start
        ) {
        Avatar(initials = "AB", imageUrl = "https://fastly.picsum.photos/id/473/200/300.jpg?hmac=WYG6etF60iOJeGoFVY1hVDMakbBRS32ZDGNkVZhF6-8")
        CustomerInfo(modifier = Modifier.padding(top = 24.dp))
        CustomerAddress(modifier = Modifier.padding(top = 24.dp))
    }
}

@Composable
fun CustomerInfo(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(8.dp, Alignment.Top),
        horizontalAlignment = Alignment.Start,
    ) {
        Text(
            text = "Savannah Nguyen",
            style = TextStyle(
                fontSize = 27.sp,
                lineHeight = 32.4.sp,
                fontWeight = FontWeight(700),
                color = Color(0xFF0E0E2C),
            )
        )

        Stickers(listOf("FAM","BAN"))

        Row(
            horizontalArrangement = Arrangement.spacedBy(10.dp, Alignment.Start),
            verticalAlignment = Alignment.Top,
        ) {
            Text(
                text = "Female",
                style = TextStyle(
                    fontSize = 17.sp,
                    lineHeight = 20.4.sp,
                    fontWeight = FontWeight(400),
                    color = Color(0xFF4A4A68),
                )
            )
            Divider(
                modifier = Modifier
                    .padding(0.dp)
                    .width(1.dp)
                    .height(20.dp)
                    .background(color = Color(0xFFCED4DA))
            )
            Text(
                text = "+49 1760 46211581",
                style = TextStyle(
                    fontSize = 17.sp,
                    lineHeight = 20.4.sp,
                    fontWeight = FontWeight(400),
                    color = Color(0xFF4A4A68),
                )
            )
        }

    }
}

@Composable
fun CustomerAddress(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(8.dp, Alignment.Top),
        horizontalAlignment = Alignment.Start,
    ) {
        Text(
            text = "Address".uppercase(),
            style = TextStyle(
                fontSize = 15.sp,
                lineHeight = 18.sp,
                fontWeight = FontWeight(700),
                color = Color(0xFF4A4A68),
            )
        )
        Text(
            text = "Catrin-Hoffmann-Ring 1, 38952\nBadoberan",
            style = TextStyle(
                fontSize = 17.sp,
                lineHeight = 20.4.sp,
                fontWeight = FontWeight(400),
                color = Color(0xFF4A4A68),
            )
        )
    }
}

@Composable
fun MapViewContainer(modifier: Modifier = Modifier) {
    Box(modifier = modifier
        .fillMaxWidth()
        .height(292.dp)
        .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Avatar(
            modifier = Modifier.size(300.dp),
            initials = "AB",
            imageUrl = "https://fastly.picsum.photos/id/445/400/400.jpg?hmac=CCjqlZXQQ_5kl0X6naMjQKUWSbQloDYImyB9zGFOA8M",
        )
    }
}