package com.nikhilproject.domain.usecase.addressusecase

import com.nikhilproject.domain.model.addressmodel.Address
import com.nikhilproject.domain.repository.AddressRepository

class UpdateAddressUseCase(
    private val repository: AddressRepository
) {
    suspend operator fun invoke(address: Address) {
        repository.updateAddress(address)
    }
}