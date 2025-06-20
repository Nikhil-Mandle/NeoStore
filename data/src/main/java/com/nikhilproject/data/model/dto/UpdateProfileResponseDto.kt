package com.nikhilproject.data.model.dto

import com.google.gson.annotations.SerializedName
import com.nikhilproject.domain.model.UpdateProfileResponse

data class UpdateProfileResponseDto(
    @SerializedName("data")
    val data: UserDto,
    @SerializedName("message")
    val message: String,
    @SerializedName("status")
    val status: Int,
    @SerializedName("user_msg")
    val user_msg: String
) {
    fun toDomain() = UpdateProfileResponse (
        data = data.toDomain(),
        message = message,
        status = status,
        user_msg = user_msg
    )
}