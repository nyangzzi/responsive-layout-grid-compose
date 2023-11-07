package com.nyangzzi.responsive_layout_grid.app_demo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.nyangzzi.responsive_layout_grid.app_demo.ui.theme.ResponsiveLayoutGridComposeTheme
import com.nyangzzi.responsive_layout_grid_compose.core.ResponsiveConfig
import com.nyangzzi.responsive_layout_grid_compose.core.row.ResponsiveRow
import com.nyangzzi.responsive_layout_grid_compose.core.row.ResponsiveBreakPoint
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
                    ResponsiveRowSample()
                }
            }
        }
    }
}

@Composable
fun ResponsiveRowSample() {
    Column(modifier = Modifier
        .fillMaxSize()
        .verticalScroll(rememberScrollState())) {

        val layoutWidth = LocalConfiguration.current.screenWidthDp.dp
        val screenType = ResponsiveBreakPoint.getScreenType(layoutWidth)

        for (index in 1 until screenType.noOfColumns) {
            ResponsiveRow(config = ResponsiveConfig.set(gutter = 20.dp, vertical = 20.dp)) {
                Text(modifier = Modifier
                    .horizontalWeight(index)
                    .background(Color.LightGray), text = "$index")
                Text(modifier = Modifier
                    .horizontalWeight(screenType.noOfColumns - index)
                    .background(Color.LightGray), text = "${screenType.noOfColumns-index}")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ResponsiveLayoutGridComposeTheme {
        ResponsiveRowSample()
    }
}