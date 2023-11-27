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
import com.nyangzzi.responsive_layout_grid_compose.core.Util
import com.nyangzzi.responsive_layout_grid_compose.core.rememberRowConfiguration

const val RESPONSIVE_AUTO = -1

/**
 *  Responsive Row Layout Gird
 *
 * Follow [Material Guideline](https://m2.material.io/design/layout/responsive-layout-grid.html)
 *
 * @param modifier The modifier to be applied to the Row.
 * @param config Set gutter, vertical padding, horizontal padding. Default value is 0.dp
 * @param layoutWidth The width of the row. Default value is screen width.
 * @param totalColumns Indicates the sum of weights. If you do not enter this value, it is automatically assigned according to the guidelines.
 * @see com.nyangzzi.responsive_layout_grid_compose.core.column.ResponsiveColumn
 * @sample com.nyangzzi.responsive_layout_grid.app_demo.ResponsiveRowSample
 */
@Composable
inline fun ResponsiveRow(
    modifier: Modifier = Modifier,
    config: ResponsiveConfig.Horizontal = ResponsiveConfig.init(),
    layoutWidth: Dp = LocalConfiguration.current.screenWidthDp.dp,
    @IntRange(from = -1) totalColumns: Int = RESPONSIVE_AUTO,
    crossinline content: @Composable ResponsiveRowScope.() -> Unit) {

    val configuration = rememberRowConfiguration(

        if(totalColumns == RESPONSIVE_AUTO){
            ResponsiveRowBreakPoint.getResponsiveDimensions(layoutWidth, config.gutterSize)
        } else {
            ResponsiveConfig.Row(
                layoutWidth = layoutWidth,
                marginWidth = config.horizontalMargin,
                gutterWidth = config.gutterSize ?: 0.dp,
                totalColumns = totalColumns,
                columnWidth = Util.getFixedColumWidth(layoutWidth, config.horizontalMargin, config.gutterSize ?: 0.dp, totalColumns)
            )
        }
    )

    Row(
        modifier = Modifier
            .then(modifier)
            .padding(horizontal = configuration.marginWidth, vertical = config.verticalMargin)
            .wrapContentSize(),
            horizontalArrangement = Arrangement.spacedBy(config.gutterSize ?: 0.dp),
        ) {
        CompositionLocalProvider(LocalRowConfiguration provides configuration) {
            ResponsiveRowInstance.content()
        }
    }

}