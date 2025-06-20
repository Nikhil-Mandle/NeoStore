package com.nikhilproject.domain.repository

import com.nikhilproject.domain.model.addressmodel.Address
import kotlinx.coroutines.flow.Flow

interface AddressRepository {
    suspend fun addAddress(address: Address)
    suspend fun updateAddress(address: Address)
    suspend fun deleteAddress(address: Address)
    fun getAllAddresses(): Flow<List<Address>>
}