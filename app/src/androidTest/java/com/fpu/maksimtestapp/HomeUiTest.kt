package com.fpu.maksimtestapp

import androidx.activity.compose.setContent
import androidx.annotation.StringRes
import androidx.compose.ui.test.junit4.AndroidComposeTestRule
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.paging.PagingData
import androidx.test.espresso.Espresso
import androidx.test.espresso.NoActivityResumedException
import com.fpu.maksimtestapp.presentation.features.home.HomeScreen
import com.fpu.maksimtestapp.presentation.features.home.HomeScreenContract
import com.fpu.maksimtestapp.presentation.model.UIChallenge
import dagger.hilt.android.testing.BindValue
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import kotlin.properties.ReadOnlyProperty
import kotlinx.coroutines.flow.flow
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TemporaryFolder

@HiltAndroidTest
class HomeUiTest {

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
    private val loadingLayoutTag by composeTestRule.stringResource(R.string.loading_layout_tag)
    private val loadingRefreshIndicatorTag by composeTestRule.stringResource(R.string.loading_refresh_indicator_tag)
    private val homeScreenText by composeTestRule.stringResource(R.string.home_screen_title)

    @Before
    fun setup() {
        hiltRule.inject()

        composeTestRule.activity.setContent {
            HomeScreen(
                uiState = fakeUiStateNotLoading,
                onEvent = {},
                onNavigateToChallengeDetails = {}
            )
        }
    }

    @Test
    fun top_bar_title_exists() {
        composeTestRule.onNodeWithText(homeScreenText).assertExists()
    }

    @Test(expected = NoActivityResumedException::class)
    fun homeDestination_back_quitsApp() {
        Espresso.pressBack()
    }

    @Test
    fun home_loading_exists() {
        composeTestRule.activity.setContent {
            HomeScreen(
                uiState = fakeUiStateLoading,
                onEvent = {},
                onNavigateToChallengeDetails = {}
            )
        }
        composeTestRule.onNodeWithTag(loadingLayoutTag).assertExists()
        composeTestRule.onNodeWithTag(loadingRefreshIndicatorTag).assertExists()
    }
}

private val challengeList = flow<PagingData<UIChallenge>> {
    listOf(
        UIChallenge(
            id = "id",
            name = "test",
            completedLanguages = listOf("test"),
            completedAt = LocalDateTime.parse(
                "2024-03-12T18:32:36.085Z",
                DateTimeFormatter.ISO_DATE_TIME
            ).format(
                DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm")
            )
        ),
        UIChallenge(
            id = "id",
            name = "test",
            completedLanguages = listOf("test"),
            completedAt = LocalDateTime.parse(
                "2024-03-12T18:32:36.085Z",
                DateTimeFormatter.ISO_DATE_TIME
            ).format(
                DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm")
            )
        ),
        UIChallenge(
            id = "id",
            name = "test",
            completedLanguages = listOf("test"),
            completedAt = LocalDateTime.parse(
                "2024-03-12T18:32:36.085Z",
                DateTimeFormatter.ISO_DATE_TIME
            ).format(
                DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm")
            )
        )
    )
}


private val fakeUiStateNotLoading = HomeScreenContract.State(
    isLoading = false,
    challengeList = challengeList
)

private val fakeUiStateLoading = HomeScreenContract.State(
    isLoading = true,
    challengeList = flow { }
)