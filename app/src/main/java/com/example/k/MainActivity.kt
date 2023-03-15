package com.example.k

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
import com.example.k.ui.screens.login.Loginscreen
import com.example.k.ui.screens.main.MainFrame
import com.example.k.ui.theme.KTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainFrame()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
//    MainFrame()
    Loginscreen({})
}