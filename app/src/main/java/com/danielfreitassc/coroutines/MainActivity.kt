package com.danielfreitassc.coroutines

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SemaforoApp()
        }
    }
}

@Composable
fun SemaforoApp() {
    var corAcesa by remember { mutableStateOf("red") }
    var isRunning by remember { mutableStateOf(false) }

    LaunchedEffect(isRunning) {
        while (isRunning) {
            corAcesa = "red"
            delay(3000L)
            corAcesa = "yellow"
            delay(1000L)
            corAcesa = "green"
            delay(2000L)
        }
    }

    LaunchedEffect(!isRunning) {
        while (!isRunning) {
            corAcesa = "yellow"
            delay(500L)
            corAcesa = ""
            delay(500L)
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        // Imagem de fundo
        Image(
            painter = painterResource(id = R.drawable.background),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(32.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TrafficLight(corAcesa)
            Spacer(modifier = Modifier.height(32.dp))

            Button(onClick = { isRunning = !isRunning }) {
                Text(if (isRunning) "Parar" else "Iniciar")
            }
        }
    }
}

@Composable
fun TrafficLight(corAcesa: String) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .background(Color.Gray) // leve escurecimento pra destacar o semáforo
            .padding(16.dp)
    ) {
        LightBox(isOn = corAcesa == "red", color = Color.Red)
        Spacer(modifier = Modifier.height(16.dp))
        LightBox(isOn = corAcesa == "yellow", color = Color.Yellow)
        Spacer(modifier = Modifier.height(16.dp))
        LightBox(isOn = corAcesa == "green", color = Color.Green)
    }
}

@Composable
fun LightBox(isOn: Boolean, color: Color) {
    val displayColor = if (isOn) color else Color.Black
    Box(
        modifier = Modifier
            .size(100.dp)
            .clip(CircleShape)
            .background(displayColor)
    )
}
