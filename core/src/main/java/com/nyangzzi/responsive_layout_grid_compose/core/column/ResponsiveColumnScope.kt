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

/**
 *
 */
@LayoutScopeMarker
@Immutable
interface ResponsiveColumnScope {
    fun Modifier.verticalWeight(weight: Int) : Modifier
}

/**
 *
 */
internal object ResponsiveColumnInstance : ResponsiveColumnScope {
    /**
     *
     *
     * @param weight The Vertical weight
     * @return Modifier.height (Fixed Dp)
     */
    @Stable
    override fun Modifier.verticalWeight(
        @IntRange(from = 1) weight: Int
    ) : Modifier = composed {

        require(weight > 0) { "Weight must be greater than 0 (Now Weight is $weight)" }

        this.then(Modifier.height(heightForRows(rowSpans = weight))
        )
    }
}

/**
 * TODO
 *
 * @param rowSpans
 * @return
 */
@Composable
internal fun heightForRows(rowSpans: Int): Dp {
    val gridConfiguration = LocalColumnConfiguration.current

    if (rowSpans > gridConfiguration.rowCounts)
        Log.w(
            "LocalGridConfiguration",
            "Row counts(now $rowSpans) exceeds Total Rows(now ${gridConfiguration.rowCounts})"
        )

    return gridConfiguration.rowHeight.times(rowSpans) + gridConfiguration.gutterHeight.times(rowSpans - 1)
}