package com.nikhilproject.presentation.navigation


import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.nikhilproject.presentation.screens.AppSettingsScreen
import com.nikhilproject.presentation.screens.HomeScreen
import com.nikhilproject.presentation.screens.ProductDetailScreen
import com.nikhilproject.presentation.screens.ProductListScreen
import com.nikhilproject.presentation.screens.UpdateProfileScreen
import com.nikhilproject.presentation.screens.authscreens.LoginScreen
import com.nikhilproject.presentation.screens.authscreens.RegisterScreen

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
        updateProfileGraph(navController)
        appSettingsGraph(navController)
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
            HomeScreen(onItemClick = {
                navController.navigate(ProductGraph)
            })
        }
    }
}

fun NavGraphBuilder.productGraph(navController: NavHostController) {
    navigation<ProductGraph>(startDestination = ProductListScreen) {
        composable<ProductListScreen> {
            ProductListScreen() {
                navController.navigate(ProductDetailScreenNav(productId = 1))
            }
        }

        composable<ProductDetailScreenNav> {
            ProductDetailScreen(1)
        }
    }
}

fun NavGraphBuilder.updateProfileGraph(navController: NavHostController) {

    navigation<ProfileGraph>(
        startDestination = UpdateProfileScreen,
    ) {
        composable<UpdateProfileScreen> {
            UpdateProfileScreen()
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
        startDestination = CartScreen,
    ) {
        composable<CartScreen> {
            AppSettingsScreen()
        }
    }
}

fun NavGraphBuilder.ordersGraph(navController: NavHostController) {
    navigation<OrdersGraph>(
        startDestination = OrdersScreen,
    ) {
        composable<OrdersScreen> {
            AppSettingsScreen()
        }
    }
}