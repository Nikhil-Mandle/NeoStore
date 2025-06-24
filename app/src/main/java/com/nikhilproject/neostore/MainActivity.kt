package com.nikhilproject.neostore

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextAlign
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.nikhilproject.presentation.navigation.AppNavHost
import com.nikhilproject.presentation.navigation.AppSettingsScreen
import com.nikhilproject.presentation.navigation.HomeScreen
import com.nikhilproject.presentation.navigation.ProductDetailScreenNav
import com.nikhilproject.presentation.navigation.ProductListScreen
import com.nikhilproject.presentation.navigation.UpdateProfileScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        enableEdgeToEdge()
        setContent {

            val navController = rememberNavController()
            NeoStoreApp(navController = navController)

        }
    }
}

@Composable
fun NeoStoreApp(navController: NavHostController) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    Scaffold(
        topBar = {
            when (currentRoute) {
                HomeScreen::class.qualifiedName -> {
                    TopBar(
                        title = "NeoSTORE",
                        showBackButton = false,
                        navController = navController
                    )
                }

                ProductListScreen::class.qualifiedName -> {
                    TopBar(
                        title = "Products",
                        showBackButton = true,
                        navController = navController
                    )
                }

                ProductDetailScreenNav::class.qualifiedName -> {
                    TopBar(
                        title = "Product Detail",
                        showBackButton = true,
                        navController = navController
                    )
                }
                UpdateProfileScreen::class.qualifiedName -> {
                    TopBar(
                        title = "Update Profile",
                        showBackButton = true,
                        navController = navController
                    )
                }
                AppSettingsScreen::class.qualifiedName -> {
                    TopBar(
                        title = "Settings",
                        showBackButton = true,
                        navController = navController
                    )
                }
                else -> {}
            }
        }
    ) { innerPadding ->
        AppNavHost(navController = navController, modifier = Modifier.padding(innerPadding))
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(
    title: String,
    showBackButton: Boolean,
    navController: NavController,
    icon: ImageVector? = null
) {
    TopAppBar(
        modifier = Modifier.background(Color.Green),
        title = { Text(text = title, textAlign = TextAlign.Center) },
        navigationIcon = {
            if (showBackButton) {
                IconButton(onClick = { navController.popBackStack() }) {
                    Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                }

            } else {
                IconButton(onClick = {

                }) {
                    Icon(Icons.Default.Menu, contentDescription = "Back")
                }
            }
        },
        actions = {
            icon?.let {
                IconButton(onClick = { /* Handle icon click */ }) {
                    Icon(imageVector = it, contentDescription = null)
                }
            }
        }
    )
}