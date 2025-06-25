package com.nikhilproject.presentation.screens.cartscreens

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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.nikhilproject.presentation.viewmodel.AddressViewModel

@Composable
fun AddressListScreen(
    viewModel: AddressViewModel = hiltViewModel(),
    onPlaceOrderClick: () -> Unit
) {
    val addresses by viewModel.allAddresses.collectAsState()

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
                        name = "Need to add name",
                        address = "${address.address}, ${address.city}, ${address.state}, ${address.zipCode}",
                        isSelected = false
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                }
            }

            Button(
                onClick = {
                    onPlaceOrderClick()
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
fun AddressCard(name: String, address: String, isSelected: Boolean) {
    Card(
        elevation = CardDefaults.cardElevation(4.dp),
        border = BorderStroke(1.dp, Color.LightGray),
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
                onClick = {

                }
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

            IconButton(
                onClick = { /* Handle delete */ }
            ) {
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
    AddressListScreen() {}
}
