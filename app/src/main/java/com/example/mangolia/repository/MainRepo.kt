package com.example.mangolia.repository

import com.example.mangolia.network.ApiInterface

class MainRepo constructor(private val apiInterface: ApiInterface) {
    suspend fun getNewsFeed() = apiInterface.getResponse()

}