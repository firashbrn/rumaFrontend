package com.example.rumafrontend.network

import com.example.rumafrontend.data.model.loginRequest
import com.example.rumafrontend.data.model.loginRespons
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.Callback
import retrofit2.Response


interface ApiService {

    @POST("login")
    suspend fun login(@Body request: loginRequest): Response<loginRespons> // HARUS ada Response<T>

}

