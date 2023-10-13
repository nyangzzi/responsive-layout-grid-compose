package com.nyangzzi.response_layout_grid_compose.core.layout

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
import com.nyangzzi.response_layout_grid_compose.core.LocalGridConfiguration
import com.nyangzzi.response_layout_grid_compose.core.columnedWidth
import com.nyangzzi.response_layout_grid_compose.core.rememberGridConfiguration


/**
 *
 * @param columnSpan    배율
 * @param content       콘텐츠
 *
 * **/

data class LayoutData(
    val columnSpan: Int = 0,
    val content: @Composable BoxScope.() -> Unit = {}
)

/**
 * 여백 정보
 *
 * @param  verticalMargin 상하 마진 (기본값 : 24dp),
 * @param  horizontalMargin 좌우 마진 (기본값 : 24dp)
 * @param  gutterWidth 좌우 gutter (기본값 : 16dp)
 *
 * **/
data class RSPLayoutWhiteSpace(
    val verticalMargin: Dp = 24.dp,
    val horizontalMargin: Dp = 24.dp,
    val gutterWidth: Dp = 16.dp,
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
fun ResponseVerticalLayout(
    modifier: Modifier = Modifier,
    contents: List<LayoutData> = emptyList(),
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
        totalColumns = if (totalColumns == null || totalColumns < sumColumns) sumColumns else totalColumns
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
                    ResponseVerticalLayoutContent(span = content.columnSpan, content = content.content)
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
private fun ResponseVerticalLayoutContent(
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