package com.patronus.customers_list.presentation.ui.composable

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Snackbar
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.patronus.customers_list.presentation.ui.CustomersListViewModel
import com.patronus.customers_list.presentation.ui.data.CustomerUiModel

@Composable
fun CustomerListScreen(customersListViewModel: CustomersListViewModel) {

    val customersListState by customersListViewModel.customersList.collectAsState()
    val isLoading by rememberUpdatedState(newValue = customersListState.isLoading)
    val isError by rememberUpdatedState(newValue = customersListState.isError)
    val errorMessage by rememberUpdatedState(newValue = customersListState.errorMessage)

    Scaffold(
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
                            //TODO:  handle the customer details screen
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