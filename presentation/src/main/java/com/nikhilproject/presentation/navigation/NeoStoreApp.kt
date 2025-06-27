package com.nikhilproject.presentation.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.nikhilproject.presentation.SharedPreferenceManager
import com.nikhilproject.presentation.screens.components.DrawerContent
import com.nikhilproject.presentation.viewmodel.UserViewModel

@Composable
fun NeoStoreApp(
    navController: NavHostController,
    sharedPref: SharedPreferenceManager
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    val topBarConfig = getTopBarConfig(currentRoute, navController, drawerState, scope)
    val userViewModel: UserViewModel = hiltViewModel()

    val content: @Composable () -> Unit = {
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
            AppNavHost(navController = navController, sharedPref = sharedPref, modifier = Modifier.padding(innerPadding))
        }
    }

    if (currentRoute == HomeScreen::class.qualifiedName) {
        ModalNavigationDrawer(
            drawerContent = {
                ModalDrawerSheet {
                    DrawerContent(
                        navController = navController,
                        drawerState = drawerState,
                        scope = scope,
                        userName = userViewModel.getUserName() ?: "",
                        userEmail = userViewModel.getUserEmail() ?: "",
                        profilePic = userViewModel.getProfilePic() ?: "",
                        sharedPref = sharedPref
                    )
                }
            },
            drawerState = drawerState
        ) {
            content()
        }
    } else {
        content()
    }
}

@Composable
fun CrashTestButton() {
    Button(onClick = {
        throw RuntimeException("Test Crash: This is a forced crash to test Firebase Crashlytics integration.")
    }) {
        Text("Force Crash")
    }
}

