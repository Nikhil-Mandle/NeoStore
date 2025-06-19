package com.nikhilproject.data.model.dto

import com.google.gson.annotations.SerializedName

data class ForgetPasswordResponseDto(
    val message: String,
    val status: Int,
    @SerializedName("user_msg") val userMsg: String
)
