package com.nikhilproject.data.di

import com.nikhilproject.data.repositoryimpl.AddressRepositoryImpl
import com.nikhilproject.data.repositoryimpl.CartRepositoryImpl
import com.nikhilproject.data.repositoryimpl.OrderRepositoryImpl
import com.nikhilproject.data.repositoryimpl.ProductRepositoryImpl
import com.nikhilproject.data.repositoryimpl.UserRepositoryImpl
import com.nikhilproject.domain.repository.AddressRepository
import com.nikhilproject.domain.repository.CartRepository
import com.nikhilproject.domain.repository.OrderRepository
import com.nikhilproject.domain.repository.ProductRepository
import com.nikhilproject.domain.repository.UserRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindUserRepository(
        impl: UserRepositoryImpl
    ): UserRepository

    @Binds
    @Singleton
    abstract fun bindProductRepository(
        impl: ProductRepositoryImpl
    ): ProductRepository

    @Binds
    @Singleton
    abstract fun bindCartRepository(
        impl: CartRepositoryImpl
    ): CartRepository

    @Binds
    @Singleton
    abstract fun bindOrderRepository(
        impl: OrderRepositoryImpl
    ): OrderRepository

    @Binds
    @Singleton
    abstract fun bindAddressRepository(
        impl: AddressRepositoryImpl
    ): AddressRepository

}