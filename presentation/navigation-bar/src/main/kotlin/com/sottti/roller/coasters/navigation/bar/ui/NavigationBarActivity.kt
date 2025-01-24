package com.sottti.roller.coasters.navigation.bar.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.sottti.roller.coasters.presentation.design.system.themes.RollerCoastersTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
internal class NavigationBarActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)

        setContent {
            RollerCoastersTheme {
                NavigationBarContent()
            }
        }
    }
}

public fun startNavigationBarActivity(context: Context) {
    context.startActivity(Intent(context, NavigationBarActivity::class.java))
}