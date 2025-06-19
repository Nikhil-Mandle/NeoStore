package com.nikhilproject.data.mapper

import com.nikhilproject.data.model.dto.DashboardResponseDto
import com.nikhilproject.data.model.dto.ProductCategory
import com.nikhilproject.data.model.dto.ProductData

fun DashboardResponseDto.toDomain(): com.nikhilproject.domain.model.DashboardResponse {
    return com.nikhilproject.domain.model.DashboardResponse(
        productData = productData.toDomain(),
        status = status
    )
}

fun ProductData.toDomain(): com.nikhilproject.domain.model.ProductData {
    return com.nikhilproject.domain.model.ProductData(
        product_categories = product_categories.map { it.toDomain() },
        total_carts = total_carts,
        total_orders = total_orders,
        user_data = user_data.toDomain()
    )
}

fun ProductCategory.toDomain(): com.nikhilproject.domain.model.ProductCategory {
    return com.nikhilproject.domain.model.ProductCategory(
        created = created,
        icon_image = icon_image,
        id = id,
        modified = modified,
        name = name
    )
}
