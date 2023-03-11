package com.minroud.mortyverse

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.minroud.mortyverse.ui.theme.MortyverseTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MortyverseTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Greeting("Morty")
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hi *burp* $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MortyverseTheme {
        Greeting("Morty")
    }
}
