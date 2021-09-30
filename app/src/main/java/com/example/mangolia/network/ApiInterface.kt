package com.example.mangolia.network


import com.example.mangolia.models.Root
import retrofit2.Response
import retrofit2.http.*

interface ApiInterface {
    @GET("page/home?platform=mobile")
    suspend fun getResponse() : Response<Root>

}