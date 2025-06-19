package com.nikhilproject.data.repositoryimpl

import com.nikhilproject.data.api.CartApiService
import com.nikhilproject.domain.repository.CartRepository
import javax.inject.Inject

class CartRepositoryImpl @Inject constructor(
    private val cartApiService: CartApiService
): CartRepository {
}