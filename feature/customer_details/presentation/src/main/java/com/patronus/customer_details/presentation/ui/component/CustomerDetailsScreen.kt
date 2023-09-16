package com.patronus.customer_details.presentation.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeightIn
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState
import com.patronus.common.components.Avatar
import com.patronus.common.components.Stickers
import com.patronus.customer_details.presentation.ui.CustomerDetailsViewModel
import com.patronus.customer_details.presentation.ui.data.CustomerDetailUiModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomerDetailsScreen(
    customerDetailsViewModel: CustomerDetailsViewModel,
    onBackPressed: () -> Unit
) {

    val customersDetailsState by customerDetailsViewModel.customerDetails.collectAsState()
    val isLoading by rememberUpdatedState(newValue = customersDetailsState.isLoading)
    val isError by rememberUpdatedState(newValue = customersDetailsState.isError)
    val errorMessage by rememberUpdatedState(newValue = customersDetailsState.errorMessage)

    Scaffold(
        topBar = {
                 TopAppBar(modifier = Modifier.padding(start = 16.dp, top = 16.dp),
                     title = {},
                     navigationIcon = {
                     IconButton(
                         onClick = { onBackPressed() }
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
                                            customerDetailsViewModel.retry()
                                        }
                                    ) {
                                        Text(text = "Retry")
                                    }
                                }
                            ) {
                                Text(text = errorMessage ?: "An error occurred")
                            }
                        }
                    } else {
                            customersDetailsState.customer?.let {
                                ScreenContent(modifier = Modifier.padding(vertical = 8.dp), it)
                            }
                    }
                }
            }
        }
    )
}

@Composable
fun ScreenContent(modifier: Modifier = Modifier, customer: CustomerDetailUiModel) {
    Column(
        modifier = modifier
    ) {
        MapViewContainer(
            currentLatitude = customer.currentLatitude,
            currentLongitude = customer.currentLongitude
        )
        InfoContent(customer = customer)
    }
}

@Composable
fun InfoContent(modifier: Modifier = Modifier, customer: CustomerDetailUiModel) {
    Column(
        modifier = modifier
            .padding(horizontal = 24.dp)
            .fillMaxWidth(),
        horizontalAlignment = Alignment.Start
        ) {
        Avatar(initials = customer.initials, imageUrl = customer.photoUrl)
        CustomerInfo(modifier = Modifier.padding(top = 24.dp), customer)
        CustomerAddress(modifier = Modifier.padding(top = 24.dp), customer.address)
    }
}

@Composable
fun CustomerInfo(
    modifier: Modifier = Modifier,
    customer: CustomerDetailUiModel
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(8.dp, Alignment.Top),
        horizontalAlignment = Alignment.Start,
    ) {
        Text(
            text = customer.name,
            style = TextStyle(
                fontSize = 27.sp,
                lineHeight = 32.4.sp,
                fontWeight = FontWeight(700),
                color = Color(0xFF0E0E2C),
            )
        )

        Stickers(customer.stickers)

        Row(
            horizontalArrangement = Arrangement.spacedBy(10.dp, Alignment.Start),
            verticalAlignment = Alignment.Top,
        ) {
            Text(
                text = customer.gender,
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
                text = customer.phoneNumber,
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
fun CustomerAddress(modifier: Modifier = Modifier, currentAddress: String) {
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
            text = currentAddress,
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
fun MapViewContainer(
    currentLatitude : Double,
    currentLongitude : Double,
) {
    Box(modifier = Modifier
        .height(300.dp)
        .fillMaxWidth()
        .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier
                .height(296.dp)
                .width(296.dp)
                .clip(CircleShape)
        ) {
            val currentLocation = LatLng(currentLatitude, currentLongitude)
            var uiSettings by remember { mutableStateOf(MapUiSettings(zoomControlsEnabled = false)) }
            val cameraPositionState = rememberCameraPositionState {
                position = CameraPosition.fromLatLngZoom(currentLocation, 12f)
            }
            GoogleMap(
                modifier = Modifier.fillMaxSize(),
                cameraPositionState = cameraPositionState,
                uiSettings = uiSettings
            ) {
                Marker(
                    state = MarkerState(position = currentLocation),
                )
            }
        }
    }
}