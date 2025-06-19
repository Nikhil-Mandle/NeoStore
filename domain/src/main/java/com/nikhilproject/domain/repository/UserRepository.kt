package com.nikhilproject.domain.repository

import com.nikhilproject.domain.model.DashboardResponse
import com.nikhilproject.domain.model.LogInRequest
import com.nikhilproject.domain.model.RegisterRequest
import com.nikhilproject.domain.model.User

interface UserRepository {
    suspend fun register(request: RegisterRequest): User
    suspend fun login(request: LogInRequest): User
    suspend fun fetchUserAccountDetails(accessToken: String): DashboardResponse
}