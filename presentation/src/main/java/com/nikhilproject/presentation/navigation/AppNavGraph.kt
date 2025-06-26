package com.nikhilproject.presentation.navigation


import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.toRoute
import com.nikhilproject.presentation.screens.AppSettingsScreen
import com.nikhilproject.presentation.screens.HomeScreen
import com.nikhilproject.presentation.screens.ProductDetailScreen
import com.nikhilproject.presentation.screens.ProductListScreen
import com.nikhilproject.presentation.screens.StoreLocatorScreen
import com.nikhilproject.presentation.screens.accountsscreen.EditProfileScreen
import com.nikhilproject.presentation.screens.accountsscreen.MyAccountScreen
import com.nikhilproject.presentation.screens.accountsscreen.ResetPasswordScreen
import com.nikhilproject.presentation.screens.authscreens.LoginScreen
import com.nikhilproject.presentation.screens.authscreens.RegisterScreen
import com.nikhilproject.presentation.screens.cartscreens.AddAddressScreen
import com.nikhilproject.presentation.screens.cartscreens.AddressListScreen
import com.nikhilproject.presentation.screens.cartscreens.MyCartScreen
import com.nikhilproject.presentation.screens.orderscreens.MyOrdersList
import com.nikhilproject.presentation.screens.orderscreens.OrderDetailsScreen

@Composable
fun AppNavHost(navController: NavHostController, modifier: Modifier) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = AuthGraph
    ) {
        authGraph(navController)
        homeGraph(navController)
        productGraph(navController)
        profileGraph(navController)
        appSettingsGraph(navController)
        cartGraph(navController)
        addressGraph(navController)
        ordersGraph(navController)
        storeLocatorGraph(navController)
    }
}

fun NavGraphBuilder.authGraph(navController: NavHostController) {
    navigation<AuthGraph>(startDestination = LoginScreen) {

        composable<RegisterScreen> {
            RegisterScreen(
                onSuccessNavigate = {
                    navController.navigate(HomeGraph) {
                        popUpTo(AuthGraph) { inclusive = true }
                        launchSingleTop = true
                    }
                }
            )
        }

        composable<LoginScreen> {
            LoginScreen(
                onLoginSuccess = {
                    navController.navigate(HomeGraph) {
                        popUpTo(AuthGraph) { inclusive = true }
                        launchSingleTop = true
                    }
                },
                onDontHaveAnAccountClick = {
                    navController.navigate(RegisterScreen)
                }
            )
        }
    }
}


fun NavGraphBuilder.homeGraph(navController: NavHostController) {
    navigation<HomeGraph>(
        startDestination = HomeScreen,
    ) {
        composable<HomeScreen> {
            HomeScreen(onItemClick = { id ->
                navController.navigate(ProductListScreenNav(categoryId = id))
            })
        }
    }
}

fun NavGraphBuilder.productGraph(navController: NavHostController) {
    navigation<ProductGraph>(startDestination = ProductListScreenNav::class) {
        composable<ProductListScreenNav> { navEntry ->
            val args = navEntry.toRoute<ProductListScreenNav>()
            ProductListScreen(categoryId = args.categoryId) { id ->
                navController.navigate(ProductDetailScreenNav(productId = id))
            }
        }

        composable<ProductDetailScreenNav> { navEntry ->
            val args = navEntry.toRoute<ProductDetailScreenNav>()
            ProductDetailScreen(args.productId)
        }
    }
}

fun NavGraphBuilder.profileGraph(navController: NavHostController) {
    navigation<ProfileGraph>(
        startDestination = ResetPasswordScreen,
    ) {

        composable<MyAccountScreen> {
            MyAccountScreen(onEditProfileClicked = { profilePic ->
                navController.navigate(EditProfileScreenNav(profilePicUrl = profilePic ?: ""))
            }, onResetPasswordClicked = {
                navController.navigate(ResetPasswordScreen)
            })
        }

        composable<EditProfileScreenNav> { navEntry ->
            val args = navEntry.toRoute<EditProfileScreenNav>()
            EditProfileScreen(initialProfilePicUrl = args.profilePicUrl ?: "")
        }

        composable<ResetPasswordScreen> {
            ResetPasswordScreen()
        }
    }
}

fun NavGraphBuilder.appSettingsGraph(navController: NavHostController) {
    navigation<SettingsGraph>(
        startDestination = AppSettingsScreen,
    ) {
        composable<AppSettingsScreen> {
            AppSettingsScreen()
        }
    }
}

fun NavGraphBuilder.cartGraph(navController: NavHostController) {
    navigation<CartGraph>(
        startDestination = MyCartScreen,
    ) {
        composable<MyCartScreen> {
            MyCartScreen() {
                navController.navigate(AddressGraph)
            }
        }
    }
}

fun NavGraphBuilder.addressGraph(navController: NavHostController) {
    navigation<AddressGraph>(
        startDestination = AddressListScreen,
    ) {
        composable<AddressListScreen> {
            AddressListScreen() {
                navController.navigate(OrdersGraph)
            }
        }

        composable<AddAddressScreen> {
            AddAddressScreen()
        }
    }
}

fun NavGraphBuilder.ordersGraph(navController: NavHostController) {
    navigation<OrdersGraph>(
        startDestination = MyOrdersListScreen,
    ) {
        composable<MyOrdersListScreen> {
            MyOrdersList() { id ->
                navController.navigate(OrderDetailScreen(id = id))
            }
        }

        composable<OrderDetailScreen> { navEntry ->
            val args = navEntry.toRoute<OrderDetailScreen>()
            OrderDetailsScreen(id = args.id)
        }
    }
}

fun NavGraphBuilder.storeLocatorGraph(navController: NavHostController) {
    composable<StoreLocatorScreen> {
        StoreLocatorScreen()
    }
}
