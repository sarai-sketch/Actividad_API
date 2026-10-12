package com.cyberqhatu.attackontitan

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.cyberqhatu.attackontitan.ui.home.HomeScreen
import com.cyberqhatu.attackontitan.ui.theme.AttackOnTitanTheme
import com.cyberqhatu.attackontitan.viewmodel.MainViewModel

class MainActivity : ComponentActivity() {

    private val mainViewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AttackOnTitanTheme {
                HomeScreen(viewModel = mainViewModel)
            }
        }
    }
}
