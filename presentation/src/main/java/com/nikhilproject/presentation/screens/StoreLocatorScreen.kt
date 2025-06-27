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
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState

@Composable
fun StoreLocatorScreen(modifier: Modifier = Modifier) {
    val selectedStore = remember { mutableStateOf<Store?>(storeLocation.first()) }

    Column(modifier = modifier.fillMaxSize()) {

        GoogleMap(
            modifier = Modifier
                .fillMaxWidth()
                .height(350.dp),
            cameraPositionState = rememberCameraPositionState {
                position = CameraPosition.fromLatLngZoom(storeLocation.first().latLng, 5f)
            }
        ) {
            storeLocation.forEach { store ->
                Marker(
                    state = MarkerState(position = store.latLng),
                    title = store.name,
                    snippet = store.address,
                    icon = BitmapDescriptorFactory.defaultMarker(
                        if (store == selectedStore.value) BitmapDescriptorFactory.HUE_RED
                        else BitmapDescriptorFactory.HUE_BLUE
                    )
                )
            }
        }

        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(storeLocation) { store ->
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

data class Store(
    val name: String,
    val address: String,
    val latLng: LatLng
)

val storeLocation = listOf(
    Store(
        "NeoSOFT DADAR",
        "The Ruby Tower, Senapati Bapat Marg, Dadar West, Mumbai, Maharashtra 400028",
        LatLng(19.0244, 72.8444)
    ),
    Store(
        "NeoSOFT PAREL",
        "9th floor, Business arcade, Sayani Rd, Parel Bus Depot, Dighe Nagar, Prabhadevi, Mumbai, Maharashtra 400025",
        LatLng(19.0158, 72.8294)
    ),
    Store(
        "NeoSOFT AIROLI",
        "Plot No. 3, (Part) Kalwa TTC Industrial Area, MIDC Railway Station, near Airoli, Airoli East, Navi Mumbai, Maharashtra 400708",
        LatLng(19.157934, 72.993477)
    ),
    Store(
        "NeoSOFT PUNE",
        "NTPL SEZ (Blueridge), IT-08/09, 10th Floor, Hinjewadi Phase 1 Rd, Hinjawadi Rajiv Gandhi Infotech Park, Hinjawadi, Pune, Maharashtra 411057",
        LatLng(18.5887, 73.7355)
    ),
)