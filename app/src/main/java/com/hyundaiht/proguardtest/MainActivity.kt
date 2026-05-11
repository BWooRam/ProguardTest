package com.hyundaiht.proguardtest

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.google.gson.Gson
import com.hyundaiht.proguardtest.ui.data.User
import com.hyundaiht.proguardtest.ui.theme.ProguardTestTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ProguardTestTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    val json = "{\"name\":\"Gemini\", \"age\":20}"
                    val user = Gson().fromJson(json, User::class.java)
                    Greeting(
                        dto = user,
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(dto: User, modifier: Modifier = Modifier) {
    Text(
        text = "Hello ${dto.name}!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ProguardTestTheme {
        Greeting(User("Android"))
    }
}