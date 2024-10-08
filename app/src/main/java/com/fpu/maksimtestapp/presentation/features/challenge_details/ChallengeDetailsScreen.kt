package com.fpu.maksimtestapp.presentation.features.challenge_details

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.fpu.maksimtestapp.R
import com.fpu.maksimtestapp.ui.components.ChallengesLoadingContent
import com.fpu.maksimtestapp.ui.theme.bodyMedium
import com.fpu.maksimtestapp.ui.theme.cardBorderColor
import com.fpu.maksimtestapp.ui.theme.titleLarge
import com.fpu.maksimtestapp.ui.theme.titleMedium

@Composable
fun ChallengeDetailsRoute(
    onBackClick: () -> Unit,
    challengeId: String,
    viewModel: ChallengeDetailsViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.setEvent(ChallengeDetailsScreenContract.Event.FetchChallengeInfoById(challengeId))
    }
    CardDetailsScreen(
        uiState = uiState,
        onEvent = viewModel::setEvent,
        onBackClick = onBackClick,
    )
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun CardDetailsScreen(
    uiState: ChallengeDetailsScreenContract.State,
    onEvent: (ChallengeDetailsScreenContract.Event) -> Unit,
    onBackClick: () -> Unit,
) {
    var isRefreshing by remember { mutableStateOf(uiState.isLoading) }
    val pullRefreshState = rememberPullRefreshState(
        refreshing = isRefreshing,
        onRefresh = {
            onEvent(
                ChallengeDetailsScreenContract.Event.FetchChallengeInfoById(
                    uiState.challenge?.id.orEmpty()
                )
            )
        }
    )
    Column {
        TopBar(
            challengeTitle = uiState.challenge?.name ?: "",
            onBackClick = onBackClick
        )
        Box(
            modifier = Modifier
                .pullRefresh(pullRefreshState)
        ) {
            isRefreshing = uiState.isLoading
            if (uiState.isLoading) {
                ChallengesLoadingContent()
            } else {
                LazyColumn(
                    modifier = Modifier
                        .testTag(stringResource(id = R.string.scrollable_layout_tag))
                        .padding(horizontal = 10.dp)
                        .fillMaxSize()
                ) {
                    uiState.challenge?.let { challenge ->
                        if (challenge.category.isNotBlank()) {
                            item {
                                ChallengeDetailsCard(
                                    title = stringResource(id = R.string.challenge_category_title),
                                    content = challenge.category
                                )
                            }
                        }

                        if (challenge.description.isNotBlank()) {
                            item {
                                ChallengeDetailsCard(
                                    title = stringResource(id = R.string.challenge_description_title),
                                    content = challenge.description
                                )
                            }
                        }

                        if (challenge.tags.isNotEmpty()) {
                            item {
                                ChallengeDetailsCard(
                                    title = stringResource(id = R.string.challenge_tags_title),
                                    content = challenge.tags.joinToString(
                                        separator = ", "
                                    ) { it }
                                )
                            }
                        }

                        if (challenge.languages.isNotEmpty()) {
                            item {
                                ChallengeDetailsCard(
                                    title = stringResource(id = R.string.challenge_languages_title),
                                    content = challenge.languages.joinToString(
                                        separator = ", "
                                    ) { it }
                                )
                            }
                        }

                        if (challenge.rank.name.isNotBlank()) {
                            item {
                                ChallengeDetailsCard(
                                    title = stringResource(id = R.string.challenge_rank_title),
                                    content = challenge.getRankString()
                                )
                            }
                        }

                        if (challenge.createdBy.username.isNotBlank()) {
                            item {
                                ChallengeDetailsCard(
                                    title = stringResource(id = R.string.challenge_created_by_title),
                                    content = challenge.getCreatedByString()
                                )
                            }
                        }

                        if (challenge.approvedBy.username.isNotBlank()) {
                            item {
                                ChallengeDetailsCard(
                                    title = stringResource(id = R.string.challenge_approved_by_title),
                                    content = challenge.getApprovedByString()
                                )
                            }
                        }

                        item {
                            ChallengeDetailsCard(
                                title = stringResource(id = R.string.challenge_total_attempts_title),
                                content = challenge.totalAttempts.toString()
                            )
                        }

                        item {
                            ChallengeDetailsCard(
                                title = stringResource(id = R.string.challenge_total_completed_title),
                                content = challenge.totalCompleted.toString()
                            )
                        }

                        item {
                            ChallengeDetailsCard(
                                title = stringResource(id = R.string.challenge_total_stars_title),
                                content = challenge.totalStars.toString()
                            )
                        }

                        item {
                            ChallengeDetailsCard(
                                title = stringResource(id = R.string.challenge_vote_score_title),
                                content = challenge.voteScore.toString()
                            )
                        }

                        val publishedAt = challenge.getStringDateTime(challenge.publishedAt)
                        if (!publishedAt.isNullOrEmpty()) {
                            item {
                                ChallengeDetailsCard(
                                    title = stringResource(id = R.string.challenge_published_at_title),
                                    content = publishedAt
                                )
                            }
                        }

                        val approvedAt = challenge.getStringDateTime(challenge.publishedAt)
                        if (!approvedAt.isNullOrEmpty()) {
                            item {
                                ChallengeDetailsCard(
                                    title = stringResource(id = R.string.challenge_approved_at_title),
                                    content = approvedAt
                                )
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(challengeTitle: String, onBackClick: () -> Unit) {
    TopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            titleContentColor = MaterialTheme.colorScheme.primary,
        ),
        title = {
            Text(
                text = challengeTitle,
                style = titleLarge,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.fillMaxWidth(),
            )
        },
        navigationIcon = {
            IconButton(onClick = { onBackClick.invoke() }) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_arrow_back_24),
                    contentDescription = "Close",
                    tint = colorResource(id = R.color.black)
                )
            }
        }
    )
}

@Composable
fun ChallengeDetailsCard(title: String, content: String) {
    Card(
        modifier = Modifier
            .padding(top = 10.dp)
            .fillMaxWidth(),
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
                    text = title
                )

                Spacer(modifier = Modifier.padding(vertical = 5.dp))

                Text(
                    modifier = Modifier
                        .fillMaxWidth(),
                    style = bodyMedium,
                    text = content
                )
            }
        }
    }
}

