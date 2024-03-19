package com.fpu.maksimtestapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.fpu.maksimtestapp.presentation.main.MainScreen
import com.fpu.maksimtestapp.presentation.main.rememberMainScreenState
import com.fpu.maksimtestapp.ui.theme.MaksimTestAppTheme
import com.fpu.maksimtestapp.ui.theme.white
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaksimTestAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = white
                ) {
                    val mainScreenState = rememberMainScreenState()

                    MainScreen(
                        mainScreenState = mainScreenState
                    )
                }
            }
        }
    }
}
