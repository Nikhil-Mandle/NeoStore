package com.nikhilproject.domain.model

data class User(
    val id: Int,
    val roleId: Int,
    val firstName: String,
    val lastName: String,
    val email: String,
    val username: String,
    val profilePic: String?,
    val countryId: Int?,
    val gender: String,
    val phoneNo: Long,
    val dob: String?,
    val isActive: Boolean,
    val created: String,
    val modified: String,
    val accessToken: String
)