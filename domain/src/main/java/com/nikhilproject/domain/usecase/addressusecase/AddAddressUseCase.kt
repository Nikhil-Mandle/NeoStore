package com.nikhilproject.domain.usecase.addressusecase

import com.nikhilproject.domain.model.addressmodel.Address
import com.nikhilproject.domain.repository.AddressRepository

class AddAddressUseCase(
    private val addressRepository: AddressRepository
) {

    suspend operator fun invoke(address: Address) {
        addressRepository.addAddress(address)
    }
}