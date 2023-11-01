package com.nyangzzi.responsive_layout_grid_compose.core

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.neverEqualPolicy
import androidx.compose.runtime.remember
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp


data class GridConfiguration(
    val layoutWidth: Dp,
    val columnWidth: Dp,
    val horizontalMargin: Dp,
    val gutterWidth: Dp,
    val totalColumns: Int,
)

@Composable
fun rememberGridConfiguration(
    layoutWidth: Dp,
   // columnWidth: Dp,
    horizontalMargin: Dp,
    gutterWidth: Dp,
    totalColumns: Int,
) = remember {
    gridConfiguration(
        layoutWidth,
        horizontalMargin,
        gutterWidth,
    //    columnWidth,
        totalColumns
    )
}

private fun gridConfiguration(
    layoutWidth: Dp,
    horizontalMargin: Dp,
    gutterWidth: Dp,
  //  columnWidth: Dp,
    totalColumns: Int,
): GridConfiguration {

    val columnLength =
        (layoutWidth - (horizontalMargin * 2) - gutterWidth * (totalColumns - 1)) / totalColumns

    return GridConfiguration(
        layoutWidth = layoutWidth,
        columnWidth = columnLength,
        horizontalMargin = horizontalMargin,
        gutterWidth = gutterWidth,
        totalColumns = totalColumns
    )
}

val LocalGridConfiguration = compositionLocalOf<GridConfiguration>(
    neverEqualPolicy()
) { error("Local Grid Configuration not present") }

@Immutable
object Configuration {

    @Stable
    interface Horizontal {
        val verticalMargin : Dp get() = 0.dp
        val horizontalMargin : Dp get() = 0.dp
        val gutterSize : Dp get() = 0.dp
    }

    @Stable
    interface Vertical {
        val verticalMargin : Dp get() = 0.dp
        val horizontalMargin : Dp get() = 0.dp
        val gutterSize : Dp get() = 0.dp
    }

    @Immutable
    internal data class ResponsiveConfig(
        val horizontal: Dp? = null,
        val vertical: Dp? = null,
        val gutter: Dp? = null,
        val totalCounts: Int? = null,
    ) : HorizontalOrVertical {
        override val gutterSize = gutter ?: 0.dp
        override val horizontalMargin = horizontal ?: 0.dp
        override val verticalMargin =vertical ?: 0.dp
    }

    @Stable
    fun init() : HorizontalOrVertical = ResponsiveConfig()

    @Stable
    fun set(gutter: Dp = 0.dp, vertical: Dp= 0.dp, horizontal: Dp= 0.dp) : HorizontalOrVertical = ResponsiveConfig(
        horizontal = horizontal,
        vertical = vertical,
        gutter = gutter
    )

    @Stable
    fun gutter(size: Dp= 0.dp) : HorizontalOrVertical = ResponsiveConfig(
        gutter = size
    )

    @Stable
    fun padding(vertical: Dp= 0.dp, horizontal: Dp= 0.dp) : HorizontalOrVertical = ResponsiveConfig(
        horizontal = horizontal,
        vertical = vertical
    )

    @Stable
    interface HorizontalOrVertical : Horizontal, Vertical {
        override val verticalMargin : Dp get() = 0.dp
        override val horizontalMargin : Dp get() = 0.dp
        override val gutterSize: Dp get() = 0.dp
    }

}