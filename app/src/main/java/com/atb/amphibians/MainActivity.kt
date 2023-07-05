package com.atb.amphibians

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.atb.amphibians.ui.AmphibiansApplication
import com.atb.amphibians.ui.screen.AmphibiansViewModel
import com.atb.amphibians.ui.theme.AmphibiensTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AmphibiensTheme {
                val viewModel: AmphibiansViewModel = viewModel(factory = AmphibiansViewModel.Factory)
                AmphibiansApplication(viewModel = viewModel)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    AmphibiensTheme {

    }
}