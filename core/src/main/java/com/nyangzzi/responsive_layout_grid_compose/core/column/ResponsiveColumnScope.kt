package com.nyangzzi.responsive_layout_grid_compose.core.column

import android.util.Log
import androidx.annotation.IntRange
import androidx.compose.foundation.layout.LayoutScopeMarker
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.unit.Dp
import com.nyangzzi.responsive_layout_grid_compose.core.LocalColumnConfiguration

@LayoutScopeMarker
@Immutable
interface ResponsiveColumnScope {
    fun Modifier.verticalWeight(weight: Int) : Modifier
}

internal object ResponsiveColumnInstance : ResponsiveColumnScope {
    @Stable
    override fun Modifier.verticalWeight(
        @IntRange(from = 1) weight: Int
    ) : Modifier = composed {

        require(weight > 0) { "Weight must be greater than 0 (Now Weight is $weight)" }

        this.then(Modifier.height(heightForRows(rowSpan = weight))
        )
    }
}

@Composable
internal fun heightForRows(rowSpan: Int): Dp {
    val gridConfiguration = LocalColumnConfiguration.current

    if (rowSpan > gridConfiguration.totalRows)
        Log.w(
            "LocalGridConfiguration",
            "Column count($rowSpan) exceeds Total Columns(${gridConfiguration.totalRows})"
        )

    return gridConfiguration.rowHeight.times(rowSpan) + gridConfiguration.gutterHeight.times(rowSpan - 1)
}