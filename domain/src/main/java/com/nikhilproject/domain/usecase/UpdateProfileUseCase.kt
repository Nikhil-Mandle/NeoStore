package com.nikhilproject.domain.usecase

import com.nikhilproject.domain.model.User
import com.nikhilproject.domain.repository.UserRepository

class UpdateProfileUseCase(
    private val repository: UserRepository
) {
    suspend operator fun invoke(
        token: String,
        firstName: String,
        lastName: String,
        email: String,
        dob: String,
        phoneNo: String,
        profilePic: String
    ): User {
        return repository.updateUserProfile(
            token = token,
            firstName = firstName,
            lastName = lastName,
            email = email,
            dob = dob,
            phoneNo = phoneNo,
            profilePic = profilePic
        )
    }
}