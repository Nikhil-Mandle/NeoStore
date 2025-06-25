package com.nikhilproject.presentation.screens.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.DrawerState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.nikhilproject.presentation.R
import com.nikhilproject.presentation.navigation.LoginScreen
import com.nikhilproject.presentation.navigation.MyAccountScreen
import com.nikhilproject.presentation.navigation.MyCartScreen
import com.nikhilproject.presentation.navigation.MyOrdersListScreen
import com.nikhilproject.presentation.navigation.ProductListScreen
import com.nikhilproject.presentation.navigation.StoreLocatorScreen
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun DrawerContent(
    navController: NavHostController,
    drawerState: DrawerState,
    scope: CoroutineScope,
    userName: String,
    userEmail: String,
    profilePic: String,
    cartCount: Int = 0
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 16.dp, vertical = 24.dp)
    ) {
        Box(
            modifier = Modifier
                .size(90.dp)
                .clip(CircleShape)
                .background(Color.Gray)
                .align(Alignment.CenterHorizontally)
        ) {
            Image(
                painter = rememberAsyncImagePainter(profilePic),
                contentDescription = "Profile",
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
        }

        Spacer(Modifier.height(8.dp))

        // Name and Email
        Text(
            text = userName,
            style = MaterialTheme.typography.displayMedium,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )

        Text(
            text = userEmail,
            style = MaterialTheme.typography.bodySmall,
            modifier = Modifier.align(Alignment.CenterHorizontally),
            color = Color.Gray
        )

        Spacer(Modifier.height(24.dp))

        DrawerItem(
            icon = R.drawable.shopping_cart,
            label = "My Cart",
        ) {
            scope.launch { drawerState.close() }
            navController.navigate(MyCartScreen)
        }

        DrawerItem(icon = R.drawable.table, label = "Tables") {
            scope.launch { drawerState.close() }
            navController.navigate(ProductListScreen)
        }

        DrawerItem(icon = R.drawable.sofa, label = "Sofas") {
            scope.launch { drawerState.close() }
            navController.navigate(ProductListScreen)
        }

        DrawerItem(icon = R.drawable.chair, label = "Chairs") {
            scope.launch { drawerState.close() }
            navController.navigate(ProductListScreen)
        }

        DrawerItem(icon = R.drawable.closet, label = "Cupboards") {
            scope.launch { drawerState.close() }
            navController.navigate(ProductListScreen)
        }

        DrawerItem(icon = R.drawable.person, label = "My Account") {
            scope.launch { drawerState.close() }
            navController.navigate(MyAccountScreen)
        }

        DrawerItem(icon = R.drawable.location_on, label = "Store Locator") {
            scope.launch { drawerState.close() }
            navController.navigate(StoreLocatorScreen)
        }

        DrawerItem(icon = R.drawable.order_list, label = "My Orders") {
            scope.launch { drawerState.close() }
            navController.navigate(MyOrdersListScreen)
        }

        DrawerItem(icon = R.drawable.exit_to_app, label = "Logout") {
            scope.launch { drawerState.close() }
            navController.navigate(LoginScreen) {
                navController.popBackStack()
                launchSingleTop = true
            }
        }
    }
}