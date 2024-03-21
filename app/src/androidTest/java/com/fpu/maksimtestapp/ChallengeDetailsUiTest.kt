package com.fpu.maksimtestapp

import androidx.activity.compose.setContent
import androidx.annotation.StringRes
import androidx.compose.ui.test.assert
import androidx.compose.ui.test.hasScrollAction
import androidx.compose.ui.test.junit4.AndroidComposeTestRule
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performScrollToIndex
import com.fpu.maksimtestapp.presentation.features.challenge_details.CardDetailsScreen
import com.fpu.maksimtestapp.presentation.features.challenge_details.ChallengeDetailsScreenContract
import com.fpu.maksimtestapp.presentation.model.UIChallengeDetails
import com.fpu.maksimtestapp.presentation.model.UICreatedApprovedBy
import com.fpu.maksimtestapp.presentation.model.UIRank
import dagger.hilt.android.testing.BindValue
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import kotlin.properties.ReadOnlyProperty
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TemporaryFolder

@HiltAndroidTest
class ChallengeDetailsUiTest {

    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @BindValue
    @get:Rule(order = 1)
    val tmpFolder: TemporaryFolder = TemporaryFolder.builder().assureDeletion().build()

    @get:Rule(order = 2)
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    private fun AndroidComposeTestRule<*, *>.stringResource(@StringRes resId: Int) =
        ReadOnlyProperty<Any, String> { _, _ -> activity.getString(resId) }

    // The strings used for matching in these tests
    private val challengeCategory by composeTestRule.stringResource(R.string.challenge_category_title)
    private val challengeDescription by composeTestRule.stringResource(R.string.challenge_description_title)
    private val challengeTags by composeTestRule.stringResource(R.string.challenge_tags_title)
    private val challengeLanguages by composeTestRule.stringResource(R.string.challenge_languages_title)
    private val challengeRank by composeTestRule.stringResource(R.string.challenge_rank_title)
    private val challengeCreatedBy by composeTestRule.stringResource(R.string.challenge_created_by_title)
    private val challengeApprovedBy by composeTestRule.stringResource(R.string.challenge_approved_by_title)
    private val challengeTotalAttempts by composeTestRule.stringResource(R.string.challenge_total_attempts_title)
    private val challengeTotalCompleted by composeTestRule.stringResource(R.string.challenge_total_completed_title)
    private val challengeTotalStars by composeTestRule.stringResource(R.string.challenge_total_stars_title)
    private val challengeVoteScore by composeTestRule.stringResource(R.string.challenge_vote_score_title)
    private val challengePublishedAt by composeTestRule.stringResource(R.string.challenge_published_at_title)
    private val challengeApprovedAt by composeTestRule.stringResource(R.string.challenge_approved_at_title)

    private val scrollableLayoutTag by composeTestRule.stringResource(R.string.scrollable_layout_tag)
    private val loadingLayoutTag by composeTestRule.stringResource(R.string.loading_layout_tag)
    private val loadingRefreshIndicatorTag by composeTestRule.stringResource(R.string.loading_refresh_indicator_tag)

    @Before
    fun setup() {
        hiltRule.inject()

        composeTestRule.activity.setContent {
            CardDetailsScreen(
                uiState = fakeUiStateNotLoading,
                onEvent = {},
                onBackClick = {},
            )
        }
    }

    @Test
    fun navigate_up_button_exists() {
        composeTestRule.onNodeWithContentDescription("Close").assertExists()
    }

    @Test
    fun challenge_titles_exists() {
        composeTestRule.onNodeWithTag(scrollableLayoutTag)
            .assert(hasScrollAction())

        composeTestRule.onNodeWithText(challengeCategory).assertExists()
        composeTestRule.onNodeWithText(challengeDescription).assertExists()
        composeTestRule.onNodeWithText(challengeTags).assertExists()
        composeTestRule.onNodeWithText(challengeLanguages).assertExists()
        composeTestRule.onNodeWithText(challengeRank).assertExists()
        composeTestRule.onNodeWithText(challengeCreatedBy).assertExists()
        composeTestRule.onNodeWithText(challengeApprovedBy).assertExists()
        composeTestRule.onNodeWithText(challengeTotalAttempts).assertExists()

        composeTestRule
            .onNodeWithTag(scrollableLayoutTag)
            .performScrollToIndex(12)

        composeTestRule.onNodeWithText(challengeTotalCompleted).assertExists()
        composeTestRule.onNodeWithText(challengeTotalStars).assertExists()
        composeTestRule.onNodeWithText(challengeVoteScore).assertExists()
        composeTestRule.onNodeWithText(challengePublishedAt).assertExists()
        composeTestRule.onNodeWithText(challengeApprovedAt).assertExists()
    }

    @Test
    fun challenge_loading_exists() {
        composeTestRule.activity.setContent {
            CardDetailsScreen(
                uiState = fakeUiStateLoading,
                onEvent = {},
                onBackClick = {},
            )
        }
        composeTestRule.onNodeWithTag(loadingLayoutTag).assertExists()
        composeTestRule.onNodeWithTag(loadingRefreshIndicatorTag).assertExists()
    }
}

private val fakeUiChallenge = UIChallengeDetails(
    id = "0",
    name = "Test",
    category = "test category",
    description = "some test description",
    tags = listOf("test", "tags"),
    languages = listOf("test", "languages"),
    rank = UIRank(
        name = "rankName",
        color = "rankColor"
    ),
    createdBy = UICreatedApprovedBy(
        username = "created username",
        url = "created.url.com"
    ),
    approvedBy = UICreatedApprovedBy(
        username = "approvedBy username",
        url = "approvedBy.url.com"
    ),
    totalAttempts = 3,
    totalCompleted = 3,
    totalStars = 3,
    voteScore = 3,
    publishedAt = LocalDateTime.parse(
        "2024-03-12T18:32:36.085Z",
        DateTimeFormatter.ISO_DATE_TIME
    ),
    approvedAt = LocalDateTime.parse(
        "2024-04-12T18:32:36.085Z",
        DateTimeFormatter.ISO_DATE_TIME
    )
)


private val fakeUiStateNotLoading = ChallengeDetailsScreenContract.State(
    isLoading = false,
    challenge = fakeUiChallenge
)

private val fakeUiStateLoading = ChallengeDetailsScreenContract.State(
    isLoading = true,
    challenge = null
)