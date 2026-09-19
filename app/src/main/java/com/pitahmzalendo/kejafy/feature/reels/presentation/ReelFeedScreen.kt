package com.pitahmzalendo.kejafy.feature.reels.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.pager.VerticalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.pitahmzalendo.kejafy.feature.reels.presentation.components.ReelPlayer
import com.pitahmzalendo.kejafy.feature.reels.presentation.components.ReelOverlay

@Composable
fun ReelFeedScreen(
    state: ReelFeedUiState,
    onLikeClick: (Long) -> Unit,
    onShareClick: (Long) -> Unit,
    onViewPropertyClick: (Long) -> Unit,
    modifier: Modifier = Modifier
) {
    val pagerState = rememberPagerState(pageCount = { state.reels.size })

    Box(modifier = modifier.fillMaxSize()) {
        VerticalPager(
            state = pagerState,
            modifier = Modifier.fillMaxSize()
        ) { pageIndex ->
            val reel = state.reels[pageIndex]
            val isCurrentPage = pagerState.currentPage == pageIndex
            
            Box(modifier = Modifier.fillMaxSize()) {
                ReelPlayer(
                    videoUrl = reel.videoUrl,
                    isPlaying = isCurrentPage
                )

                ReelOverlay(
                    title = "Property #${reel.propertyId}",
                    neighbourhood = "Kilimani", // Mock for now
                    price = "KSh 45,000/mo", // Mock for now
                    isLiked = false,
                    onLikeClick = { onLikeClick(reel.id) },
                    onShareClick = { onShareClick(reel.id) },
                    onViewPropertyClick = { onViewPropertyClick(reel.propertyId) }
                )
            }
        }
    }
}
