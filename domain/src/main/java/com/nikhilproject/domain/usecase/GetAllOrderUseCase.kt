package com.nikhilproject.domain.usecase

import com.nikhilproject.domain.model.GetAllOrderResponse
import com.nikhilproject.domain.repository.OrderRepository

class GetAllOrderUseCase(
    private val orderRepository: OrderRepository
) {
    suspend operator fun invoke(accessToken: String): GetAllOrderResponse {
        return orderRepository.getAllOrders(accessToken)
    }
}
