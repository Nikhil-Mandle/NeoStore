package com.nikhilproject.domain.model

data class OrderResponse(
    val message: String,
    val status: Int,
    val user_msg: String
)