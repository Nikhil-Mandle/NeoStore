package com.nikhilproject.data.repositoryimpl

import com.nikhilproject.data.api.OrderApiService
import com.nikhilproject.domain.repository.OrderRepository
import javax.inject.Inject

class OrderRepositoryImpl @Inject constructor(
    private val orderApiService: OrderApiService
): OrderRepository {

}