package com.example.one_piece_tap_app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.one_piece_tap_app.ui.theme.OnePieceTapAppTheme
import com.google.mlkit.vision.text.Text

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            OnePieceTapAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                ) {
                    OnePieceTapApp()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OnePieceTapApp() {
    var currentStep by remember { mutableStateOf(1) }
    var tapCount by remember { mutableStateOf(0) }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "One Piece",
                        fontWeight = FontWeight.Bold
                    )
                },
                colors = TopAppBarDefaults.smallTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer,
                    navigationIconContentColor = MaterialTheme.colorScheme.onPrimaryContainer,
                    actionIconContentColor = MaterialTheme.colorScheme.onPrimaryContainer
                )
            )
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    color = MaterialTheme.colorScheme.background // Cambiado a colorScheme.background
                )
                .padding(innerPadding) // Utilizando innerPadding para el padding
        ) {
            Image(
                painter = painterResource(id = R.drawable.background),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxSize()
            )
            Column {
                when (currentStep) {
                    1 -> {
                        OnePieceImageStep(
                            drawableResourceId = R.drawable.luffy,
                            onImageClick = {
                                currentStep = 2
                                tapCount = (2..4).random()
                            },buttonText = "Luffy ¿Dónde está Merry?"
                        )
                    }
                    2 -> {
                        OnePieceImageStep(
                            drawableResourceId = R.drawable.nami,
                            onImageClick = {
                                tapCount--
                                if (tapCount == 0) {
                                    currentStep = 3
                                }
                            },buttonText = "Nami ¿Y mis mandarinas?"
                        )
                    }
                    3 -> {
                        OnePieceImageStep(
                            drawableResourceId = R.drawable.sanji,
                            onImageClick = {
                                currentStep = 4
                            },
                            buttonText = "Sanji Voy a cocinar"
                        )
                    }
                    4 -> {
                        OnePieceImageStep(
                            drawableResourceId = R.drawable.zorro,
                            onImageClick = {
                                currentStep = 1
                            },
                            buttonText = "Zorro ¿Y mis espadas?"
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun OnePieceImageStep(
    drawableResourceId: Int,
    onImageClick: () -> Unit,
    buttonText: String,
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = modifier.fillMaxSize()
    ) {
        Button(
            onClick = onImageClick,
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Image(
                    painter = painterResource(drawableResourceId),
                    contentDescription = "Character Image",
                    modifier = Modifier
                        .size(100.dp)
                )
                Text(
                    text = buttonText,
                    color = White,
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(start = 8.dp, top = 4.dp, end = 8.dp, bottom = 4.dp)
                )
            }
        }
    }
}

@Preview
@Composable
fun OnePiecePreview() {
    OnePieceTapAppTheme() {
        OnePieceTapApp()
    }
}

