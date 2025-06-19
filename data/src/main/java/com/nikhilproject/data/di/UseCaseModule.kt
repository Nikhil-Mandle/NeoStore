package com.nikhilproject.data.di

import com.nikhilproject.domain.repository.ProductRepository
import com.nikhilproject.domain.repository.UserRepository
import com.nikhilproject.domain.usecase.FetchDashboardUseCase
import com.nikhilproject.domain.usecase.FetchProductDetailsUseCase
import com.nikhilproject.domain.usecase.FetchProductListUseCase
import com.nikhilproject.domain.usecase.LoginUserUseCase
import com.nikhilproject.domain.usecase.RegisterUserUseCase
import com.nikhilproject.domain.usecase.SetProductRatingUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Provides
    fun provideRegisterUserUseCase(
        userRepository: UserRepository
    ): RegisterUserUseCase {
        return RegisterUserUseCase(userRepository)
    }

    @Provides
    fun provideLoginUserUseCase(
        userRepository: UserRepository
    ): LoginUserUseCase {
        return LoginUserUseCase(userRepository)
    }

    @Provides
    fun provideFetchDashboardUseCase(
        userRepository: UserRepository
    ): FetchDashboardUseCase {
        return FetchDashboardUseCase(userRepository)
    }

    @Provides
    fun provideFetchProductListUseCase(
        productRepository: ProductRepository
    ): FetchProductListUseCase {
        return FetchProductListUseCase(productRepository)
    }

    @Provides
    fun provideFetchProductDetailsUseCase(
        productRepository: ProductRepository
    ): FetchProductDetailsUseCase {
        return FetchProductDetailsUseCase(productRepository)
    }

    @Provides
    fun provideSetProductRatingUseCase(
        productRepository: ProductRepository
    ): SetProductRatingUseCase {
        return SetProductRatingUseCase(productRepository)
    }

}