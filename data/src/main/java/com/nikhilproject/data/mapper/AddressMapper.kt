package com.nikhilproject.data.mapper

import com.nikhilproject.database.entity.AddressEntity
import com.nikhilproject.domain.model.addressmodel.Address

fun AddressEntity.toDomain() = Address(
    id = id,
    name = name,
    street = street,
    city = city,
    state = state,
    zip = zip
)


fun Address.toEntity() = AddressEntity(
    id = id,
    name = name,
    street = street,
    city = city,
    state = state,
    zip = zip
)
