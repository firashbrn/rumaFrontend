package com.example.rumafrontend.network

import com.example.rumafrontend.data.model.loginRequest
import com.example.rumafrontend.data.model.loginRespons
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.Callback


interface ApiService {

    @POST("auth/login")
    suspend fun login(@Body loginRequest: loginRequest):loginRespons
}

