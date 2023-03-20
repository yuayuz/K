package com.example.k

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.k.ui.screens.login.loginScreen
import com.example.k.ui.screens.main.MainFrame

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainFrame()
        }
    }
}

/*
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
//    MainFrame()
    loginScreen ({},{},{},{})
}*/
