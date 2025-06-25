package com.nikhilproject.data.mapper

import com.nikhilproject.database.entity.AddressEntity
import com.nikhilproject.domain.model.addressmodel.Address

fun AddressEntity.toDomain() = Address(
    id = id,
    address = address,
    landmark = landmark,
    city = city,
    state = state,
    zipCode = zipCode,
    country = country
)

fun Address.toEntity() = AddressEntity(
    id = id,
    address = address,
    landmark = landmark,
    city = city,
    state = state,
    zipCode = zipCode,
    country = country
)
