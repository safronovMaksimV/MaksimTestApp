@file:OptIn(ExperimentalMaterial3Api::class)

package com.fpu.maksimtestapp.presentation.features.home

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemContentType
import androidx.paging.compose.itemKey
import com.fpu.maksimtestapp.R
import com.fpu.maksimtestapp.presentation.model.UIChallenge
import com.fpu.maksimtestapp.ui.components.ChallengesLoadingContent
import com.fpu.maksimtestapp.ui.theme.bodySmall
import com.fpu.maksimtestapp.ui.theme.cardBorderColor
import com.fpu.maksimtestapp.ui.theme.shimmer1
import com.fpu.maksimtestapp.ui.theme.shimmer2
import com.fpu.maksimtestapp.ui.theme.titleLarge
import com.fpu.maksimtestapp.ui.theme.titleMedium

const val CHALLENGE_SHIMMER_REPEAT = 3

@Composable
fun HomeRoute(
    onNavigateToChallengeDetails: (String) -> Unit,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    HomeScreen(
        uiState = uiState,
        onEvent = viewModel::setEvent,
        onNavigateToChallengeDetails = onNavigateToChallengeDetails
    )
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun HomeScreen(
    uiState: HomeScreenContract.State,
    onEvent: (HomeScreenContract.Event) -> Unit,
    onNavigateToChallengeDetails: (String) -> Unit
) {
    var isRefreshing by remember { mutableStateOf(uiState.isLoading) }
    val pullRefreshState = rememberPullRefreshState(
        refreshing = isRefreshing,
        onRefresh = { onEvent(HomeScreenContract.Event.RefreshScreen) }
    )
    val challenges = uiState.challengeList.collectAsLazyPagingItems()
    Column {
        TopBar()
        Box(
            modifier = Modifier
                .pullRefresh(pullRefreshState)
        ) {
            isRefreshing = uiState.isLoading
            if (!challenges.loadState.prepend.endOfPaginationReached) {
                onEvent(HomeScreenContract.Event.SetRefreshing(true))
                ChallengesLoadingContent()
            } else {
                onEvent(HomeScreenContract.Event.SetRefreshing(false))
                LazyColumn(
                    modifier = Modifier
                        .padding(horizontal = 10.dp)
                        .padding(top = 10.dp)
                        .testTag(stringResource(id = R.string.scrollable_layout_tag))
                ) {
                    items(
                        count = challenges.itemCount,
                        key = challenges.itemKey(),
                        contentType = challenges.itemContentType()
                    ) { index ->
                        challenges[index]?.let { challenge ->
                            ChallengeCard(
                                challenge = challenge,
                                onClick = { onNavigateToChallengeDetails.invoke(challenge.id) }
                            )
                            Spacer(modifier = Modifier.padding(10.dp))
                        }
                    }
                    if (challenges.loadState.append is LoadState.Loading ||
                        challenges.loadState.refresh is LoadState.Loading
                    ) {
                        item(key = "loading") {
                            ShimmerAnimation { brush ->
                                Spacer(modifier = Modifier.height(20.dp))
                                repeat(CHALLENGE_SHIMMER_REPEAT) {
                                    CheckInsListChallengeLoadingState(brush)
                                    Spacer(Modifier.height(30.dp))
                                }
                            }
                        }
                    }
                }
            }
            PullRefreshIndicator(
                isRefreshing,
                pullRefreshState,
                Modifier
                    .align(Alignment.TopCenter)
                    .testTag(stringResource(id = R.string.loading_refresh_indicator_tag))
            )
        }
    }
}

@Composable
fun CheckInsListChallengeLoadingState(brush: Brush) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        border = BorderStroke(1.dp, androidx.compose.material.MaterialTheme.colors.cardBorderColor),
    ) {
        Row(
            modifier = Modifier.padding(10.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 10.dp)
            ) {
                Spacer(
                    modifier = Modifier
                        .fillMaxWidth(0.8f)
                        .height(20.dp)
                        .clip(RoundedCornerShape(0.dp))
                        .background(brush = brush)
                )
                Spacer(
                    modifier = Modifier.size(4.dp)
                )

                Spacer(
                    modifier = Modifier
                        .fillMaxWidth(0.3f)
                        .height(20.dp)
                        .clip(RoundedCornerShape(0.dp))
                        .background(brush = brush)
                )
                Spacer(
                    modifier = Modifier.size(5.dp)
                )
                Spacer(
                    modifier = Modifier
                        .fillMaxWidth(0.3f)
                        .height(20.dp)
                        .clip(RoundedCornerShape(0.dp))
                        .background(brush = brush)
                )
            }
        }
    }
}

@Suppress("MagicNumber")
@Composable
fun ShimmerAnimation(
    content: @Composable ColumnScope.(brush: Brush) -> Unit
) {
    val shimmerColorShades = listOf(
        androidx.compose.material.MaterialTheme.colors.shimmer1,
        androidx.compose.material.MaterialTheme.colors.shimmer2,
        androidx.compose.material.MaterialTheme.colors.shimmer1
    )

    val transition = rememberInfiniteTransition(label = "")
    val translateAnim by transition.animateFloat(
        initialValue = 0f,
        targetValue = 1000f,
        animationSpec = infiniteRepeatable(
            tween(durationMillis = 600, easing = FastOutSlowInEasing),
            RepeatMode.Reverse
        ), label = ""
    )

    val brush = Brush.linearGradient(
        colors = shimmerColorShades,
        start = Offset(10f, 10f),
        end = Offset(translateAnim, translateAnim)
    )

    Column {
        content(this, brush)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar() {
    TopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            titleContentColor = MaterialTheme.colorScheme.primary,
        ),
        title = {
            Text(
                stringResource(id = R.string.home_screen_title),
                maxLines = 1,
                style = titleLarge,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.fillMaxWidth()
            )
        },
    )
}

@Composable
fun ChallengeCard(challenge: UIChallenge, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(
                onClick = onClick,
                indication = null,
                interactionSource = remember { MutableInteractionSource() }
            ),
        shape = RoundedCornerShape(16.dp),
        border = BorderStroke(1.dp, androidx.compose.material.MaterialTheme.colors.cardBorderColor),
    ) {
        Row(
            modifier = Modifier.padding(10.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp)
            ) {
                Text(
                    modifier = Modifier
                        .fillMaxWidth(),
                    style = titleMedium,
                    text = challenge.name
                )

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 10.dp)
                ) {
                    Text(
                        modifier = Modifier
                            .wrapContentWidth(),
                        style = bodySmall,
                        text = "Completion date - "
                    )
                    Spacer(modifier = Modifier.padding(horizontal = 5.dp))
                    Text(
                        modifier = Modifier
                            .fillMaxWidth(),
                        style = bodySmall,
                        text = challenge.completedAt
                    )
                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 10.dp)
                ) {
                    Text(
                        modifier = Modifier
                            .fillMaxWidth(),
                        style = bodySmall,
                        text = "Completed languages: " + challenge.completedLanguages.joinToString(
                            separator = ","
                        ) { it }
                    )
                }
            }
        }
    }
}