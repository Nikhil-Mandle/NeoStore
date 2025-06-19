package com.nikhilproject.data.api

import com.nikhilproject.data.model.dto.DashboardResponseDto
import com.nikhilproject.data.model.dto.ForgetPasswordResponseDto
import com.nikhilproject.data.model.dto.RegisterResponseDto
import com.nikhilproject.data.model.dto.ResetPasswordResponseDto
import com.nikhilproject.data.model.dto.UpdateProfileResponseDto
import com.nikhilproject.data.model.dto.UserLoginResponse
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface UserApiService {
    @FormUrlEncoded
    @POST("api/users/register")
    suspend fun registerUser(
        @Field("first_name") firstName: String,
        @Field("last_name") lastName: String,
        @Field("email") email: String,
        @Field("password") password: String,
        @Field("confirm_password") confirmPassword: String,
        @Field("gender") gender: String,
        @Field("phone_no") phoneNumber: Number
    ): RegisterResponseDto


    @FormUrlEncoded
    @POST("api/users/login")
    suspend fun loginUser(
        @Field("email") email: String,
        @Field("password") password: String
    ): UserLoginResponse

    @GET("api/users/getUserData")
    suspend fun fetchUserAccountDetails(
        @Header("access_token") accessToken: String
    ): DashboardResponseDto

    @FormUrlEncoded
    @POST("api/users/forgot")
    suspend fun forgetPassword(
        @Field("email") email: String
    ): ForgetPasswordResponseDto

    @FormUrlEncoded
    @POST("api/users/change")
    suspend fun changePassword(
        @Header("access_token") token: String,
        @Field("old_password") old_password: String,
        @Field("password") password: String,
        @Field("confirm_password") confirm_password: String,
    ): ResetPasswordResponseDto

    @FormUrlEncoded
    @POST("api/users/update")
    suspend fun updateUserProfile(
        @Header("access_token") token: String,
        @Field("first_name") firstName: String,
        @Field("last_name") lastName: String,
        @Field("email") email: String,
        @Field("dob") dob: String,
        @Field("phone_no") phoneNo: String,
        @Field("profile_pic") profilePic: String
    ): UpdateProfileResponseDto
}