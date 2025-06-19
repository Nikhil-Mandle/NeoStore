package com.nikhilproject.data.mapper

import com.nikhilproject.data.model.dto.ProductDetailsDataDto
import com.nikhilproject.data.model.dto.ProductDetailsResponseDto
import com.nikhilproject.data.model.dto.ProductImageDto
import com.nikhilproject.data.model.dto.ProductItemDto
import com.nikhilproject.data.model.dto.ProductsListResponseDto
import com.nikhilproject.data.model.dto.SetProductRatingDto
import com.nikhilproject.domain.model.ProductDetailResponse
import com.nikhilproject.domain.model.ProductDetailsData
import com.nikhilproject.domain.model.ProductImage
import com.nikhilproject.domain.model.ProductItem
import com.nikhilproject.domain.model.ProductsListResponse
import com.nikhilproject.domain.model.SetProductRatingResponse

fun ProductsListResponseDto.toDomain(): ProductsListResponse {
    return ProductsListResponse(
        data = productItemDto.map { it.toDomain() },
        status = status
    )
}

fun ProductItemDto.toDomain(): ProductItem {
    return ProductItem(
        cost = cost,
        created = created,
        description = description,
        id = id,
        modified = modified,
        name = name,
        producer = producer,
        product_category_id = productCategoryId,
        product_images = productImages,
        rating = rating,
        viewCount = viewCount
    )
}

fun ProductDetailsResponseDto.toDomain(): ProductDetailResponse {
    return ProductDetailResponse(
        data = productDetailsDataDto.toDomain(),
        status = status
    )
}

fun ProductDetailsDataDto.toDomain(): ProductDetailsData {
    return ProductDetailsData(
        cost = cost,
        created = created,
        description = description,
        id = id,
        modified = modified,
        name = name,
        producer = producer,
        product_category_id = product_category_id,
        product_images = product_images.map { it.toDomain() },
        rating = rating,
        view_count = view_count
    )
}

fun ProductImageDto.toDomain(): ProductImage {
    return ProductImage(
        created = created,
        id = id,
        image = image,
        modified = modified,
        product_id = product_id
    )
}

fun SetProductRatingDto.toDomain(): SetProductRatingResponse {
    return SetProductRatingResponse(
        data = data.toDomain(),
        message = message,
        status = status,
        user_msg = user_msg
    )
}