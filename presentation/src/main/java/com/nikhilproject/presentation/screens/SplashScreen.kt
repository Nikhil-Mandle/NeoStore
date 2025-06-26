package com.nikhilproject.presentation.screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavHostController
import com.nikhilproject.presentation.SharedPreferenceManager
import com.nikhilproject.presentation.navigation.AuthGraph
import com.nikhilproject.presentation.navigation.HomeGraph
import com.nikhilproject.presentation.navigation.LoginScreen
import com.nikhilproject.presentation.navigation.SplashScreenNav

/*@Composable
fun SplashScreen(
    navController: NavHostController,
    sharedPref: SharedPreferenceManager
) {
    LaunchedEffect(Unit) {
        val token = sharedPref.getAccessToken()
        if (!token.isNullOrEmpty()) {
            navController.navigate(HomeGraph) {
                popUpTo(SplashScreenNav) { inclusive = true }
                launchSingleTop = true
            }
        } else {
            navController.navigate(AuthGraph) {
                popUpTo(SplashScreenNav) { inclusive = true }
                launchSingleTop = true
            }
        }
    }
}*/

@Composable
fun SplashScreen(navController: NavHostController, sharedPref: SharedPreferenceManager) {
    LaunchedEffect(Unit) {
        val token = sharedPref.getAccessToken()
        if (token.isNullOrEmpty()) {
            navController.navigate(LoginScreen) {
                popUpTo(SplashScreenNav) { inclusive = true }  // remove SplashScreen from back stack
            }
        } else {
            navController.navigate(HomeGraph) {
                popUpTo(SplashScreenNav) { inclusive = true }  // remove SplashScreen from back stack
            }
        }
    }
}
