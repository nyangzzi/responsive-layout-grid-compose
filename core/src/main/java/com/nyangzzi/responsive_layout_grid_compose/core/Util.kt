package com.nyangzzi.responsive_layout_grid_compose.core

import androidx.compose.ui.unit.Dp

fun getColumWidth(layoutWidth: Dp, horizontalMargin: Dp,gutterWidth:Dp,totalColumns:Int)
    = ((layoutWidth - (horizontalMargin * 2) - gutterWidth * (totalColumns - 1)) / totalColumns)
