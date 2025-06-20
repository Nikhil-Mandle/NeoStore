package com.nikhilproject.domain.repository

import com.nikhilproject.database.entity.AddressEntity
import kotlinx.coroutines.flow.Flow

interface AddressRepository {
    suspend fun addAddress(address: AddressEntity)
    suspend fun updateAddress(address: AddressEntity)
    suspend fun deleteAddress(address: AddressEntity)
    fun getAllAddresses(): Flow<List<AddressEntity>>
}