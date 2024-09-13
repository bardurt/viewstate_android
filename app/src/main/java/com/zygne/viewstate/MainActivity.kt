package com.zygne.viewstate

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import com.zygne.viewstate.ui.theme.ViewStateTheme

import com.zygne.viewstate.ui.views.ViewStateApp

class MainActivity : ComponentActivity() {

    private val viewModel: MainViewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ViewStateTheme {
                ViewStateApp(
                    mainViewModel = viewModel
                )
            }
        }
    }
}