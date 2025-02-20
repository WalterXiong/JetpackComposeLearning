package com.JetpackComposeLearning.ModifierTest

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.JetpackComposeLearning.ModifierTest.ui.theme.ModifierTestTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        setContent {
            ModifierTestTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->

                    IconImage()
                }
            }
        }
    }
}


@Composable
fun IconImage() {
    
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ModifierTestTheme {
        IconImage()
    }
}