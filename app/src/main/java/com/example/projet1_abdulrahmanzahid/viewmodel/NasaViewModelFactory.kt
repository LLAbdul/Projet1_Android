package com.example.projet1_abdulrahmanzahid.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.projet1_abdulrahmanzahid.data.NasaRepo

class NasaViewModelFactory(private val repo: NasaRepo) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(NasaRepo::class.java)
            .newInstance(repo)
    }
}
