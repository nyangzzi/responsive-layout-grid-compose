package com.nyangzzi.responsive_layout_grid_compose.core.layout

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.nyangzzi.responsive_layout_grid_compose.core.rememberGridConfiguration
import kotlin.math.roundToInt

enum class ScreenType {
    PHONE,
    TABLET_NORMAL,
    TABLET_LARGE,
    LAPTOP,
    DESKTOP
}

fun screenTypesFactory(dimension: Dp) = when (dimension.value.roundToInt().dp) {
    in (0..599).map { it.dp } -> ScreenType.PHONE
    in (600..904).map { it.dp } -> ScreenType.TABLET_NORMAL
    in (905..1239).map { it.dp } -> ScreenType.TABLET_LARGE
    in (1240..1439).map { it.dp } -> ScreenType.LAPTOP
    else -> ScreenType.DESKTOP
}

fun getResponsiveDimensions(totalScreenWidth: Dp) = when (screenTypesFactory(totalScreenWidth)) {
    ScreenType.PHONE -> {
        val noOfColumns = 4
        val gutterWidth = 16.dp
        val marginWidth = 16.dp
        val columnWidth =
            ((totalScreenWidth - ((2 * marginWidth.value).dp) - (((noOfColumns - 1) * gutterWidth.value).dp)) / noOfColumns)
        ResponsiveDimensions(noOfColumns, columnWidth, gutterWidth, marginWidth)
    }
    ScreenType.TABLET_NORMAL -> {
        val noOfColumns = 8
        val gutterWidth = 24.dp
        val marginWidth = 32.dp
        val columnWidth =
            ((totalScreenWidth - ((2 * marginWidth.value).dp) - (((noOfColumns - 1) * gutterWidth.value).dp)) / noOfColumns)
        ResponsiveDimensions(noOfColumns, columnWidth, gutterWidth, marginWidth)
    }
    ScreenType.TABLET_LARGE -> {
        val noOfColumns = 12
        val gutterWidth = 24.dp
        val marginWidth = (totalScreenWidth - 840.dp) / 2
        val columnWidth = (840.dp - ((noOfColumns - 1) * gutterWidth.value).dp) / 12
        ResponsiveDimensions(noOfColumns, columnWidth, gutterWidth, marginWidth)
    }
    ScreenType.LAPTOP -> {
        val noOfColumns = 12
        val gutterWidth = 32.dp
        val marginWidth = 200.dp
        val columnWidth =
            ((totalScreenWidth - ((2 * marginWidth.value).dp) - (((noOfColumns - 1) * gutterWidth.value).dp)) / noOfColumns)
        ResponsiveDimensions(noOfColumns, columnWidth, gutterWidth, marginWidth)
    }
    ScreenType.DESKTOP -> {
        val noOfColumns = 12
        val gutterWidth = 32.dp
        val marginWidth = (totalScreenWidth - 1040.dp) / 2
        val columnWidth = (1040.dp - ((noOfColumns - 1) * gutterWidth.value).dp) / 12
        ResponsiveDimensions(noOfColumns, columnWidth, gutterWidth, marginWidth)
    }
}

sealed class BreakPoint {

}

data class ResponsiveDimensions (
    val totalColumns : Int,
    val columnWidth : Dp,
    val gutterWidth : Dp,
    val marginWidth : Dp
)