package com.nikhilproject.data.model.dto

import com.google.gson.annotations.SerializedName
import com.nikhilproject.domain.model.ResetPasswordResponse

data class ResetPasswordResponseDto(
    @SerializedName("data")
    val data: List<Any>,
    @SerializedName("message")
    val message: String,
    @SerializedName("status")
    val status: Int,
    @SerializedName("user_msg")
    val user_msg: String
) {
    fun toDomain() = ResetPasswordResponse(
        data = data,
        message = message,
        status = status,
        user_msg = user_msg
    )
}