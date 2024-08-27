package com.example.androidtesttask.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.androidtesttask.ui.theme.AndroidTestTaskTheme

@Composable
fun ListProductsScreen() {
    Scaffold (
        topBar = {
            TopBar(title = "Список товаров")
        }
    ) { innerPadding ->
        CardProduct(modifier = Modifier.padding(innerPadding))
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

/**
   Карточка товара
 */
@Composable
fun CardProduct(modifier: Modifier) {
    ElevatedCard(
        modifier = modifier
            .padding(8.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 4.dp
        ),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        )
    ) {
        Column(modifier = Modifier.padding(8.dp)) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(4.dp),
                horizontalArrangement = Arrangement.SpaceBetween)
            {
                //title
                Text(text = "Title", fontWeight = FontWeight.Bold, style = MaterialTheme.typography.titleLarge)
                //icon
                Row {
                    Icon(imageVector = Icons.Default.Create, contentDescription = "", tint = Color(0xFF6200EE))
                    Icon(imageVector = Icons.Default.Delete, contentDescription = "", tint = Color(0xFFCC4100))
                }
            }
            //tag
            FilterChip(selected = false, onClick = {}, label = { Text(text = "Tag") })
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                //amount
                Column {
                    Text("На складе", fontWeight = FontWeight.Bold, style = MaterialTheme.typography.bodyMedium)
                    Text(text = "10", style = MaterialTheme.typography.bodyMedium)
                }
                //date
                Column {
                    Text(text = "Дата добавления", fontWeight = FontWeight.Bold, style = MaterialTheme.typography.bodyMedium)
                    Text(text = "01.10.2021", style = MaterialTheme.typography.bodyMedium)
                }
            }
        }
    }
}


@Preview
@Composable
fun TopBarPreview() {
    AndroidTestTaskTheme {
        TopBar(title = "Список товаров")
    }
}

@Preview
@Composable
fun CardProductPreview() {
    AndroidTestTaskTheme {
        CardProduct(modifier = Modifier)
    }
}