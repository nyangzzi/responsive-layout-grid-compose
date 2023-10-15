package com.nyangzzi.responsive_layout_grid_compose.core.layout

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.nyangzzi.responsive_layout_grid_compose.core.LocalGridConfiguration
import com.nyangzzi.responsive_layout_grid_compose.core.columnedWidth
import com.nyangzzi.responsive_layout_grid_compose.core.rememberGridConfiguration


/**
 *
 * @param columnSpan    배율
 * @param content       콘텐츠
 *
 * **/

data class ResponsiveLayoutData(
    val columnSpan: Int = 0,
    val content: @Composable BoxScope.() -> Unit = {}
)

/**
 *
 * base layout
 *
 * @param contents
 * @param layoutWidth 기본 영역 넓이
 * @param backgroundColor 기본 백그라운드 색상
 * @param totalColumns 총 비율 / totalColumns는 항상 자식 비율의 합보다 커야 합니다. (totalColumns >= sumColumns)
 * @param modifier
 *
 * **/
@Composable
fun ResponsiveVerticalLayout(
    modifier: Modifier = Modifier,
    contents: List<ResponsiveLayoutData> = emptyList(),
    verticalMargin: Dp,
    horizontalMargin: Dp,
    gutterWidth: Dp,
    layoutWidth: Dp = LocalConfiguration.current.screenWidthDp.dp,
    totalColumns: Int? = null,
    backgroundColor: Color = MaterialTheme.colorScheme.background,
) {

    val sumColumns = contents.sumOf { it.columnSpan }

    val configuration = rememberGridConfiguration(
        layoutWidth = layoutWidth,
        horizontalMargin = horizontalMargin,
        gutterWidth = gutterWidth,
        totalColumns = if (totalColumns == null || totalColumns < sumColumns) sumColumns else totalColumns,
                columnWidth = 0.dp
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor)
            .then(modifier)
    ) {
        Row(
            modifier = Modifier
                .align(Alignment.Center)
                .padding(
                    horizontal = configuration.horizontalMargin,
                    vertical = verticalMargin
                ),
            horizontalArrangement = Arrangement.spacedBy(configuration.gutterWidth)
        ) {
            CompositionLocalProvider(LocalGridConfiguration provides configuration) {
                for (content in contents) {
                    ResponsiveVerticalLayoutContent(span = content.columnSpan, content = content.content)
                }
            }
        }
    }

}

/**
 *
 * 공통 layout 의 내부 컨텐츠
 *
 * @param span 배율 (column 개수)
 * @param content 내부 콘텐츠
 *
 * **/
@Composable
private fun ResponsiveVerticalLayoutContent(
    span: Int = 0,
    content: @Composable BoxScope.() -> Unit
) {
    Box(
        modifier = Modifier
            .columnedWidth(span)
            .fillMaxHeight()
    ) {
        content()
    }
}

@Composable
fun ResponsiveVerticalLayout(
    modifier: Modifier = Modifier,
    contents: List<ResponsiveLayoutData> = emptyList(),
    layoutWidth: Dp = LocalConfiguration.current.screenWidthDp.dp,
    backgroundColor: Color = MaterialTheme.colorScheme.background,
) {

    val sumColumns = contents.sumOf { it.columnSpan }

    val responsiveDimensions = getResponsiveDimensions(layoutWidth)
    val configuration = rememberGridConfiguration(
        layoutWidth = layoutWidth,
        horizontalMargin = responsiveDimensions.marginWidth,
        gutterWidth = responsiveDimensions.gutterWidth,
        totalColumns = responsiveDimensions.totalColumns,
        columnWidth = responsiveDimensions.columnWidth
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor)
            .then(modifier)
    ) {
        Row(
            modifier = Modifier
                .align(Alignment.Center)
                .padding(
                    horizontal = configuration.horizontalMargin,
                    //vertical = verticalMargin
                ),
            horizontalArrangement = Arrangement.spacedBy(configuration.gutterWidth)
        ) {
            CompositionLocalProvider(LocalGridConfiguration provides configuration) {
                for (content in contents) {
                    ResponsiveVerticalLayoutContent(span = content.columnSpan, content = content.content)
                }
            }
        }
    }

}