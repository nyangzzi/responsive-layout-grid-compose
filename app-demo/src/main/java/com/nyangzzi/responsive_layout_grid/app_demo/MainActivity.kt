package com.nyangzzi.responsive_layout_grid.app_demo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.nyangzzi.responsive_layout_grid.app_demo.ui.theme.ResponsiveLayoutGridComposeTheme
import com.nyangzzi.responsive_layout_grid_compose.core.ResponsiveConfig
import com.nyangzzi.responsive_layout_grid_compose.core.column.ResponsiveColumn
import com.nyangzzi.responsive_layout_grid_compose.core.row.ResponsiveRow
import com.nyangzzi.responsive_layout_grid_compose.core.row.ResponsiveRowBreakPoint

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
                    //ResponsiveColumnSample()
                    ResponsiveRowSample()
                }
            }
        }
    }
}

/**
 * Responsive Column Sample
 */
@Composable
fun ResponsiveColumnSample(){
    Row(modifier = Modifier
        .fillMaxSize()
        .horizontalScroll(rememberScrollState()),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {

        val totalRows = remember {
            mutableStateOf(5)
        }

        for (index in 1 until totalRows.value) {
            ResponsiveColumn(config = ResponsiveConfig.set(gutter = 16.dp, vertical = 8.dp),
                totalRows = totalRows.value) {
                Text(modifier = Modifier
                    .verticalWeight(index)
                    .background(Color.LightGray)
                    .padding(4.dp), text = "$index")
                Text(modifier = Modifier
                    .verticalWeight(totalRows.value - index)
                    .fillMaxSize()
                    .background(Color.LightGray)
                    .padding(4.dp), text = "${totalRows.value-index}")
            }
        }
    }
}

/**
 *  Responsive Row Sample
 */
@Composable
fun ResponsiveRowSample() {
    Column(modifier = Modifier
        .fillMaxSize()
        .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {

        val layoutWidth = LocalConfiguration.current.screenWidthDp.dp
        val screenType = ResponsiveRowBreakPoint.getScreenType(layoutWidth)

        for (index in 1 until screenType.columnCounts) {
            ResponsiveRow(
                config = ResponsiveConfig.set(gutter = 16.dp, horizontal = 8.dp),
                columnCounts = screenType.columnCounts) {
                Text(modifier = Modifier
                    .horizontalWeight(index)
                    .background(Color.LightGray)
                    .padding(4.dp), text = "$index")
                Text(modifier = Modifier
                    .horizontalWeight(screenType.columnCounts - index)
                    .fillMaxSize()
                    .background(Color.LightGray)
                    .padding(4.dp), text = "${screenType.columnCounts-index}")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun DefaultColumnPreview() {
    ResponsiveLayoutGridComposeTheme {
        ResponsiveColumnSample()
    }
}

@Preview(showBackground = true)
@Composable
private fun DefaultRowPreview() {
    ResponsiveLayoutGridComposeTheme {
        ResponsiveRowSample()
    }
}