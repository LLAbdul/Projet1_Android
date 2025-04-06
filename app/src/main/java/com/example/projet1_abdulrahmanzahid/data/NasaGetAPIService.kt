package com.example.projet1_abdulrahmanzahid.data

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NasaGetAPIService {
    @GET("planetary/apod")
    suspend fun getApi(
        @Query("api_key") apiKey: String,
        @Query("date") date: String
    ): Response<NasaDataAPI>
}