package com.example.projet1_abdulrahmanzahid.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.projet1_abdulrahmanzahid.data.NasaDataAPI
import com.example.projet1_abdulrahmanzahid.data.NasaRepo
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class NasaViewModel(private val repo: NasaRepo) : ViewModel() {

    private val _uiState = MutableStateFlow<NasaDataAPI?>(null)
    val uiState: StateFlow<NasaDataAPI?> = _uiState.asStateFlow()

    private val _date = MutableStateFlow("")
    val date: StateFlow<String> = _date.asStateFlow()

    fun updateDate(newDate: String) {
        _date.value = newDate
    }

    fun chargerPhoto(apiKey: String) {
        viewModelScope.launch {
            val resultat = repo.getPhoto(apiKey, _date.value)
            resultat.onSuccess {
                _uiState.value = it
            }.onFailure {
                println("Erreur API : ${it.message}")
            }
        }
    }
    //Test
    /*fun chargerTestPhoto() {
        _uiState.value = NasaDataAPI(
            date = "2025-03-28",
            explanation = "Voici un exemple d'explication de la photo du jour.",
            hdurl = "https://apod.nasa.gov/apod/image/2503/AS17-137-20979_1024.jpg"
        )
    }*/
}
