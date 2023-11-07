package com.nyangzzi.responsive_layout_grid_compose.core

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

object Util {
    fun getFixedColumWidth(layoutWidth: Dp, horizontalMargin: Dp, gutterWidth:Dp, totalColumns: Int)
            = ((layoutWidth - (horizontalMargin * 2) - gutterWidth * (totalColumns - 1)) / totalColumns)

    fun getScalingColumnWidth(bodyWidth: Dp, gutterWidth:Dp, totalColumns: Int)
            = ((bodyWidth - ((totalColumns - 1) * gutterWidth.value).dp) / totalColumns)

    fun getScalingMarginWidth(layoutWidth: Dp,bodyWidth: Dp) = ((layoutWidth - bodyWidth) / 2)

    fun getRowHeight(layoutHeight: Dp, verticalMargin: Dp, gutterHeight:Dp, totalRows: Int)
            = ((layoutHeight - (verticalMargin * 2) - gutterHeight * (totalRows - 1)) / totalRows)
}

