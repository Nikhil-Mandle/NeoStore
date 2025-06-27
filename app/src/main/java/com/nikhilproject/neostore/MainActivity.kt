package com.nikhilproject.neostore

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.rememberNavController
import com.google.firebase.crashlytics.FirebaseCrashlytics
import com.nikhilproject.presentation.SharedPreferenceManager
import com.nikhilproject.presentation.navigation.NeoStoreApp
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @Inject
    lateinit var sharedPref: SharedPreferenceManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        installSplashScreen()
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()

            NeoStoreApp(
                navController = navController,
                sharedPref = sharedPref
            )
        }
    }
}
