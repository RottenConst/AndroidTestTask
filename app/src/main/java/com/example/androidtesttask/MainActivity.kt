package com.example.androidtesttask

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.androidtesttask.screen.ListProductsScreen
import com.example.androidtesttask.ui.theme.AndroidTestTaskTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AndroidTestTaskTheme {
                ListProductsScreen()
            }
        }
    }
}