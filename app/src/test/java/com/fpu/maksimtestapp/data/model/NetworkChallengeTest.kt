package com.fpu.maksimtestapp.data.model

import com.fpu.maksimtestapp.data.mapper.toChallengeDomain
import com.fpu.maksimtestapp.data.network.model.response.ChallengeDetailsResponse
import com.fpu.maksimtestapp.data.network.model.response.CommonChallenge
import com.fpu.maksimtestapp.data.network.model.response.CreatedApprovedBy
import com.fpu.maksimtestapp.data.network.model.response.Rank
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import junit.framework.TestCase.assertEquals
import org.junit.Test

class NetworkChallengeTest {

    @Test
    fun network_common_challenge_can_be_mapped_to_domain_entity() {
        val networkCommonChallenge = CommonChallenge(
            id = "0",
            name = "Test",
            slug = "test-slug",
            completedLanguages = listOf("test", "strings"),
            completedAt = "2024-03-12T18:32:36.085Z"
        )
        val domainChallenge = networkCommonChallenge.toChallengeDomain()

        assertEquals("0", domainChallenge.id)
        assertEquals("Test", domainChallenge.name)
        assertEquals("test-slug", domainChallenge.slug)
        assertEquals(listOf("test", "strings"), domainChallenge.completedLanguages)
        assertEquals(
            LocalDateTime.parse(
                "2024-03-12T18:32:36.085Z",
                DateTimeFormatter.ISO_DATE_TIME
            ), domainChallenge.completedAt
        )
    }

    @Test
    fun network_challenge_details_can_be_mapped_to_domain_entity() {
        val networkChallengeDetails = ChallengeDetailsResponse(
            id = "0",
            name = "Test",
            slug = "test-slug",
            url = "test.url.com",
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
            publishedAt = "2024-03-12T18:32:36.085Z",
            approvedAt = "2024-04-12T18:32:36.085Z"
        )

        val domainChallengeDetails = networkChallengeDetails.toChallengeDomain()

        assertEquals("0", domainChallengeDetails.id)
        assertEquals("Test", domainChallengeDetails.name)
        assertEquals("test-slug", domainChallengeDetails.slug)
        assertEquals("test category", domainChallengeDetails.category)
        assertEquals("some test description", domainChallengeDetails.description)
        assertEquals(listOf("test", "tags"), domainChallengeDetails.tags)
        assertEquals(listOf("test", "languages"), domainChallengeDetails.languages)
        assertEquals(0, domainChallengeDetails.rank.id)
        assertEquals("rankName", domainChallengeDetails.rank.name)
        assertEquals("rankColor", domainChallengeDetails.rank.color)
        assertEquals("created username", domainChallengeDetails.createdBy.username)
        assertEquals("created.url.com", domainChallengeDetails.createdBy.url)
        assertEquals("approvedBy username", domainChallengeDetails.approvedBy.username)
        assertEquals("approvedBy.url.com", domainChallengeDetails.approvedBy.url)
        assertEquals(3, domainChallengeDetails.totalAttempts)
        assertEquals(3, domainChallengeDetails.totalCompleted)
        assertEquals(3, domainChallengeDetails.totalStars)
        assertEquals(3, domainChallengeDetails.voteScore)
        assertEquals(
            ZonedDateTime.parse(
                "2024-03-12T18:32:36.085Z", DateTimeFormatter.ISO_INSTANT.withZone(
                    ZoneId.systemDefault()
                )
            ).toLocalDateTime(), domainChallengeDetails.publishedAt
        )
        assertEquals(
            ZonedDateTime.parse(
                "2024-04-12T18:32:36.085Z", DateTimeFormatter.ISO_INSTANT.withZone(
                    ZoneId.systemDefault()
                )
            ).toLocalDateTime(), domainChallengeDetails.approvedAt
        )
    }

}