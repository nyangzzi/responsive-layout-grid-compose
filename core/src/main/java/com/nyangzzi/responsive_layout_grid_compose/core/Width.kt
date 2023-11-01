package com.nyangzzi.responsive_layout_grid_compose.core

import android.util.Log
import androidx.annotation.IntRange
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.unit.Dp


/**
 * widthForColumns is a fixed value based on the [GridConfiguration] to calculate from grid system.
 *
 * throws [IllegalStateException] when [LocalGridConfiguration] is not configured.
 *
 *
 */
@Composable
fun widthForColumns(columnSpan: Int): Dp {
    val gridConfiguration = LocalGridConfiguration.current

    if (columnSpan > gridConfiguration.totalColumns)
        Log.w(
            "LocalGridConfiguration",
            "Column count($columnSpan) exceeds Total Columns(${gridConfiguration.totalColumns})"
        )

    return gridConfiguration.columnWidth.times(columnSpan) + gridConfiguration.gutterWidth.times(
        columnSpan - 1
    )
}