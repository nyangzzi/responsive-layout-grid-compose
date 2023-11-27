package com.nyangzzi.responsive_layout_grid_compose.core.row

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.nyangzzi.responsive_layout_grid_compose.core.ResponsiveConfig
import com.nyangzzi.responsive_layout_grid_compose.core.Util
import kotlin.math.roundToInt

object ResponsiveRowBreakPoint {

    val Scaling = (-1).dp

    sealed class ScreenType(val noOfColumns: Int, val gutterDefault : Dp, val marginWidth: Dp, val bodyWidth: Dp) {
        object Phone : ScreenType(noOfColumns = 4, gutterDefault = 16.dp, marginWidth = 16.dp, bodyWidth= Scaling)
        object TabletSmall : ScreenType(noOfColumns = 8, gutterDefault = 24.dp, marginWidth = 32.dp, bodyWidth = Scaling)
        object TabletLarge : ScreenType(noOfColumns = 12, gutterDefault = 24.dp, marginWidth = Scaling, bodyWidth = 840.dp)
        object Laptop : ScreenType(noOfColumns = 12, gutterDefault = 32.dp, marginWidth = 200.dp, bodyWidth = Scaling)
        object Desktop : ScreenType(noOfColumns = 12, gutterDefault = 32.dp, marginWidth = Scaling, bodyWidth = 1040.dp)
    }

    fun getScreenType(dimension: Dp) = when (dimension.value.roundToInt().dp) {
        in (0..599).map { it.dp } -> ScreenType.Phone
        in (600..904).map { it.dp } -> ScreenType.TabletSmall
        in (905..1239).map { it.dp } -> ScreenType.TabletLarge
        in (1240..1439).map { it.dp } -> ScreenType.Laptop
        else -> ScreenType.Desktop
    }

    fun getResponsiveDimensions(totalScreenWidth: Dp, gutter: Dp?): ResponsiveConfig.Row {
        val screenType = getScreenType(totalScreenWidth)

        val marginWidth = if(screenType.marginWidth == Scaling) Util.getScalingMarginWidth(totalScreenWidth,screenType.bodyWidth) else screenType.marginWidth
        val gutterWidth = gutter ?: screenType.gutterDefault
        val columnWidth = if(screenType.bodyWidth == Scaling) Util.getFixedColumWidth(totalScreenWidth, marginWidth, gutterWidth, screenType.noOfColumns) else
            Util.getScalingColumnWidth(screenType.bodyWidth, gutterWidth, screenType.noOfColumns)

       return ResponsiveConfig.Row(
            layoutWidth = totalScreenWidth,
            marginWidth = marginWidth,
            columnWidth = columnWidth,
            gutterWidth = gutterWidth,
            totalColumns = screenType.noOfColumns
        )

    }
}


