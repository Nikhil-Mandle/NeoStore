package com.nikhilproject.data.repositoryimpl

import com.nikhilproject.data.api.OrderApiService
import com.nikhilproject.data.mapper.toDomain
import com.nikhilproject.domain.model.FetchOrderDetailsResponse
import com.nikhilproject.domain.model.GetAllOrderResponse
import com.nikhilproject.domain.model.OrderResponse
import com.nikhilproject.domain.repository.OrderRepository
import javax.inject.Inject

class OrderRepositoryImpl @Inject constructor(
    private val orderApiService: OrderApiService
) : OrderRepository {
    override suspend fun orderProduct(accessToken: String, address: String): OrderResponse {
        val response = orderApiService.orderProduct(accessToken, address)
        return response.toDomain()
    }

    override suspend fun getAllOrders(accessToken: String): GetAllOrderResponse {
        val response = orderApiService.getAllOrders(accessToken)
        return response.toDomain()
    }

    override suspend fun fetchOrderDetail(
        accessToken: String,
        orderId: Int
    ): FetchOrderDetailsResponse {
        val response = orderApiService.fetchOrderDetail(accessToken, orderId)
        return response.toDomain()
    }

}