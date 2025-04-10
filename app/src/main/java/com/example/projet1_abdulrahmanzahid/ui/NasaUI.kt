package com.example.projet1_abdulrahmanzahid.ui

import android.app.DatePickerDialog
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberAsyncImagePainter
import com.example.projet1_abdulrahmanzahid.createRetrofitService
import com.example.projet1_abdulrahmanzahid.data.NasaRepo
import com.example.projet1_abdulrahmanzahid.viewmodel.NasaViewModel
import com.example.projet1_abdulrahmanzahid.viewmodel.NasaViewModelFactory
import java.util.*
import com.example.projet1_abdulrahmanzahid.Secrets


@Composable
fun NasaUI() {

    val context = LocalContext.current
    val retrofitService = remember { createRetrofitService() }
    val repo = remember { NasaRepo(retrofitService) }
    val factory = remember { NasaViewModelFactory(repo) }
    val viewModel: NasaViewModel = viewModel(factory = factory)

    val date by viewModel.date.collectAsState()
    val uiState by viewModel.uiState.collectAsState()

    val calendrier = Calendar.getInstance()

    val datePickerDialog = remember {
        DatePickerDialog(
            context,
            { _, year, month, dayOfMonth ->
                val formattedDate = "%04d-%02d-%02d".format(year, month + 1, dayOfMonth)
                viewModel.updateDate(formattedDate)
            },
            calendrier.get(Calendar.YEAR),
            calendrier.get(Calendar.MONTH),
            calendrier.get(Calendar.DAY_OF_MONTH)
        )
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(32.dp))
        Text(
            "PHOTO DU JOUR",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(8.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))
        Text(
            "Sélectionnez une date pour afficher la photo du jour de la NASA.",
            style = MaterialTheme.typography.bodyLarge,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(8.dp)
        )

        OutlinedTextField(
            value = date,
            onValueChange = { viewModel.updateDate(it) },
            label = { Text("Date (YYYY-MM-DD)") },
            modifier = Modifier.fillMaxWidth(),
            trailingIcon = {
                IconButton(onClick = { datePickerDialog.show() }) {
                    Icon(Icons.Default.DateRange, contentDescription = "Sélectionner une date")
                }
            },
            singleLine = true,
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Bouton réel (API)
        Button(onClick = {
            viewModel.chargerPhoto(Secrets.NASA_API_KEY)
        }) {
            Text("Afficher la photo")
        }

        Spacer(modifier = Modifier.height(8.dp))

        /*// TEST
        OutlinedButton(onClick = {
            viewModel.chargerTestPhoto()
        }) {
            Text("Test photo (locale)")
        }

        Spacer(modifier = Modifier.height(24.dp))
*/
        // Affichage
        uiState?.let { nasaData ->
            Spacer(modifier = Modifier.height(8.dp))
            Image(
                painter = rememberAsyncImagePainter(nasaData.hdurl),
                contentDescription = "Image NASA",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(250.dp),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = nasaData.explanation)
        }
    }
}
