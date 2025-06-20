package com.nikhilproject.domain.usecase.addressusecase

import com.nikhilproject.domain.model.addressmodel.Address
import com.nikhilproject.domain.repository.AddressRepository
import kotlinx.coroutines.flow.Flow

class GetAllAddressesUseCase(
    private val repository: AddressRepository
) {
    operator fun invoke(): Flow<List<Address>> {
        return repository.getAllAddresses()
    }
}