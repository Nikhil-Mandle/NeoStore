package com.nikhilproject.presentation.screens

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState

data class Store(
    val name: String,
    val address: String,
    val latLng: LatLng
)

val dummyStores = listOf(
    Store("SKYLAND STORE", "6335 Edgewood Road Reisterstown, MD 21136", LatLng(39.4579, -76.8161)),
    Store(
        "WOODMOUNT STORE",
        "9437 Pin Oak Drive South Plainfield, NJ 07080",
        LatLng(40.5796, -74.4194)
    ),
    Store("NATUFUR STORE", "3798 Pennsylvania Avenue Brandon, FL 33510", LatLng(27.9374, -82.2971)),
    Store("LAVANDER STORE", "9311 Garfield Avenue Hamburg, NY 14075", LatLng(42.7156, -78.8291)),
    Store("FURNIMATT STORE", "7346 Hanover Court Arlington, MA 02474", LatLng(42.4195, -71.1698))
)


@Composable
fun StoreLocatorScreen(modifier: Modifier = Modifier) {
    val selectedStore = remember { mutableStateOf<Store?>(dummyStores.first()) }

    Column(modifier = modifier.fillMaxSize()) {

        GoogleMap(
            modifier = Modifier
                .fillMaxWidth()
                .height(250.dp),
            cameraPositionState = rememberCameraPositionState {
                position = CameraPosition.fromLatLngZoom(dummyStores.first().latLng, 5f)
            }
        ) {
            dummyStores.forEach { store ->
                Marker(
                    state = MarkerState(position = store.latLng),
                    title = store.name,
                    snippet = store.address
                )
            }
        }

        // List Section
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(dummyStores) { store ->
                StoreListItem(
                    store = store,
                    isSelected = selectedStore.value == store,
                    onClick = { selectedStore.value = store }
                )
            }
        }
    }
}


@Composable
fun StoreListItem(store: Store, isSelected: Boolean, onClick: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(12.dp)
            .border(
                width = if (isSelected) 2.dp else 1.dp,
                color = if (isSelected) Color.Green else Color.LightGray,
                shape = RoundedCornerShape(4.dp)
            )
            .padding(12.dp)
    ) {
        Text(
            text = store.name,
            style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold)
        )
        Text(
            text = store.address,
            style = MaterialTheme.typography.bodySmall.copy(color = Color.Gray)
        )
    }
}