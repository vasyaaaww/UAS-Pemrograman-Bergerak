package com.example.mywebserviceapp

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {
    @GET("api/data")
    fun getData(): Call<ApiResponse>

    @POST("api/data")
    fun sendData(@Body request: PostRequest): Call<ApiResponse>
}

data class ApiResponse(
    val message: String,
    val data: List<String>? = null
)

data class PostRequest(
    val name: String
)
