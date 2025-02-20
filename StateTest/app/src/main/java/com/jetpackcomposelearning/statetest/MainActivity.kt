package com.jetpackcomposelearning.statetest

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.jetpackcomposelearning.statetest.ui.theme.StateTestTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            StateTestTheme {

                Scaffold(
                    modifier = Modifier.fillMaxSize()
                ) { innerPadding ->
                    Counter(innerPadding)
                }
            }
        }
    }
}


@Composable
fun Counter(modifier: Modifier = Modifier) {

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {


    }

}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    StateTestTheme {
        Counter()
    }
}