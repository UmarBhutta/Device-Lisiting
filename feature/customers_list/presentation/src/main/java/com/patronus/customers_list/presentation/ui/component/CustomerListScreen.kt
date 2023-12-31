package com.patronus.customers_list.presentation.ui.component

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Snackbar
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.patronus.customers_list.presentation.ui.CustomersListViewModel
import com.patronus.customers_list.presentation.ui.data.CustomerUiModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomerListScreen(
    customersListViewModel: CustomersListViewModel,
    navigateToDetails: (id: Int) -> Unit
) {

    val customersListState by customersListViewModel.customersList.collectAsState()
    val isLoading by rememberUpdatedState(newValue = customersListState.isLoading)
    val isError by rememberUpdatedState(newValue = customersListState.isError)
    val errorMessage by rememberUpdatedState(newValue = customersListState.errorMessage)

    Scaffold(
        topBar = {
                 TopAppBar(modifier = Modifier.padding(start = 16.dp, top = 16.dp),
                     title = {
                         Text(
                             text = "Device holders",
                             style = TextStyle(
                                 fontSize = 27.sp,
                                 lineHeight = 32.sp,
                                 fontWeight = FontWeight(700),
                                 color = Color(0xFF0E0E2C),
                             )
                         )
                     }
                 )
        },
        content = { paddingValue ->
            Column(
                modifier = Modifier
                    .padding(paddingValue)
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
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
                                            customersListViewModel.retry()
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
                        CustomersList(customers = customersListState.customers, onItemClick = {
                            navigateToDetails(it)
                        }
                        )
                    }
                }
            }
        }
    )
}

@Composable
fun CustomersList(customers: List<CustomerUiModel>, onItemClick: (id: Int) -> Unit) {
    LazyColumn(
        contentPadding = PaddingValues(start = 24.dp)
    ) {
        items(items = customers) { item ->
            ListItem(customerUiModel = item, onItemClick = onItemClick)
        }
    }
}