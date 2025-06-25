package com.nikhilproject.presentation.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.DrawerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavHostController
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun getTopBarConfig(
    currentRoute: String?,
    navController: NavHostController,
    drawerState: DrawerState,
    scope: CoroutineScope
): TopBarConfig? {
    return when (currentRoute) {
        HomeScreen::class.qualifiedName -> TopBarConfig(
            title = "NeoSTORE",
            icon = Icons.Default.Menu,
            onNavigationClick = {
                scope.launch {
                    if (drawerState.isClosed) drawerState.open() else drawerState.close()
                }
            }
        )

        ProductListScreen::class.qualifiedName -> TopBarConfig(
            title = "Products",
            icon = Icons.Default.ArrowBack,
            onNavigationClick = { navController.popBackStack() }
        )

        ProductDetailScreenNav::class.qualifiedName -> TopBarConfig(
            title = "Product Detail",
            icon = Icons.Default.ArrowBack,
            onNavigationClick = { navController.popBackStack() }
        )

        ResetPasswordScreen::class.qualifiedName -> TopBarConfig(
            title = "Update Profile",
            icon = Icons.Default.ArrowBack,
            onNavigationClick = { navController.popBackStack() }
        )

        AppSettingsScreen::class.qualifiedName -> TopBarConfig(
            title = "Settings",
            icon = Icons.Default.ArrowBack,
            onNavigationClick = { navController.popBackStack() }
        )

        MyCartScreen::class.qualifiedName -> TopBarConfig(
            title = "My Cart",
            icon = Icons.Default.ArrowBack,
            onNavigationClick = { navController.popBackStack() }
        )

        AddressListScreen::class.qualifiedName -> TopBarConfig(
            title = "Address List",
            icon = Icons.Default.ArrowBack,
            endIcon = Icons.Default.Add,
            onEndIconClick = {
                navController.navigate(AddAddressScreen)
            },
            onNavigationClick = { navController.popBackStack() }
        )

        AddAddressScreen::class.qualifiedName -> TopBarConfig(
            title = "Add Address",
            icon = Icons.Default.ArrowBack,
            onNavigationClick = { navController.popBackStack() }
        )

        MyOrdersListScreen::class.qualifiedName -> TopBarConfig(
            title = "My Orders",
            icon = Icons.Default.ArrowBack,
            onNavigationClick = { navController.popBackStack() }
        )

        OrderDetailScreen::class.qualifiedName -> TopBarConfig(
            title = "Order Details",
            icon = Icons.Default.ArrowBack,
            onNavigationClick = { navController.popBackStack() }
        )

        StoreLocatorScreen::class.qualifiedName -> TopBarConfig(
            title = "Store Locator",
            icon = Icons.Default.ArrowBack,
            onNavigationClick = { navController.popBackStack() }
        )

        MyAccountScreen::class.qualifiedName -> TopBarConfig(
            title = "My Account",
            icon = Icons.Default.ArrowBack,
            onNavigationClick = { navController.popBackStack() }
        )

        EditProfileScreenNav::class.qualifiedName -> TopBarConfig(
            title = "Edit Profile",
            icon = Icons.Default.ArrowBack,
            onNavigationClick = { navController.popBackStack() }
        )

        else -> null
    }
}

data class TopBarConfig(
    val title: String,
    val icon: ImageVector,
    val endIcon: ImageVector? = null,
    val onEndIconClick: (() -> Unit)? = null,
    val onNavigationClick: (() -> Unit)? = null
)