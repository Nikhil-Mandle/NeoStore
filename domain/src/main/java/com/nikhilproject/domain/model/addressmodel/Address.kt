package com.nikhilproject.domain.model.addressmodel

data class Address(
    val id: Int = 0,
    val address: String,
    val landmark: String,
    val city: String,
    val state: String,
    val zipCode: String,
    val country: String
)