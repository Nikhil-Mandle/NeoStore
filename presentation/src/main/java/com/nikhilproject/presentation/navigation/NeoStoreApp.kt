package com.nikhilproject.presentation.navigation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.nikhilproject.presentation.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun NeoStoreApp() {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route


    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    ModalNavigationDrawer(
        drawerContent = {
            ModalDrawerSheet {
                DrawerContent(
                    navController = navController,
                    drawerState = drawerState,
                    scope = scope
                )
            }
        },
        drawerState = drawerState
    ) {
        Scaffold(
            topBar = {
                when (currentRoute) {
                    HomeScreen::class.qualifiedName -> {
                        TopBar(
                            title = "NeoSTORE",
                            navigationIcon = Icons.Default.Menu
                        ) {
                            scope.launch {
                                if (drawerState.isClosed) {
                                    drawerState.open()
                                } else {
                                    drawerState.close()
                                }
                            }
                        }
                    }

                    ProductListScreen::class.qualifiedName -> {
                        TopBar(
                            title = "Products",
                            navigationIcon = Icons.Default.ArrowBack
                        ) {
                            navController.popBackStack()
                        }
                    }

                    ProductDetailScreenNav::class.qualifiedName -> {
                        TopBar(
                            title = "Product Detail",
                            navigationIcon = Icons.Default.ArrowBack
                        ) {
                            navController.popBackStack()
                        }
                    }

                    ResetPasswordScreen::class.qualifiedName -> {
                        TopBar(
                            title = "Update Profile",
                            navigationIcon = Icons.Default.ArrowBack
                        ) {
                            navController.popBackStack()
                        }
                    }

                    AppSettingsScreen::class.qualifiedName -> {
                        TopBar(
                            title = "Settings",
                            navigationIcon = Icons.Default.ArrowBack
                        ) {
                            navController.popBackStack()
                        }
                    }

                    else -> {}
                }
            }
        ) { innerPadding ->
            AppNavHost(navController = navController, modifier = Modifier.padding(innerPadding))
        }
    }
}

@Composable
fun DrawerContent(
    navController: NavHostController,
    drawerState: DrawerState,
    scope: CoroutineScope,
    userName: String = "Kinjal Jain",
    userEmail: String = "kinjal.jain@wwindia.com",
    cartCount: Int = 2
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
                painter = painterResource(id = R.drawable.closet),
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
            badgeCount = cartCount
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

@Composable
fun DrawerItem(
    icon: Int,
    label: String,
    badgeCount: Int? = null,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 12.dp)
            .clickable { onClick() },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box {
            Icon(
                painter = painterResource(icon),
                contentDescription = label,
                modifier = Modifier.size(24.dp)
            )
            badgeCount?.let {
                if (it > 0) {
                    Box(
                        modifier = Modifier
                            .align(Alignment.TopEnd)
                            .offset(x = 12.dp, y = (-6).dp)
                            .size(16.dp)
                            .background(Color.Red, CircleShape),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = it.toString(),
                            color = Color.White,
                            fontSize = 10.sp
                        )
                    }
                }
            }
        }
        Spacer(Modifier.width(24.dp))
        Text(text = label, style = MaterialTheme.typography.bodyMedium)
    }
}
