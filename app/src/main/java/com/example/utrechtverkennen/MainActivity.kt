package com.example.utrechtverkennen

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpSize
import com.example.utrechtverkennen.ui.MyCityApp
import com.example.utrechtverkennen.ui.theme.UtrechtverkennenTheme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            UtrechtverkennenTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    val windowSize = calculateWindowSizeClass(this)
                    MyCityApp(
                        windowWidthSize = windowSize.widthSizeClass,
                        onBackPressed = { finish() },
                        modifier = Modifier.padding(innerPadding),
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
@Preview(device = "id:pixel_tablet", showBackground = true)
@Preview(device = "id:pixel_fold", showBackground = true)
@Preview(widthDp = 700, showBackground = true)
@Preview(device = "id:pixel_5", showBackground = true)
@Composable
fun MyCityAppPreview() {
    UtrechtverkennenTheme {
        BoxWithConstraints {
            val windowSize = WindowSizeClass.calculateFromSize(DpSize(maxWidth, maxHeight))
            MyCityApp(
                windowWidthSize = windowSize.widthSizeClass,
                onBackPressed = {},
            )
        }
    }
}