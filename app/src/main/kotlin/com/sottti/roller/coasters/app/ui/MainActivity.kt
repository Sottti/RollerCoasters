package com.sottti.roller.coasters.app.ui

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material3.MaterialTheme
import com.sotti.rollercoaster.presentation.design.system.themes.AppTheme
import com.sottti.roller.coasters.app.data.MainActivityViewModel

internal class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        setContent {
            val viewModel = MainActivityViewModel()
            AppTheme {
                MainActivityContent(viewModel)
            }
        }
    }
}