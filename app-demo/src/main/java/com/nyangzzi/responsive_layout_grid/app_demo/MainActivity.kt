package com.nyangzzi.responsive_layout_grid.app_demo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.nyangzzi.responsive_layout_grid.app_demo.ui.theme.ResponsiveLayoutGridComposeTheme
import com.nyangzzi.responsive_layout_grid_compose.core.Configuration
import com.nyangzzi.responsive_layout_grid_compose.core.row.ResponsiveRow
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ResponsiveLayoutGridComposeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Greeting()
                }
            }
        }
    }
}

@Composable
fun Greeting() {
    Box(modifier= Modifier.fillMaxSize()){
        ResponsiveRow(modifier= Modifier.background(Color.Gray),
            config= Configuration.set(20.dp,40.dp,60.dp)) {
            Text(modifier = Modifier.horizontalWeight(3).background(Color.Red),text = "1")
            Text(modifier = Modifier.horizontalWeight(3).background(Color.Green),text = "2")
            Text(modifier = Modifier.horizontalWeight(3).background(Color.Yellow),text = "3")
        }
    }

}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ResponsiveLayoutGridComposeTheme {
        Greeting()
    }
}