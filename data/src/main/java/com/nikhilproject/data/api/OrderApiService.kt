package com.nikhilproject.data.api

import com.nikhilproject.data.model.dto.FetchOrderDetailsResponseDto
import com.nikhilproject.data.model.dto.GetAllOrderResponseDto
import com.nikhilproject.data.model.dto.OrderResponseDto
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Query

interface OrderApiService {

    @FormUrlEncoded
    @POST("api/order")
    suspend fun orderProduct(
        @Header("access_token") accessToken: String,
        @Field("address") address: String,
    ): OrderResponseDto


    @GET("api/orderList")
    suspend fun getAllOrders(
        @Header("access_token") accessToken: String,
    ): GetAllOrderResponseDto


    @GET("api/orderDetail")
    suspend fun fetchOrderDetail(
        @Header("access_token") accessToken: String,
        @Query("order_id") orderId: Int,
    ): FetchOrderDetailsResponseDto
}