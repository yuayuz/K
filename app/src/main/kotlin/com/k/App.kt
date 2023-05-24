package com.k

import androidx.compose.runtime.*
import com.k.ui.screens.login.loginScreen
import com.k.ui.screens.main.MainFrame

@Composable
fun App() {

    var isLoggedIn by remember { mutableStateOf(false) }

    if (isLoggedIn) {
        MainFrame(onLogout = { isLoggedIn = false })
    } else {
        loginScreen(
            onLoginSuccess = { isLoggedIn = true },
//            navToMain = { /*TODO*/ },
//            navToLogin = { /*TODO*/ },
//            showNoRegister = { /*TODO*/ },
//            showMistake = { /*TODO*/ }
        )
    }
}