package com.nyangzzi.responsive_layout_grid_compose.core.column

import androidx.annotation.IntRange
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.nyangzzi.responsive_layout_grid_compose.core.ResponsiveConfig
import com.nyangzzi.responsive_layout_grid_compose.core.LocalColumnConfiguration
import com.nyangzzi.responsive_layout_grid_compose.core.Util
import com.nyangzzi.responsive_layout_grid_compose.core.rememberColumnConfiguration

/**
 *  Responsive Column Layout Gird
 *
 * Follow [Material Guideline](https://m2.material.io/design/layout/responsive-layout-grid.html)
 *
 * @param modifier The modifier to be applied to the Row.
 * @param config Set gutter, vertical padding, horizontal padding. Default value is 0.dp
 * @param layoutHeight The height of the column. Default value is screen height.
 * @param totalRows Indicates the sum of weights. This is a required value.
 * @see com.nyangzzi.responsive_layout_grid_compose.core.row.ResponsiveRow
 * @sample com.nyangzzi.responsive_layout_grid.app_demo.ResponsiveColumnSample
 */
@Composable
inline fun ResponsiveColumn(
    modifier: Modifier = Modifier,

    config: ResponsiveConfig.Vertical = ResponsiveConfig.init(),
    layoutHeight: Dp = LocalConfiguration.current.screenHeightDp.dp,
    @IntRange(from = -1) totalRows: Int,

    crossinline content: @Composable ResponsiveColumnScope.() -> Unit) {
    
    val configuration = rememberColumnConfiguration(
        ResponsiveConfig.Column(
                layoutHeight = layoutHeight,
                marginHeight = config.verticalMargin,
                gutterHeight = config.gutterSize,
                totalRows = totalRows,
                rowHeight = Util.getRowHeight(layoutHeight=layoutHeight, verticalMargin = config.verticalMargin, gutterHeight = config.gutterSize, totalRows = totalRows)
        )
    )

    Column(
        modifier = Modifier
            .then(modifier)
            .padding(horizontal = config.horizontalMargin, vertical = configuration.marginHeight)
            .wrapContentSize(),
            verticalArrangement = Arrangement.spacedBy(config.gutterSize),
        ) {
        CompositionLocalProvider(LocalColumnConfiguration provides configuration) {
            ResponsiveColumnInstance.content()
        }
    }

}