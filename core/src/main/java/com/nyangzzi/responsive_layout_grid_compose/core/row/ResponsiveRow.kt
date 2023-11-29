package com.nyangzzi.responsive_layout_grid_compose.core.row

import androidx.annotation.IntRange
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.nyangzzi.responsive_layout_grid_compose.core.ResponsiveConfig
import com.nyangzzi.responsive_layout_grid_compose.core.LocalRowConfiguration
import com.nyangzzi.responsive_layout_grid_compose.core.Util
import com.nyangzzi.responsive_layout_grid_compose.core.rememberRowConfiguration

/**
 * Use When you want Responsive Break Point.
 * If [columnCounts] is -1, this means setting it automatically.
 */
const val RESPONSIVE_AUTO = -1

/**
 * Responsive Row Layout Gird
 *
 * Follow [Material Guideline](https://m2.material.io/design/layout/responsive-layout-grid.html)
 *
 * @author nyangzzi(eunkyung lee)
 *
 * @param modifier The modifier to be applied to the Row.
 * @param config Set gutter, vertical padding and horizontal padding.
 * @param layoutWidth The width of the layout. Default value is screen width.
 * @param columnCounts Indicates the sum of weights. If you do not enter this value, it is automatically assigned according to the guidelines.
 * @param horizontalAlignment The horizontal alignment of the layout's children.
 * @param verticalAlignment The vertical alignment of the layout's children.
 *
 * @see [ResponsiveColumn](com.nyangzzi.responsive_layout_grid_compose.core.column.ResponsiveColumn)
 * @sample com.nyangzzi.responsive_layout_grid.app_demo.ResponsiveRowSample
 */
@Composable
inline fun ResponsiveRow(
    modifier: Modifier = Modifier,
    config: ResponsiveConfig.Horizontal = ResponsiveConfig.init(),
    layoutWidth: Dp = LocalConfiguration.current.screenWidthDp.dp,
    @IntRange(from = -1) columnCounts: Int = RESPONSIVE_AUTO,
    horizontalAlignment: Alignment.Horizontal = Alignment.Start,
    verticalAlignment: Alignment.Vertical = Alignment.Top,
    crossinline content: @Composable ResponsiveRowScope.() -> Unit) {

    val configuration = rememberRowConfiguration(

        if(columnCounts == RESPONSIVE_AUTO){
            /**
             * Auto by Break Point.
             * You can set gutter size.
             */
            ResponsiveRowBreakPoint.getResponsiveDimensions(layoutWidth, config.gutterSize)
        } else {
            /**
             * Custom
             */
            ResponsiveConfig.Row(
                layoutWidth = layoutWidth,
                marginWidth = config.horizontalMargin,
                gutterWidth = config.gutterSize ?: 0.dp,
                columnCounts = columnCounts,
                columnWidth = Util.getFixedColumWidth(layoutWidth, config.horizontalMargin, config.gutterSize ?: 0.dp, columnCounts)
            )
        }
    )

    Row(
        modifier = Modifier
            .then(modifier)
            .padding(horizontal = configuration.marginWidth, vertical = config.verticalMargin)
            .wrapContentSize(),
            verticalAlignment = verticalAlignment,
            horizontalArrangement = Arrangement.spacedBy(config.gutterSize ?: 0.dp, horizontalAlignment),
        ) {
        CompositionLocalProvider(LocalRowConfiguration provides configuration) {
            ResponsiveRowInstance.content()
        }
    }

}