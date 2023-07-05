package com.atb.amphibians.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.atb.amphibians.data.Amphibian
import com.atb.amphibians.ui.theme.AmphibiensTheme
import com.atb.amphibiens.R

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    uiState: AmphibiansUiState
) {
    when(uiState) {
        is AmphibiansUiState.Error -> ErrorScreen()
        is AmphibiansUiState.Loading -> LoadingScreen()
        is AmphibiansUiState.Success -> SuccessScreen(amphibians = uiState.amphibians)
    }
}

@Composable
fun SuccessScreen(
    amphibians: List<Amphibian>,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier
    ) {
        items(items = amphibians, key = {amphibian -> amphibian.name}) {amphibian ->
            AmphibianItem(amphibian)
        }
    }
}

@Composable
fun LoadingScreen(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.loading_img),
            contentDescription = "loading icon",
            modifier = Modifier.size(200.dp)
        )
    }
}

@Composable
fun ErrorScreen(modifier: Modifier = Modifier) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier.fillMaxSize()
    ) {
        Text(text = "Failed to load")
    }
}

@Composable
fun AmphibianItem(
    amphibian: Amphibian,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.padding(8.dp),
        elevation = CardDefaults.cardElevation(8.dp),
        shape = ShapeDefaults.Medium
    ) {
        AmphibianTitle(amphibian.name, amphibian.type)
        Spacer(modifier = Modifier.height(8.dp))
        AmphibianDescription(amphibian.description)
        Spacer(modifier = Modifier.height(8.dp))
        AmphibianImg(amphibian)
    }
}

@Composable
fun AmphibianTitle(
    name: String,
    type: String,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.fillMaxWidth().padding(horizontal = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Text(
            text = "$name ($type)",
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp,
            modifier = Modifier.padding(top = 8.dp)
        )
    }
}

@Composable
fun AmphibianDescription(
    description: String,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.fillMaxWidth()
    ) {
        Text(
            text = description,
            fontSize = 14.sp,
            textAlign = TextAlign.Justify,
            modifier = Modifier.padding(horizontal = 12.dp)
        )
    }
}

@Composable
fun AmphibianImg(
    amphibian: Amphibian,
    modifier: Modifier = Modifier
) {
    AsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
            .data(amphibian.imgSrc)
            .crossfade(true)
            .build(),
        contentDescription = "amphibian",
        error = painterResource(id = R.drawable.ic_broken_image),
        placeholder = painterResource(id = R.drawable.loading_img),
        contentScale = ContentScale.FillBounds,
        modifier = modifier.fillMaxWidth()
    )
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun Pre() {
    AmphibiensTheme {
        AmphibianTitle(name = "Crapeau de nuit et quell dconde", type = "Topoc")
    }
}