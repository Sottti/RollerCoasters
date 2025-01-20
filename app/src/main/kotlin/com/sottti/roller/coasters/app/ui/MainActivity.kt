package com.sottti.roller.coasters.app.ui

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.sottti.roller.coasters.presentation.design.system.themes.RollerCoastersTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
public class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        setContent {
            RollerCoastersTheme {
                MainActivityContent()
            }
        }
    }
}