package com.nikhilproject.data.model.dto

import com.nikhilproject.domain.model.User

data class RegisterResponseDto(
    val status: Int,
    val data: UserDto,
    val message: String,
    val user_msg: String
)

data class UserDto(
    val id: Int,
    val role_id: Int,
    val first_name: String,
    val last_name: String,
    val email: String,
    val username: String,
    val profile_pic: String?,
    val country_id: Int?,
    val gender: String,
    val phone_no: Long,
    val dob: String?,
    val is_active: Boolean,
    val created: String,
    val modified: String,
    val access_token: String
) {
    fun toDomain() = User(
        id = id,
        roleId = role_id,
        firstName = first_name,
        lastName = last_name,
        email = email,
        username = username,
        profilePic = profile_pic,
        countryId = country_id,
        gender = gender,
        phoneNo = phone_no,
        dob = dob,
        isActive = is_active,
        created = created,
        modified = modified,
        accessToken = access_token
    )
}