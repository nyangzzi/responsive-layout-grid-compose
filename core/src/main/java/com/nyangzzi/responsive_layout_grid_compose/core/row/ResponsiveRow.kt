package com.nyangzzi.responsive_layout_grid_compose.core.row

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.Stable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.nyangzzi.responsive_layout_grid_compose.core.Configuration
import com.nyangzzi.responsive_layout_grid_compose.core.LocalGridConfiguration
import com.nyangzzi.responsive_layout_grid_compose.core.rememberGridConfiguration

@Composable
inline fun ResponsiveRow(
    modifier: Modifier = Modifier,

    config: Configuration.Horizontal = Configuration.init(),
    layoutWidth: Dp = LocalConfiguration.current.screenWidthDp.dp,

    crossinline content: @Composable ResponsiveRowScope.() -> Unit) {

    val configuration = rememberGridConfiguration(
        layoutWidth = layoutWidth,
        horizontalMargin = config.horizontalMargin,
        gutterWidth = config.gutterSize,
        totalColumns = 9,
        //columnWidth = 0.dp
    )

    Row(
        modifier = Modifier
            .then(modifier)
            .padding(horizontal = config.horizontalMargin, vertical = config.verticalMargin)
            .wrapContentSize(),
            horizontalArrangement = Arrangement.spacedBy(config.gutterSize),
        ) {
        CompositionLocalProvider(LocalGridConfiguration provides configuration) {
            ResponsiveRowInstance.content()
        }
    }

}