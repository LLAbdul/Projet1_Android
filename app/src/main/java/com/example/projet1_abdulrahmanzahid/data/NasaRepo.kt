package com.example.projet1_abdulrahmanzahid.data

class NasaRepo(private val apiService: NasaGetAPIService) {
    suspend fun getPhoto(apiKey: String, date: String): Result<NasaDataAPI> {
        return try {
            val response = apiService.getApi(apiKey, date)
            if (response.isSuccessful) {
                val body = response.body()
                if (body != null) {
                    Result.success(body)
                } else {
                    Result.failure(Exception("Réponse vide"))
                }
            } else {
                Result.failure(Exception("Erreur HTTP: ${response.code()}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}