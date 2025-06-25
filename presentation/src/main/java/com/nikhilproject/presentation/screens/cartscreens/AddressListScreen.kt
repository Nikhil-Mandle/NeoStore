package com.nikhilproject.presentation.screens.cartscreens

import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.nikhilproject.domain.model.OrderResponse
import com.nikhilproject.domain.model.addressmodel.Address
import com.nikhilproject.presentation.UiState
import com.nikhilproject.presentation.screens.components.ShowProgressDialog
import com.nikhilproject.presentation.screens.components.ShowToastMessage
import com.nikhilproject.presentation.viewmodel.AddressViewModel
import com.nikhilproject.presentation.viewmodel.OrderViewModel
import com.nikhilproject.presentation.viewmodel.UserViewModel

@Composable
fun AddressListScreen(
    viewModel: AddressViewModel = hiltViewModel(),
    onSuccessfulOrderPlaced: () -> Unit
) {
    val context = LocalContext.current
    val orderViewModel: OrderViewModel = hiltViewModel()
    val userViewModel: UserViewModel = hiltViewModel()
    val selectedAddress = remember { mutableStateOf<Address?>(null) }
    val addresses by viewModel.allAddresses.collectAsState()
    val deleteAddressState by viewModel.deleteAddressState.collectAsState()
    val placeOrderState by orderViewModel.orderProductState.collectAsState()
    val accessToken = userViewModel.getAccessToken()
    var showLoading by remember { mutableStateOf(false) }
    var messageToShow by remember { mutableStateOf<String?>(null) }

    LaunchedEffect(placeOrderState) {
        when (placeOrderState) {
            is UiState.Loading -> {
                showLoading = true
            }

            is UiState.Success -> {
                showLoading = false
                messageToShow = (placeOrderState as UiState.Success<OrderResponse>).data.message
                onSuccessfulOrderPlaced.invoke()
            }

            is UiState.Error -> {
                showLoading = false
                messageToShow = (placeOrderState as UiState.Success<OrderResponse>).data.message
            }

            else -> Unit
        }
    }

    ShowProgressDialog(showLoading)

    ShowToastMessage(messageToShow) {
        messageToShow = it
    }

    LaunchedEffect(deleteAddressState) {
        when (deleteAddressState) {
            is UiState.Success -> {
                val message = (deleteAddressState as UiState.Success).data
                Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
            }

            is UiState.Error -> {
                val message = (deleteAddressState as UiState.Error).message
                Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
            }

            else -> Unit
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Shipping Address",
            style = MaterialTheme.typography.labelSmall,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        if (addresses.isEmpty()) {
            Text("No addresses found. Please add one.")
        } else {
            LazyColumn(modifier = Modifier.weight(1f)) {
                items(addresses) { address ->
                    AddressCard(
                        name = "",
                        address = "${address.address}, ${address.city}, ${address.state}, ${address.zipCode}",
                        isSelected = selectedAddress.value == address,
                        onSelect = { selectedAddress.value = address },
                        onDelete = { viewModel.deleteAddress(address) }
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                }
            }

            Button(
                onClick = {
                    selectedAddress.value?.let {
                        val fullAddress =
                            "${it.address}, ${it.city}, ${it.state}, ${it.zipCode}, ${it.country}"
                        orderViewModel.placeOrder(accessToken = accessToken ?: "", fullAddress)
                    } ?: run {
                        Toast.makeText(context, "Please select an address", Toast.LENGTH_SHORT)
                            .show()
                    }
                },
                colors = ButtonDefaults.buttonColors(containerColor = Color.Red),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
                    .padding(top = 16.dp)
            ) {
                Text("PLACE ORDER", color = Color.White)
            }
        }
    }
}

@Composable
fun AddressCard(
    name: String,
    address: String,
    isSelected: Boolean,
    onSelect: () -> Unit,
    onDelete: () -> Unit
) {
    Card(
        elevation = CardDefaults.cardElevation(4.dp),
        border = BorderStroke(1.dp, if (isSelected) Color.Red else Color.LightGray),
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.Top
        ) {
            RadioButton(
                selected = isSelected,
                onClick = onSelect
            )

            Spacer(modifier = Modifier.width(8.dp))

            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = name,
                    style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold),
                    modifier = Modifier.padding(bottom = 4.dp)
                )
                Text(
                    text = address,
                    style = MaterialTheme.typography.bodySmall
                )
            }

            IconButton(onClick = onDelete) {
                Icon(
                    imageVector = Icons.Default.Close,
                    contentDescription = "Delete Address"
                )
            }
        }
    }
}

@Preview
@Composable
private fun AddressListScreenPreview() {
    AddressListScreen(){}
}
