package com.nikhilproject.data.repositoryimpl

import com.nikhilproject.data.api.UserApiService
import com.nikhilproject.data.mapper.toDomain
import com.nikhilproject.domain.model.DashboardResponse
import com.nikhilproject.domain.model.LogInRequest
import com.nikhilproject.domain.model.RegisterRequest
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
        val responseDto = api.fetchUserAccountDetails(accessToken = accessToken)
        return responseDto.toDomain()
    }
}