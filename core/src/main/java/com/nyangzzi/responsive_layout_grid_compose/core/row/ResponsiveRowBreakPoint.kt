package com.nyangzzi.responsive_layout_grid_compose.core.row

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.nyangzzi.responsive_layout_grid_compose.core.ResponsiveConfig
import com.nyangzzi.responsive_layout_grid_compose.core.Util
import kotlin.math.roundToInt

/**
 * Responsive Row BreakPoint
 *
 * Follow [Material Guideline Breakpoints](https://m2.material.io/design/layout/responsive-layout-grid.html#breakpoints)
 *
 * @author nyangzzi(eunkyung lee)
 */
object ResponsiveRowBreakPoint {

    /**
     * Undefined Width.
     */
    val Scaling = (-1).dp

    /**
     * Default Screen Type by Material Guideline.
     *
     * @property columnCounts Default Total Counts of Column.
     * @property gutterDefault Default Gutter. Use When Unless User Custom Size.
     * @property marginWidth Default Margin.
     * @property bodyWidth Default Body Width.
     */
    sealed class ScreenType(val columnCounts: Int, val gutterDefault : Dp, val marginWidth: Dp, val bodyWidth: Dp) {
        object Phone : ScreenType(columnCounts = 4, gutterDefault = 16.dp, marginWidth = 16.dp, bodyWidth= Scaling)
        object TabletSmall : ScreenType(columnCounts = 8, gutterDefault = 24.dp, marginWidth = 32.dp, bodyWidth = Scaling)
        object TabletLarge : ScreenType(columnCounts = 12, gutterDefault = 24.dp, marginWidth = Scaling, bodyWidth = 840.dp)
        object Laptop : ScreenType(columnCounts = 12, gutterDefault = 32.dp, marginWidth = 200.dp, bodyWidth = Scaling)
        object Desktop : ScreenType(columnCounts = 12, gutterDefault = 32.dp, marginWidth = Scaling, bodyWidth = 1040.dp)
    }

    /**
     * Get Default Screen Type.
     *
     * @param layoutWidth The Width of layout.
     *
     * @return Screen Type
     */
    fun getScreenType(layoutWidth: Dp) = when (layoutWidth.value.roundToInt().dp) {
        in (0..599).map { it.dp } -> ScreenType.Phone
        in (600..904).map { it.dp } -> ScreenType.TabletSmall
        in (905..1239).map { it.dp } -> ScreenType.TabletLarge
        in (1240..1439).map { it.dp } -> ScreenType.Laptop
        else -> ScreenType.Desktop
    }

    /**
     * Responsive Config Row
     *
     * @param layoutWidth The Width of layout.
     * @param gutter The Gutter of User Custom.
     *
     * @return ResponsiveConfig with Layout, Margin, Column, Gutter Width and Total Column Counts
     */
    fun getResponsiveDimensions(layoutWidth: Dp, gutter: Dp?): ResponsiveConfig.Row {

        /**
         * Screen Type by Layout Width
         */
        val screenType = getScreenType(layoutWidth)

        /**
         * 1 Margin Width
         *
         * Scaling Margin Width - Use Scaling Margin Width
         * Fixed Margin Width - Use Default Margin Width
         */
        val marginWidth = if(screenType.marginWidth == Scaling) Util.getScalingMarginWidth(layoutWidth,screenType.bodyWidth)
                            else screenType.marginWidth

        /**
         * 1 Gutter Width
         *
         * Unless Custom Gutter, Use Default Gutter Width.
         */
        val gutterWidth = gutter ?: screenType.gutterDefault

        /**
         * 1 Column Width
         *
         * Scaling Body Width - Use Fixed Column Width
         * Fixed Body Width - Use Scaling Column Width
         */
        val columnWidth = if(screenType.bodyWidth == Scaling) Util.getFixedColumWidth(layoutWidth, marginWidth, gutterWidth, screenType.columnCounts)
                            else Util.getScalingColumnWidth(screenType.bodyWidth, gutterWidth, screenType.columnCounts)

       return ResponsiveConfig.Row(
            layoutWidth = layoutWidth,
            marginWidth = marginWidth,
            columnWidth = columnWidth,
            gutterWidth = gutterWidth,
            columnCounts = screenType.columnCounts
        )

    }
}


