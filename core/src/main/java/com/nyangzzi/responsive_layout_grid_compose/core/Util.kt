package com.nyangzzi.responsive_layout_grid_compose.core

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

/**
 *  UI Util
 *
 * @author nyangzzi(eunkyung lee)
 */
object Util {

    /**
     * Fixed value for 1 Column Width.
     * Use when Scaling Body Width, Fixed Gutter Width and Fixed Horizontal Margin.
     *
     * @param layoutWidth The Width of the layout.
     * @param horizontalMargin The horizontal Margin of the layout.
     * @param gutterWidth The horizontal Gutter of the layout.
     * @param columnCounts Number of columns based on which the layout is divided.
     *
     * @return 1 Column Width
     */
    fun getFixedColumWidth(layoutWidth: Dp, horizontalMargin: Dp, gutterWidth:Dp, columnCounts: Int)
            = ((layoutWidth - (horizontalMargin * 2) - gutterWidth * (columnCounts - 1)) / columnCounts)

    /**
     * Scaling value for 1 Column Width.
     * Use When Fixed Body Width and Fixed Horizontal Margin.
     *
     * @param bodyWidth
     * @param gutterWidth
     * @param columnCounts
     *
     * @return 1 Column Width
     */
    fun getScalingColumnWidth(bodyWidth: Dp, gutterWidth:Dp, columnCounts: Int)
            = ((bodyWidth - ((columnCounts - 1) * gutterWidth.value).dp) / columnCounts)

    /**
     * Scaling value for 1 Horizontal Margin.
     * Use When Fixed Layout Width and Fixed Body Width.
     *
     * @param layoutWidth The Width of the layout. (Body Width + Margin)
     * @param bodyWidth The Width of the body. (Columns + Gutters)
     *
     * @return 1 Horizontal Margin
     */
    fun getScalingMarginWidth(layoutWidth: Dp, bodyWidth: Dp) = ((layoutWidth - bodyWidth) / 2)

    /**
     * Fixed value for 1 Row Height.
     * Use when Scaling Body Height, Fixed Gutter Height and Fixed Vertical Margin.
     *
     * @param layoutHeight
     * @param verticalMargin
     * @param gutterHeight
     * @param totalRows
     *
     * @return 1 Row Height
     */
    fun getFixedRowHeight(layoutHeight: Dp, verticalMargin: Dp, gutterHeight:Dp, totalRows: Int)
            = ((layoutHeight - (verticalMargin * 2) - gutterHeight * (totalRows - 1)) / totalRows)
}

