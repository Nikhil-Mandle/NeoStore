package com.nikhilproject.data.di

import com.nikhilproject.domain.repository.AddressRepository
import com.nikhilproject.domain.repository.CartRepository
import com.nikhilproject.domain.repository.OrderRepository
import com.nikhilproject.domain.repository.ProductRepository
import com.nikhilproject.domain.repository.UserRepository
import com.nikhilproject.domain.usecase.AddToCartUseCase
import com.nikhilproject.domain.usecase.ChangePasswordUseCase
import com.nikhilproject.domain.usecase.DeleteCartItemUseCase
import com.nikhilproject.domain.usecase.EditCartItemUseCase
import com.nikhilproject.domain.usecase.FetchCartItemUseCase
import com.nikhilproject.domain.usecase.FetchDashboardUseCase
import com.nikhilproject.domain.usecase.FetchOrderDetailUseCase
import com.nikhilproject.domain.usecase.FetchProductDetailsUseCase
import com.nikhilproject.domain.usecase.FetchProductListUseCase
import com.nikhilproject.domain.usecase.ForgetPasswordUseCase
import com.nikhilproject.domain.usecase.GetAllOrderUseCase
import com.nikhilproject.domain.usecase.LoginUserUseCase
import com.nikhilproject.domain.usecase.OrderProductUseCase
import com.nikhilproject.domain.usecase.RegisterUserUseCase
import com.nikhilproject.domain.usecase.SetProductRatingUseCase
import com.nikhilproject.domain.usecase.UpdateProfileUseCase
import com.nikhilproject.domain.usecase.addressusecase.AddAddressUseCase
import com.nikhilproject.domain.usecase.addressusecase.DeleteAddressUseCase
import com.nikhilproject.domain.usecase.addressusecase.GetAllAddressesUseCase
import com.nikhilproject.domain.usecase.addressusecase.UpdateAddressUseCase
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
    fun provideUpdateProfileUseCase(
        userRepository: UserRepository
    ): UpdateProfileUseCase {
        return UpdateProfileUseCase(userRepository)
    }

    @Provides
    fun provideForgetPasswordUseCase(
        userRepository: UserRepository
    ): ForgetPasswordUseCase {
        return ForgetPasswordUseCase(userRepository)
    }

    @Provides
    fun provideChangePasswordUseCase(
        userRepository: UserRepository
    ): ChangePasswordUseCase {
        return ChangePasswordUseCase(userRepository)
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

    @Provides
    fun provideOrderProductUseCase(
        orderRepository: OrderRepository
    ): OrderProductUseCase {
        return OrderProductUseCase(orderRepository)
    }

    @Provides
    fun provideGetAllOrderUseCase(
        orderRepository: OrderRepository
    ): GetAllOrderUseCase {
        return GetAllOrderUseCase(orderRepository)
    }

    @Provides
    fun provideFetchOrderDetailUseCase(
        orderRepository: OrderRepository
    ): FetchOrderDetailUseCase {
        return FetchOrderDetailUseCase(orderRepository)
    }

    @Provides
    fun provideAddToCartUseCase(
        cartRepository: CartRepository
    ): AddToCartUseCase {
        return AddToCartUseCase(cartRepository)
    }

    @Provides
    fun provideFetchCartItemUseCase(
        cartRepository: CartRepository
    ): FetchCartItemUseCase {
        return FetchCartItemUseCase(cartRepository)
    }

    @Provides
    fun provideEditCartItemUseCase(
        cartRepository: CartRepository
    ): EditCartItemUseCase {
        return EditCartItemUseCase(cartRepository)
    }

    @Provides
    fun provideDeleteCartItemUseCase(
        cartRepository: CartRepository
    ): DeleteCartItemUseCase {
        return DeleteCartItemUseCase(cartRepository)
    }

    @Provides
    fun provideGetAllAddressesUseCase(
        addressRepository: AddressRepository
    ): GetAllAddressesUseCase {
        return GetAllAddressesUseCase(addressRepository)
    }

    @Provides
    fun provideAddAddressUseCase(
        addressRepository: AddressRepository
    ): AddAddressUseCase {
        return AddAddressUseCase(addressRepository)
    }

    @Provides
    fun provideUpdateAddressUseCase(
        addressRepository: AddressRepository
    ): UpdateAddressUseCase {
        return UpdateAddressUseCase(addressRepository)
    }

    @Provides
    fun provideDeleteAddressUseCase(
        addressRepository: AddressRepository
    ): DeleteAddressUseCase {
        return DeleteAddressUseCase(addressRepository)
    }

}