package com.nikhilproject.domain.usecase

import com.nikhilproject.domain.model.OrderResponse
import com.nikhilproject.domain.repository.OrderRepository

class OrderProductUseCase(
    private val orderRepository: OrderRepository
) {
    suspend operator fun invoke(accessToken: String, address: String): OrderResponse {
        return orderRepository.orderProduct(accessToken, address)
    }
}
