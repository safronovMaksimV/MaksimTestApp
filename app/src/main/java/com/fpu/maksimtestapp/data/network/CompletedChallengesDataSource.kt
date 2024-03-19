package com.fpu.maksimtestapp.data.network

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.fpu.maksimtestapp.domain.usecase.GetCompletedChallengesUseCase
import com.fpu.maksimtestapp.presentation.mapper.toUIChallenge
import com.fpu.maksimtestapp.presentation.model.UIChallenge
import java.io.IOException

private const val STARTING_PAGE_INDEX = 0

class CompletedChallengesDataSource constructor(
    private val user: String,
    private val getCompletedChallengesUseCase: GetCompletedChallengesUseCase,
) : PagingSource<Int, UIChallenge>() {
    override fun getRefreshKey(state: PagingState<Int, UIChallenge>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, UIChallenge> {
        val pageIndex = params.key ?: STARTING_PAGE_INDEX

        return try {
            val challenges = getCompletedChallengesUseCase.getCompletedChallenges(
                user,
                page = pageIndex.toString(),
            )
            val nextKey = if (challenges.isEmpty()) {
                null
            } else {
                pageIndex + 1
            }
            LoadResult.Page(
                data = challenges.map { it.toUIChallenge() },
                prevKey = null,
                nextKey = nextKey,
            )
        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: Exception) {
            return LoadResult.Error(exception)
        }
    }
}