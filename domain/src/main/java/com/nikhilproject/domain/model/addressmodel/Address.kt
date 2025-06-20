package com.nikhilproject.domain.model.addressmodel

// domain module
data class Address(
    val id: Int = 0,
    val name: String,
    val street: String,
    val city: String,
    val state: String,
    val zip: String
)