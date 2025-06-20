package com.nikhilproject.data.model.dto

import com.google.gson.annotations.SerializedName
import com.nikhilproject.domain.model.ForgetPasswordResponse

data class ForgetPasswordResponseDto(
    val message: String,
    val status: Int,
    @SerializedName("user_msg") val userMsg: String
) {
    fun toDomain() = ForgetPasswordResponse(
        message = message,
        status = status,
        user_msg = userMsg
    )
}
