package com.sottti.roller.coasters.presentation.home.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.sottti.roller.coasters.presentation.design.system.themes.RollerCoastersTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
internal class HomeActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        setContent { RollerCoastersTheme { HomeUi() } }
    }
}

public fun startHomeActivity(context: Context) {
    context.startActivity(Intent(context, HomeActivity::class.java))
}
