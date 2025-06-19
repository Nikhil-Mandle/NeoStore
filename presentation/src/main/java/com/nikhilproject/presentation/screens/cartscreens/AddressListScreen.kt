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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun AddressListScreen() {
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

        LazyColumn(
            modifier = Modifier.weight(1f)
        ) {
            items(3) { index ->  // Assuming 3 addresses for now
                AddressCard(
                    name = "Glen Dmello",
                    address = "NeoSOFT Technologies 4th Floor, The Ruby,\n" +
                            "29, Senapati Bapat Marg, Dadar (West)\n" +
                            "Mumbai- 400-028.INDIA.",
                    isSelected = index == 0  // First item selected by default
                )
                Spacer(modifier = Modifier.height(12.dp))
            }
        }

        Button(
            onClick = { /* Handle place order */ },
            colors = ButtonDefaults.buttonColors(containerColor = Color.Red),
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .padding(top = 16.dp)
        ) {
            Text(text = "PLACE ORDER", color = Color.White)
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
                onClick = { /* Handle selection */ }
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
    AddressListScreen()
}
