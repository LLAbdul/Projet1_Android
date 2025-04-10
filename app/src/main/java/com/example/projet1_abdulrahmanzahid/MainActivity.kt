package com.example.projet1_abdulrahmanzahid

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.projet1_abdulrahmanzahid.theme.Projet1_AbdulRahmanZahidTheme
import com.example.projet1_abdulrahmanzahid.ui.NasaUI

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Projet1_AbdulRahmanZahidTheme {
                NasaUI()
            }
        }
    }
}
