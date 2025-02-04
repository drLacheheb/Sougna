package com.example.sougna

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.sougna.ui.theme.SougnaTheme.FirstUI
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
                //WelcomeScreen()
                //ProfileScreen()
                //TodoTaskScreen(); //The Path for code CTRL + LeftMouse for see the code
                  FirstUI()    //The Path for code CTRL + LeftMouse for see the code
        }
    }
}

