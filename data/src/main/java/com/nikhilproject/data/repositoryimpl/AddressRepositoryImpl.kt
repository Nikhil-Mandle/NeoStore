package com.nikhilproject.data.repositoryimpl

import com.nikhilproject.data.mapper.toDomain
import com.nikhilproject.data.mapper.toEntity
import com.nikhilproject.database.dao.AddressDao
import com.nikhilproject.database.entity.AddressEntity
import com.nikhilproject.domain.model.addressmodel.Address
import com.nikhilproject.domain.repository.AddressRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class AddressRepositoryImpl @Inject constructor(
    private val addressDao: AddressDao
) : AddressRepository {

    override suspend fun addAddress(address: Address) {
        addressDao.insertAddress(address.toEntity())
    }

    override suspend fun updateAddress(address: Address) {
        addressDao.updateAddress(address.toEntity())
    }

    override suspend fun deleteAddress(address: Address) {
        addressDao.deleteAddress(address.toEntity())
    }

    override fun getAllAddresses(): Flow<List<Address>> {
        return addressDao.getAllAddresses().map { list -> list.map { it.toDomain() } }
    }
}
