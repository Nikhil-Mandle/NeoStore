package com.nikhilproject.domain.repository

import com.nikhilproject.domain.model.FetchOrderDetailsResponse
import com.nikhilproject.domain.model.GetAllOrderResponse
import com.nikhilproject.domain.model.OrderResponse

interface OrderRepository {
    suspend fun orderProduct(accessToken: String, address: String): OrderResponse
    suspend fun getAllOrders(accessToken: String): GetAllOrderResponse
    suspend fun fetchOrderDetail(accessToken: String, orderId: Int): FetchOrderDetailsResponse
}
