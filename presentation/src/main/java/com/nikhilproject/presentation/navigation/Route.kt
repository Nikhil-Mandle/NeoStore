package com.nikhilproject.presentation.navigation

import kotlinx.serialization.Serializable

@Serializable
data object AuthGraph

@Serializable
data object LoginScreen

@Serializable
data object RegisterScreen

@Serializable
data object HomeGraph

@Serializable
data object HomeScreen

@Serializable
data object ProductGraph

@Serializable
data object ProductListScreen

@Serializable
data class ProductDetailScreenNav(val productId: Int)

@Serializable
data object ProfileGraph

@Serializable
data object UpdateProfileScreen

@Serializable
data object SettingsGraph

@Serializable
data object AppSettingsScreen

@Serializable
data object CartGraph

@Serializable
data object CartScreen

@Serializable
data object OrdersGraph

@Serializable
data object OrdersScreen

