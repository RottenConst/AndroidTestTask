package com.example.androidtesttask.screen

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.example.androidtesttask.Greeting
import com.example.androidtesttask.ui.theme.AndroidTestTaskTheme

@Composable
fun ListProductsScreen() {
    Scaffold (
        topBar = {
            TopBar(title = "Список товаров")
        }
    ) { innerPadding ->
        Greeting(
            name = "Android",
            modifier = Modifier.padding(innerPadding)
        )
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(title: String) {
    TopAppBar(
        title = {
            Text(
                text = title,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth())
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color(0xFFB1DCFC),
            titleContentColor = Color.Black
        )
    )
}


@Preview
@Composable
fun TopBarPreview() {
    AndroidTestTaskTheme {
        TopBar(title = "Список товаров")
    }
}