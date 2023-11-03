package com.nyangzzi.responsive_layout_grid_compose.core.row

import androidx.annotation.IntRange
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.nyangzzi.responsive_layout_grid_compose.core.ResponsiveConfig
import com.nyangzzi.responsive_layout_grid_compose.core.LocalRowConfiguration
import com.nyangzzi.responsive_layout_grid_compose.core.rememberRowConfiguration

const val RESPONSIVE_AUTO = -1

@Composable
inline fun ResponsiveRow(
    modifier: Modifier = Modifier,

    config: ResponsiveConfig.Horizontal = ResponsiveConfig.init(),
    layoutWidth: Dp = LocalConfiguration.current.screenWidthDp.dp,
    @IntRange(from = -1) totalColumns: Int = RESPONSIVE_AUTO,

    crossinline content: @Composable ResponsiveRowScope.() -> Unit) {

    val colum = (layoutWidth - (config.horizontalMargin * 2) - config.gutterSize * (totalColumns - 1)) / totalColumns

    val configuration = rememberRowConfiguration(

        if(totalColumns == RESPONSIVE_AUTO){
            ResponsiveBreakPoint.getResponsiveDimensions(layoutWidth)
        } else {
            ResponsiveConfig.Row(
                layoutWidth = layoutWidth,
                marginWidth = config.horizontalMargin,
                gutterWidth = config.gutterSize,
                totalColumns = totalColumns,
                columnWidth = colum
            )
        }
    )

    Row(
        modifier = Modifier
            .then(modifier)
            .padding(horizontal = configuration.marginWidth, vertical = config.verticalMargin)
            .wrapContentSize(),
            horizontalArrangement = Arrangement.spacedBy(config.gutterSize),
        ) {
        CompositionLocalProvider(LocalRowConfiguration provides configuration) {
            ResponsiveRowInstance.content()
        }
    }

}