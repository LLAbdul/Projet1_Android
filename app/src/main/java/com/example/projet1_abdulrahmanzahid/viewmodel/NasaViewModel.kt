package com.example.projet1_abdulrahmanzahid.viewmodel

import androidx.lifecycle.ViewModel
import com.example.projet1_abdulrahmanzahid.data.NasaDataAPI
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class NasaViewModel : ViewModel() {

    // UI State — affichage de photo + description
    private val _uiState = MutableStateFlow<NasaDataAPI?>(null)
    val uiState: StateFlow<NasaDataAPI?> = _uiState.asStateFlow()

    // Date entrée par l’utilisateur
    private var _date = MutableStateFlow("")
    val date: StateFlow<String> = _date.asStateFlow()

    fun updateDate(newDate: String) {
        _date.value = newDate
    }
    //Test
    fun chargerTestPhoto() {
        _uiState.value = NasaDataAPI(
            date = "2025-03-28",
            explanation = "Voici un exemple d'explication de la photo du jour.",
            hdurl = "https://apod.nasa.gov/apod/image/2503/AS17-137-20979_1024.jpg"
        )
    }
}
