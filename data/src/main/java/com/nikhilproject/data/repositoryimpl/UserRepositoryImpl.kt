package com.nikhilproject.data.repositoryimpl

import com.nikhilproject.data.api.UserApiService
import com.nikhilproject.data.mapper.toDomain
import com.nikhilproject.domain.model.DashboardResponse
import com.nikhilproject.domain.model.ForgetPasswordResponse
import com.nikhilproject.domain.model.LogInRequest
import com.nikhilproject.domain.model.RegisterRequest
import com.nikhilproject.domain.model.ResetPasswordResponse
import com.nikhilproject.domain.model.User
import com.nikhilproject.domain.repository.UserRepository
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val api: UserApiService
) : UserRepository {
    override suspend fun register(request: RegisterRequest): User {
        val resp = api.registerUser(
            firstName = request.firstName,
            lastName = request.lastName,
            email = request.email,
            password = request.password,
            confirmPassword = request.confirmPassword,
            gender = request.gender,
            phoneNumber = request.phoneNo
        )
        return resp.data.toDomain()
    }

    override suspend fun login(request: LogInRequest): User {
        val resp = api.loginUser(
            email = request.email,
            password = request.password
        )
        return resp.user.toDomain()
    }

    override suspend fun fetchUserAccountDetails(
        accessToken: String
    ): DashboardResponse {
        val response = api.fetchUserAccountDetails(accessToken = accessToken)
        return response.toDomain()
    }

    override suspend fun forgetPassword(email: String): ForgetPasswordResponse {
        val response = api.forgetPassword(email = email)
        return response.toDomain()
    }

    override suspend fun changePassword(
        token: String,
        old_password: String,
        password: String,
        confirm_password: String
    ): ResetPasswordResponse {
        val response = api.changePassword(
            token = token,
            old_password = old_password,
            password = password,
            confirm_password = confirm_password
        )
        return response.toDomain()
    }

    override suspend fun updateUserProfile(
        token: String,
        firstName: String,
        lastName: String,
        email: String,
        dob: String,
        phoneNo: String,
        profilePic: String
    ): User {
        val response = api.updateUserProfile(
            token = token,
            firstName = firstName,
            lastName = lastName,
            email = email,
            dob = dob,
            phoneNo = phoneNo,
            profilePic = profilePic
        )
        return response.data.toDomain()
    }
}