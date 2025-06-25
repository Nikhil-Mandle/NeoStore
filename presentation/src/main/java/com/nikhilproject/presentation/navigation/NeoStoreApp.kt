package com.nikhilproject.presentation.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.nikhilproject.presentation.screens.components.DrawerContent

@Composable
fun NeoStoreApp() {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    val topBarConfig = getTopBarConfig(currentRoute, navController, drawerState, scope)

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
                topBarConfig?.let { config ->
                    TopBar(
                        title = config.title,
                        navigationIcon = config.icon,
                        endIcon = config.endIcon,
                        onEndIconClick = {
                            config.endIcon?.let {
                                config.onEndIconClick?.invoke()
                            }
                        }
                    ) {
                        config.onNavigationClick?.invoke()
                    }

                }
            }
        ) { innerPadding ->
            AppNavHost(navController = navController, modifier = Modifier.padding(innerPadding))
        }
    }
}
