package com.nyangzzi.responsive_layout_grid_compose.core.row

import android.util.Log
import androidx.annotation.IntRange
import androidx.compose.foundation.layout.LayoutScopeMarker
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.unit.Dp
import com.nyangzzi.responsive_layout_grid_compose.core.LocalRowConfiguration

@LayoutScopeMarker
@Immutable
interface ResponsiveRowScope {
    fun Modifier.horizontalWeight(weight: Int) : Modifier
}

internal object ResponsiveRowInstance : ResponsiveRowScope {
    @Stable
    override fun Modifier.horizontalWeight(
        @IntRange(from = 1) weight: Int
    ) : Modifier = composed {

        require(weight > 0) { "Weight must be greater than 0 (Now Weight is $weight)" }

        this.then(Modifier.width(widthForColumns(columnSpan = weight))
        )
    }
}

@Composable
internal fun widthForColumns(columnSpan: Int): Dp {
    val gridConfiguration = LocalRowConfiguration.current

    if (columnSpan > gridConfiguration.totalColumns)
        Log.w(
            "LocalGridConfiguration",
            "Column count($columnSpan) exceeds Total Columns(${gridConfiguration.totalColumns})"
        )

    return gridConfiguration.columnWidth.times(columnSpan) + gridConfiguration.gutterWidth.times(columnSpan - 1)
}