package com.nikhilproject.data.model.dto

import com.google.gson.annotations.SerializedName

data class UserLoginResponse(
    @SerializedName("data") val user: UserDto,
    val message: String,
    val status: Int,
    @SerializedName("user_msg") val userMsg: String
)