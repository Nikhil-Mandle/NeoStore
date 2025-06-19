package com.nikhilproject.presentation.navigation


import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.nikhilproject.presentation.screens.AppSettingsScreen
import com.nikhilproject.presentation.screens.HomeScreen
import com.nikhilproject.presentation.screens.LoginScreen
import com.nikhilproject.presentation.screens.ProductDetailScreen
import com.nikhilproject.presentation.screens.ProductListScreen
import com.nikhilproject.presentation.screens.RegisterScreen
import com.nikhilproject.presentation.screens.UpdateProfileScreen

@Composable
fun AppNavHost(navController: NavHostController, modifier: Modifier) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = Routes.AUTH_GRAPH
    ) {
        navigation(
            startDestination = Routes.LOGIN,
            route = Routes.AUTH_GRAPH
        ) {
            composable(Routes.REGISTER) {
                RegisterScreen(onSuccessNavigate = {
                    navController.navigate(Routes.HOME_GRAPH)
                })
            }

            composable(Routes.LOGIN) {
                LoginScreen(onLoginSuccess = {
                    navController.navigate(Routes.HOME_GRAPH)
                }, onDontHaveAnAccountClick = {
                    navController.navigate(Routes.REGISTER)
                })
            }
        }

        navigation(
            startDestination = Routes.HOME_SCREEN,
            route = Routes.HOME_GRAPH
        ) {
            composable(Routes.HOME_SCREEN) {
                HomeScreen(OnItemClick = {
                    navController.navigate(Routes.PRODUCT_GRAPH)
                })
            }
        }

        navigation(startDestination = Routes.PRODUCT_LIST, route = Routes.PRODUCT_GRAPH) {
            composable(Routes.PRODUCT_LIST) {
                ProductListScreen() {
                    navController.navigate(Routes.PRODUCT_DETAIL)
                }
            }

            composable(Routes.PRODUCT_DETAIL) {
                ProductDetailScreen(1)
            }
        }

        navigation(
            startDestination = Routes.UPDATE_PROFILE,
            route = Routes.PROFILE_GRAPH
        ) {
            composable(Routes.UPDATE_PROFILE) {
                UpdateProfileScreen()
            }
        }

        navigation(
            startDestination = Routes.APP_SETTINGS,
            route = Routes.SETTINGS_GRAPH
        ) {
            composable(Routes.APP_SETTINGS) {
                AppSettingsScreen()
            }
        }
    }
}


