package com.nikhilproject.data.model.dto

import com.google.gson.annotations.SerializedName

data class ResetPasswordResponseDto(
    @SerializedName("data")
    val data: List<Any>,
    @SerializedName("message")
    val message: String,
    @SerializedName("status")
    val status: Int,
    @SerializedName("user_msg")
    val user_msg: String
)