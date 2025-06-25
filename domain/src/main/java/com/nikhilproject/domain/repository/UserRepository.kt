package com.nikhilproject.domain.repository

import com.nikhilproject.domain.model.DashboardResponse
import com.nikhilproject.domain.model.ForgetPasswordResponse
import com.nikhilproject.domain.model.LogInRequest
import com.nikhilproject.domain.model.RegisterRequest
import com.nikhilproject.domain.model.ResetPasswordResponse
import com.nikhilproject.domain.model.UpdateProfileResponse
import com.nikhilproject.domain.model.User

interface UserRepository {
    suspend fun register(request: RegisterRequest): User
    suspend fun login(request: LogInRequest): User
    suspend fun fetchUserAccountDetails(accessToken: String): DashboardResponse
    suspend fun forgetPassword(email: String): ForgetPasswordResponse
    suspend fun changePassword(
        token: String,
        old_password: String,
        password: String,
        confirm_password: String
    ): ResetPasswordResponse

    suspend fun updateUserProfile(
        token: String,
        firstName: String,
        lastName: String,
        email: String,
        dob: String,
        phoneNo: String,
        profilePic: String
    ): UpdateProfileResponse

}