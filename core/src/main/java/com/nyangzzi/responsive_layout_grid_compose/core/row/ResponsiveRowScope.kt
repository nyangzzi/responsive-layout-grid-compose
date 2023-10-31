package com.nyangzzi.responsive_layout_grid_compose.core.row

import androidx.annotation.IntRange
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Stable
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import com.nyangzzi.responsive_layout_grid_compose.core.ConstResponsiveRow
import com.nyangzzi.responsive_layout_grid_compose.core.widthForColumns

@Stable
interface ResponsiveRowScope {
    fun Modifier.horizontalWeight(columns: Int) : Modifier
}

internal object ResponsiveRowInstance : ResponsiveRowScope {
    @Stable
    override fun Modifier.horizontalWeight(
        @IntRange(from = -1) columns: Int
    ) : Modifier = composed {

        require(columns >= -1) {"Weight must be greater than 0 (Now Weight is $columns)"}

        this.then(
            when(columns){
                ConstResponsiveRow.MathParent -> Modifier.fillMaxWidth()
                else -> Modifier.width(widthForColumns(columnSpan = columns))
            }
        )
    }
}