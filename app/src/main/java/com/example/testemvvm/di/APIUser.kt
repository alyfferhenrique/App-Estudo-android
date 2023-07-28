package com.example.testemvvm.di

import com.example.testemvvm.data.models.Login
import com.example.testemvvm.data.models.RegisterModel
import com.example.testemvvm.data.models.ResponseBase
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface APIUser {

    @POST("api/LoginDocument")
    fun login(@Body request: Login.Request) : Call<ResponseBase<Login.UserResponse>>

    @GET("api/BuscarTodos")
    fun getAll() : Call<ResponseBase<ArrayList<Login.UserResponse>>>

    @POST("api/Cadastro")
    fun register(@Body request: RegisterModel.Request) : Call<ResponseBase<RegisterModel.Response>>

}