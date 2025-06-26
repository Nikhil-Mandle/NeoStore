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
data class ProductListScreenNav(val categoryId: Int)

@Serializable
data class ProductDetailScreenNav(val productId: Int)

@Serializable
data object ProfileGraph

@Serializable
data class EditProfileScreenNav(val profilePicUrl: String)

@Serializable
data object MyAccountScreen

@Serializable
data object ResetPasswordScreen

@Serializable
data object SettingsGraph

@Serializable
data object AppSettingsScreen

@Serializable
data object CartGraph

@Serializable
data object MyCartScreen

@Serializable
data object OrdersGraph

@Serializable
data object MyOrdersListScreen

@Serializable
data class OrderDetailScreen(val id: Int)

@Serializable
data object StoreLocatorScreen

@Serializable
data object AddressGraph

@Serializable
data object AddressListScreen

@Serializable
data object AddAddressScreen

