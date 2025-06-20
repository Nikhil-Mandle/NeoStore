package com.nikhilproject.data.mapper

import com.nikhilproject.data.model.dto.CartListResponseDto
import com.nikhilproject.data.model.dto.CartOperationResponseDto
import com.nikhilproject.data.model.dto.ProductDto
import com.nikhilproject.data.model.dto.ProductItemDataDto
import com.nikhilproject.domain.model.CartListResponse
import com.nikhilproject.domain.model.CartOperationResponse
import com.nikhilproject.domain.model.Product
import com.nikhilproject.domain.model.ProductItemData

fun CartListResponseDto.toDomain(): CartListResponse {

    return CartListResponse(
        count = count,
        data = productItem.map { it.toDomain() },
        status = status,
        total = total
    )
}

fun ProductItemDataDto.toDomain(): ProductItemData {
    return ProductItemData(
        id = id,
        product = product.toDomain(),
        product_id = productId,
        quantity = quantity
    )
}

fun ProductDto.toDomain(): Product {
    return Product(
        cost = cost,
        id = id,
        name = name,
        product_category = productCategory,
        product_images = productImages,
        sub_total = subTotal
    )
}

fun CartOperationResponseDto.toDomain(): CartOperationResponse {
    return CartOperationResponse(
        message = message,
        status = status,
        userMsg = userMsg,
        totalCarts = totalCarts,
        data = data
    )
}
