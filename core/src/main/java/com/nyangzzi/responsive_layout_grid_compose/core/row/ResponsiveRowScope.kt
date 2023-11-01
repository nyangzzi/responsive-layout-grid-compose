package com.nyangzzi.responsive_layout_grid_compose.core.row

import androidx.annotation.IntRange
import androidx.compose.foundation.layout.LayoutScopeMarker
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import com.nyangzzi.responsive_layout_grid_compose.core.widthForColumns

@LayoutScopeMarker
@Immutable
interface ResponsiveRowScope {
    fun Modifier.horizontalWeight(weight: Int) : Modifier

}

internal object ResponsiveRowInstance : ResponsiveRowScope {
    @Stable
    override fun Modifier.horizontalWeight(
        @IntRange(from = 0) weight: Int
    ) : Modifier = composed {

        require(weight > 0) { "Weight must be greater than 0 (Now Weight is $weight)" }

        this.then(Modifier.width(widthForColumns(columnSpan = weight))
        )
    }
}
