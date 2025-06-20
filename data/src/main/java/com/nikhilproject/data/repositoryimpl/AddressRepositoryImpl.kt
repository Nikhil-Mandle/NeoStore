package com.nikhilproject.data.repositoryimpl

import com.nikhilproject.database.dao.AddressDao
import com.nikhilproject.database.entity.AddressEntity
import com.nikhilproject.domain.repository.AddressRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AddressRepositoryImpl @Inject constructor(
    private val addressDao: AddressDao
) : AddressRepository {

    override suspend fun addAddress(address: AddressEntity) {
        addressDao.insertAddress(address)
    }

    override suspend fun updateAddress(address: AddressEntity) {
        addressDao.updateAddress(address)
    }

    override suspend fun deleteAddress(address: AddressEntity) {
        addressDao.deleteAddress(address)
    }

    override fun getAllAddresses(): Flow<List<AddressEntity>> {
        return addressDao.getAllAddresses()
    }
}
