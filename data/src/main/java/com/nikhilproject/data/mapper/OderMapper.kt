package com.nikhilproject.data.mapper

import com.nikhilproject.data.model.dto.FetchOrderDetailsDataDto
import com.nikhilproject.data.model.dto.FetchOrderDetailsResponseDto
import com.nikhilproject.data.model.dto.GetAllOrderResponseDto
import com.nikhilproject.data.model.dto.OneOrderDetailDto
import com.nikhilproject.data.model.dto.OrderDetailsDto
import com.nikhilproject.data.model.dto.OrderResponseDto
import com.nikhilproject.domain.model.FetchOrderDetailsData
import com.nikhilproject.domain.model.FetchOrderDetailsResponse
import com.nikhilproject.domain.model.GetAllOrderResponse
import com.nikhilproject.domain.model.OneOrderDetail
import com.nikhilproject.domain.model.OrderDetails
import com.nikhilproject.domain.model.OrderResponse

fun OrderResponseDto.toDomain(): OrderResponse {
    return OrderResponse(
        message = message,
        status = status,
        user_msg = user_msg
    )
}

fun GetAllOrderResponseDto.toDomain(): GetAllOrderResponse {
    return GetAllOrderResponse(
        data = data.map { it.toDomain() },
        status = status,
    )
}

fun OrderDetailsDto.toDomain(): OrderDetails {
    return OrderDetails(
        cost = cost,
        created = created,
        id = id
    )
}

fun FetchOrderDetailsResponseDto.toDomain(): FetchOrderDetailsResponse {
    return FetchOrderDetailsResponse(
        data = data.toDomain(),
        status = status
    )
}

fun FetchOrderDetailsDataDto.toDomain(): FetchOrderDetailsData {
    return FetchOrderDetailsData(
        cost = cost,
        address = address,
        id = id,
        order_details = order_details.map { it.toDomain() }
    )
}

fun OneOrderDetailDto.toDomain(): OneOrderDetail {
    return OneOrderDetail(
        id = id,
        order_id = order_id,
        prod_cat_name = prod_cat_name,
        prod_image = prod_image,
        prod_name = prod_name,
        product_id = product_id,
        quantity = quantity,
        total = total
    )
}
