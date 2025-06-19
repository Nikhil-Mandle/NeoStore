package com.nikhilproject.data.model.dto

import com.google.gson.annotations.SerializedName
import com.nikhilproject.domain.model.User

data class UpdateProfileResponseDto(
    @SerializedName("data")
    val data: User,
    @SerializedName("message")
    val message: String,
    @SerializedName("status")
    val status: Int,
    @SerializedName("user_msg")
    val user_msg: String
)