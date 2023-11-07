package com.nyangzzi.responsive_layout_grid_compose.core.row

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.nyangzzi.responsive_layout_grid_compose.core.ResponsiveConfig
import com.nyangzzi.responsive_layout_grid_compose.core.Util
import kotlin.math.roundToInt

object ResponsiveBreakPoint {

    enum class ScreenType {
        PHONE,
        TABLET_NORMAL,
        TABLET_LARGE,
        LAPTOP,
        DESKTOP
    }

    fun getScreenType(dimension: Dp) = when (dimension.value.roundToInt().dp) {
        in (0..599).map { it.dp } -> ScreenType.PHONE
        in (600..904).map { it.dp } -> ScreenType.TABLET_NORMAL
        in (905..1239).map { it.dp } -> ScreenType.TABLET_LARGE
        in (1240..1439).map { it.dp } -> ScreenType.LAPTOP
        else -> ScreenType.DESKTOP
    }

    fun getNoOfColum(screenType: ScreenType) = when(screenType){
        ScreenType.PHONE -> 4
        ScreenType.TABLET_NORMAL -> 8
        ScreenType.TABLET_LARGE -> 12
        ScreenType.LAPTOP -> 12
        ScreenType.DESKTOP -> 12
    }

    fun getResponsiveDimensions(totalScreenWidth: Dp, gutter: Dp?) = when (getScreenType(totalScreenWidth)) {
        ScreenType.PHONE -> {
            val noOfColumns = 4
            val gutterWidth = gutter ?: 16.dp
            val marginWidth = 16.dp

            ResponsiveConfig.Row(
                layoutWidth = totalScreenWidth,
                marginWidth = marginWidth,
                columnWidth = Util.getFixedColumWidth(totalScreenWidth, marginWidth, gutterWidth, noOfColumns),
                gutterWidth = gutterWidth,
                totalColumns = noOfColumns
            )

        }
        ScreenType.TABLET_NORMAL -> {
            val noOfColumns = 8
            val gutterWidth = gutter ?: 24.dp
            val marginWidth = 32.dp
            ResponsiveConfig.Row(
                layoutWidth = totalScreenWidth,
                marginWidth = marginWidth,
                columnWidth = Util.getFixedColumWidth(totalScreenWidth, marginWidth, gutterWidth, noOfColumns),
                gutterWidth = gutterWidth,
                totalColumns = noOfColumns
            )
        }
        ScreenType.TABLET_LARGE -> {
            val noOfColumns = 12
            val gutterWidth = gutter ?: 24.dp

            ResponsiveConfig.Row(
                layoutWidth = totalScreenWidth,
                marginWidth = (totalScreenWidth - 840.dp) / 2,
                columnWidth = (840.dp - ((noOfColumns - 1) * gutterWidth.value).dp) / 12,
                gutterWidth = gutterWidth,
                totalColumns = noOfColumns
            )
        }
        ScreenType.LAPTOP -> {
            val noOfColumns = 12
            val gutterWidth = gutter ?: 32.dp
            val marginWidth = 200.dp
            ResponsiveConfig.Row(
                layoutWidth = totalScreenWidth,
                marginWidth = marginWidth,
                columnWidth = Util.getFixedColumWidth(totalScreenWidth, marginWidth, gutterWidth, noOfColumns),
                gutterWidth = gutterWidth,
                totalColumns = noOfColumns
            )
        }
        ScreenType.DESKTOP -> {
            val noOfColumns = 12
            val gutterWidth = gutter ?: 32.dp
            ResponsiveConfig.Row(
                layoutWidth = totalScreenWidth,
                marginWidth =  (totalScreenWidth - 1040.dp) / 2,
                columnWidth = (1040.dp - ((noOfColumns - 1) * gutterWidth.value).dp) / 12 ,
                gutterWidth = gutterWidth,
                totalColumns = noOfColumns
            )
        }
    }

}


