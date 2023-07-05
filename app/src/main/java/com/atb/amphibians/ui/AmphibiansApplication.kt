package com.atb.amphibians.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.atb.amphibians.ui.screen.AmphibiansViewModel
import com.atb.amphibians.ui.screen.HomeScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AmphibiansApplication(
    viewModel: AmphibiansViewModel,
    modifier: Modifier = Modifier
) {
    Scaffold(
        topBar = { TopAppBar (title = {Text(text = "Amphibians")}) },
        modifier = modifier.fillMaxSize()
    ) {
        Surface(
            modifier = Modifier.fillMaxSize().padding(it),
            color = MaterialTheme.colorScheme.background
        ) {
            HomeScreen(uiState = viewModel.uiState)
        }
    }
}