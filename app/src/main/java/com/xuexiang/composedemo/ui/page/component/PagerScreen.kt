package com.xuexiang.composedemo.ui.page.component

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.lerp
import coil.compose.AsyncImage
import com.xuexiang.composedemo.model.DemoDataProvider
import com.xuexiang.composedemo.ui.widget.ScrollColumnArea
import kotlin.math.absoluteValue

@Composable
fun PagerScreen() {
    val urls = DemoDataProvider.urls
    ScrollColumnArea {
        BannerComponent(urls)
    }
}

@Composable
@OptIn(ExperimentalFoundationApi::class)
private fun BannerComponent(urls: Array<String>) {
    val pagerState = rememberPagerState(pageCount = {
        urls.size
    })
    Box {
        HorizontalPager(
            state = pagerState, pageSpacing = 32.dp
        ) { pageIndex ->
            BannerItem(
                urls = urls, pageIndex = pageIndex, pagerState = pagerState
            )
        }
        Indicator(modifier = Modifier.align(Alignment.BottomCenter), pagerState)
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun BannerItem(
    urls: Array<String>, pageIndex: Int, pagerState: PagerState
) {
    Card(
        Modifier
            .fillMaxWidth()
            .aspectRatio(1.78f)
            .graphicsLayer {
                alpha = getAlphaByOffset(pagerState, pageIndex)
            }) {
        AsyncImage(
            modifier = Modifier.fillMaxSize(), model = urls[pageIndex], contentDescription = null
        )
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun Indicator(
    modifier: Modifier = Modifier, pagerState: PagerState
) {
    Row(
        modifier = modifier
            .wrapContentHeight()
            .fillMaxWidth()
            .padding(bottom = 8.dp),
        horizontalArrangement = Arrangement.Center
    ) {
        repeat(pagerState.pageCount) { iteration ->
            val color = if (pagerState.currentPage == iteration) Color.DarkGray else Color.LightGray
            Box(
                modifier = Modifier
                    .padding(3.dp)
                    .clip(CircleShape)
                    .background(color)
                    .size(8.dp)
            )
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
private fun getAlphaByOffset(
    pagerState: PagerState, page: Int
): Float {
    val pageOffset =
        ((pagerState.currentPage - page) + pagerState.currentPageOffsetFraction).absoluteValue
    // We animate the alpha, between 50% and 100%
    return lerp(
        start = 0.5f, stop = 1f, fraction = 1f - pageOffset.coerceIn(0f, 1f)
    )
}

