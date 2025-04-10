package com.example.projet1_abdulrahmanzahid

import com.example.projet1_abdulrahmanzahid.data.NasaGetAPIService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

fun createRetrofitService(): NasaGetAPIService {
    return Retrofit.Builder()
        .baseUrl("https://api.nasa.gov/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(NasaGetAPIService::class.java)
}
