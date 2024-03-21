package com.fpu.maksimtestapp.presentation.model

import com.fpu.maksimtestapp.domain.model.ChallengeDetailsDomain
import com.fpu.maksimtestapp.domain.model.ChallengeDomain
import com.fpu.maksimtestapp.domain.model.CreatedApprovedBy
import com.fpu.maksimtestapp.domain.model.Rank
import com.fpu.maksimtestapp.presentation.mapper.toUIChallenge
import com.fpu.maksimtestapp.presentation.mapper.toUIChallengeDetails
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import junit.framework.TestCase.assertEquals
import org.junit.Test

class DomainChallengeTest {

    @Test
    fun domain_common_challenge_can_be_mapped_to_presentation_entity() {
        val domainCommonChallenge = ChallengeDomain(
            id = "0",
            name = "Test",
            slug = "test-slug",
            completedLanguages = listOf("test", "strings"),
            completedAt = LocalDateTime.parse(
                "2024-03-12T18:32:36.085Z",
                DateTimeFormatter.ISO_DATE_TIME
            )
        )
        val uiChallenge = domainCommonChallenge.toUIChallenge()

        assertEquals("0", uiChallenge.id)
        assertEquals("Test", uiChallenge.name)
        assertEquals(listOf("test", "strings"), uiChallenge.completedLanguages)
        assertEquals(
            LocalDateTime.parse(
                "2024-03-12T18:32:36.085Z",
                DateTimeFormatter.ISO_DATE_TIME
            ).format(
                DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm")
            ),
            uiChallenge.completedAt
        )
    }

    @Test
    fun domain_challenge_details_can_be_mapped_to_presentation_entity() {
        val domainChallengeDetails = ChallengeDetailsDomain(
            id = "0",
            name = "Test",
            slug = "test-slug",
            category = "test category",
            description = "some test description",
            tags = listOf("test", "tags"),
            languages = listOf("test", "languages"),
            rank = Rank(
                id = 0,
                name = "rankName",
                color = "rankColor"
            ),
            createdBy = CreatedApprovedBy(
                username = "created username",
                url = "created.url.com"
            ),
            approvedBy = CreatedApprovedBy(
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

        val uiChallengeDetails = domainChallengeDetails.toUIChallengeDetails()

        assertEquals("0", uiChallengeDetails.id)
        assertEquals("Test", uiChallengeDetails.name)
        assertEquals("test category", uiChallengeDetails.category)
        assertEquals("some test description", uiChallengeDetails.description)
        assertEquals(listOf("test", "tags"), uiChallengeDetails.tags)
        assertEquals(listOf("test", "languages"), uiChallengeDetails.languages)
        assertEquals("rankName", uiChallengeDetails.rank.name)
        assertEquals("rankColor", uiChallengeDetails.rank.color)
        assertEquals("created username", uiChallengeDetails.createdBy.username)
        assertEquals("created.url.com", uiChallengeDetails.createdBy.url)
        assertEquals("approvedBy username", uiChallengeDetails.approvedBy.username)
        assertEquals("approvedBy.url.com", uiChallengeDetails.approvedBy.url)
        assertEquals(3, uiChallengeDetails.totalAttempts)
        assertEquals(3, uiChallengeDetails.totalCompleted)
        assertEquals(3, uiChallengeDetails.totalStars)
        assertEquals(3, uiChallengeDetails.voteScore)
        assertEquals(
            LocalDateTime.parse(
                "2024-03-12T18:32:36.085Z",
                DateTimeFormatter.ISO_DATE_TIME
            ), uiChallengeDetails.publishedAt
        )
        assertEquals(
            LocalDateTime.parse(
                "2024-04-12T18:32:36.085Z",
                DateTimeFormatter.ISO_DATE_TIME
            ), uiChallengeDetails.approvedAt
        )
    }

}