package com.nikhilproject.domain.usecase

import com.nikhilproject.domain.model.FetchOrderDetailsResponse
import com.nikhilproject.domain.repository.OrderRepository

class FetchOrderDetailUseCase(
    private val orderRepository: OrderRepository
) {

    suspend operator fun invoke(accessToken: String, orderId: Int): FetchOrderDetailsResponse {
        return orderRepository.fetchOrderDetail(accessToken, orderId)
    }
}
