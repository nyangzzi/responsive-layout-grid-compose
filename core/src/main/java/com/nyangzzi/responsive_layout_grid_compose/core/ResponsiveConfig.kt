package com.nyangzzi.responsive_layout_grid_compose.core

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.neverEqualPolicy
import androidx.compose.runtime.remember
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

/**
 * TODO
 *
 * @param gridConfiguration
 */
@Composable
fun rememberRowConfiguration(
    gridConfiguration : ResponsiveConfig.Row
) = remember {
    gridConfiguration
}

/**
 * TODO
 *
 * @param gridConfiguration
 */
@Composable
fun rememberColumnConfiguration(
    gridConfiguration : ResponsiveConfig.Column
) = remember {
    gridConfiguration
}

/**
 *
 */
val LocalRowConfiguration = compositionLocalOf<ResponsiveConfig.Row>(
    neverEqualPolicy()
) { error("Local Grid Configuration not present") }

/**
 *
 */
val LocalColumnConfiguration = compositionLocalOf<ResponsiveConfig.Column>(
    neverEqualPolicy()
) { error("Local Grid Configuration not present") }

/**
 *
 */
@Immutable
object ResponsiveConfig {

    /**
     * TODO
     *
     * @property layoutWidth
     * @property marginWidth
     * @property columnWidth
     * @property gutterWidth
     * @property columnCounts
     */
    @Stable
    data class Row (
        val layoutWidth: Dp,
        val marginWidth: Dp,
        val columnWidth: Dp,
        val gutterWidth: Dp,
        val columnCounts: Int)

    /**
     * TODO
     *
     * @property layoutHeight
     * @property marginHeight
     * @property rowHeight
     * @property gutterHeight
     * @property rowCounts
     */
    @Stable
    data class Column (
        val layoutHeight: Dp,
        val marginHeight: Dp,
        val rowHeight: Dp,
        val gutterHeight: Dp,
        val rowCounts: Int)

    /**
     * TODO
     *
     */
    @Stable
    interface Horizontal {
        val verticalMargin : Dp get() = 0.dp
        val horizontalMargin : Dp get() = 0.dp
        val gutterSize : Dp? get() = null
    }

    /**
     * TODO
     *
     */
    @Stable
    interface Vertical {
        val verticalMargin : Dp get() = 0.dp
        val horizontalMargin : Dp get() = 0.dp
        val gutterSize : Dp get() = 0.dp
    }

    /**
     * TODO
     *
     * @property horizontal
     * @property vertical
     * @property gutter
     * @property totalCounts
     */
    @Immutable
    internal data class ResponsiveConfig(
        val horizontal: Dp? = null,
        val vertical: Dp? = null,
        val gutter: Dp? = null,
        val totalCounts: Int? = null,
    ) : HorizontalOrVertical {
        override val gutterSize = gutter ?: 0.dp
        override val horizontalMargin = horizontal ?: 0.dp
        override val verticalMargin = vertical ?: 0.dp
    }

    /**
     * TODO
     *
     * @return
     */
    @Stable
    fun init() : HorizontalOrVertical = ResponsiveConfig()

    /**
     * TODO
     *
     * @param gutter
     * @param vertical
     * @param horizontal
     * @return
     */
    @Stable
    fun set(gutter: Dp = 0.dp, vertical: Dp= 0.dp, horizontal: Dp= 0.dp) : HorizontalOrVertical = ResponsiveConfig(
        horizontal = horizontal,
        vertical = vertical,
        gutter = gutter
    )

    /**
     * TODO
     *
     * @param size
     * @return
     */
    @Stable
    fun gutter(size: Dp= 0.dp) : HorizontalOrVertical = ResponsiveConfig(
        gutter = size
    )

    /**
     * TODO
     *
     * @param vertical
     * @param horizontal
     * @return
     */
    @Stable
    fun padding(vertical: Dp= 0.dp, horizontal: Dp= 0.dp) : HorizontalOrVertical = ResponsiveConfig(
        horizontal = horizontal,
        vertical = vertical
    )

    /**
     * TODO
     *
     */
    @Stable
    interface HorizontalOrVertical : Horizontal, Vertical {
        override val verticalMargin : Dp get() = 0.dp
        override val horizontalMargin : Dp get() = 0.dp
        override val gutterSize: Dp get() = 0.dp
    }

}