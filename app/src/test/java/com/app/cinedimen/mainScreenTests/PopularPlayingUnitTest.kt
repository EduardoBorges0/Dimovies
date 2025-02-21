package com.app.cinedimen.mainScreenTests

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.app.cinedimen.data.model.Dates
import com.app.cinedimen.data.model.NowPlayingModel
import com.app.cinedimen.data.model.Result
import com.app.cinedimen.data.repositories.listscreen.RepositoriesNowPlaying
import com.app.cinedimen.data.repositories.listscreen.RepositoriesPopularMovies
import com.app.cinedimen.presentation.viewmodel.listscreen.NowPlayingViewModel
import com.app.cinedimen.presentation.viewmodel.listscreen.PopularMovieViewModel
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import retrofit2.Response

class PopularPlayingUnitTest {
    @get:Rule
    val rule = InstantTaskExecutorRule()

    private val testDispatcher = StandardTestDispatcher()
    private lateinit var mockRepositories: RepositoriesPopularMovies
    private lateinit var viewModel: PopularMovieViewModel

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        mockRepositories = mockk(relaxed = true)
        viewModel = PopularMovieViewModel(mockRepositories)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `test getPopularMovies success`() = runTest {
        // Arrange
        val results = Result(
            adult = false,
            backdrop_path = "/path_to_backdrop_image.jpg",
            genre_ids = listOf(16, 18, 14),
            id = 12110,
            original_language = "en",
            original_title = "Coraline",
            overview = "Coraline Jones, a young girl, discovers a parallel world where everyone has button eyes and things seem better than her real life. But when the other mother wants to keep her, Coraline must find a way to escape.",
            popularity = 85.0,
            poster_path = "/path_to_poster_image.jpg",
            release_date = "2009-02-06",
            title = "Coraline",
            video = false,
            vote_average = 7.7,
            vote_count = 12000
        )
        val popularMovies = NowPlayingModel(
            dates = Dates("2025-02-20", "2025-02-19"),
            page = 1,
            results = listOf(results),
            total_pages = 1,
            total_results = 0
        )

        val response = Response.success(popularMovies)
        coEvery { mockRepositories.getPopularMovies() } returns response

        // Act
        viewModel.getPopularMovies()

        advanceUntilIdle()

        // Assert
        assertEquals(popularMovies, viewModel.popularMovies.value)
    }
    @Test
    fun `test getPopularMovies no movies`() = runTest {
        // Arrange
        val popularMovies = NowPlayingModel(
            dates = Dates("2025-02-20", "2025-02-19"),
            page = 1,
            results = emptyList(),
            total_pages = 1,
            total_results = 0
        )

        val response = Response.success(popularMovies)
        coEvery { mockRepositories.getPopularMovies() } returns response

        // Act
        viewModel.getPopularMovies()
        advanceUntilIdle()

        // Assert
        assertEquals("Sem filmes", viewModel.errorMessage.value)
    }



    @Test
    fun `test getPopularMovies error`() = runTest {
        // Arrange
        val response = Response.error<NowPlayingModel>(404, "Not Found".toResponseBody())

        coEvery { mockRepositories.getPopularMovies() } returns response

        // Act
        viewModel.getPopularMovies()

        advanceUntilIdle()

        // Assert
        assertEquals(viewModel.errorMessage.value, response.code().toString())
    }

}