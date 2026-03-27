package com.minroud.mortyverse

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.minroud.mortyverse.navigation.Navigation
import com.minroud.mortyverse.ui.MortyverseRoot

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MortyverseRoot { Navigation() }
        }
    }
}
