package com.nyangzzi.responsive_layout_grid_compose.core.row

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun ResponsiveRow(
    modifier: Modifier = Modifier,
    content: @Composable ResponsiveRowScope.() -> Unit) {
    ResponsiveRowInstance.content()
}