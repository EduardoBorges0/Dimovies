package com.app.cinedimen.mainScreenTests

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.app.cinedimen.data.model.Dates
import com.app.cinedimen.data.model.NowPlayingModel
import com.app.cinedimen.data.model.Result
import com.app.cinedimen.data.repositoriesImpl.listscreen.RepositoriesImplMainScreen
import com.app.cinedimen.presentation.viewmodel.listscreen.MainScreenViewModel
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

class UpComingUnitTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    private val testDispatcher = StandardTestDispatcher()
    private lateinit var mockRepositories: RepositoriesImplMainScreen
    private lateinit var viewModel: MainScreenViewModel

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        mockRepositories = mockk(relaxed = true)
        viewModel = MainScreenViewModel(mockRepositories)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `test getUpComing success`() = runTest {
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
        val topRated = NowPlayingModel(
            dates = Dates("2025-02-20", "2025-02-19"),
            page = 1,
            results = listOf(results),
            total_pages = 1,
            total_results = 0
        )

        val response = Response.success(topRated)
        coEvery { mockRepositories.getUpComingMovies() } returns response

        // Act
        viewModel.getUpComingMovies()

        advanceUntilIdle()

        // Assert
        assertEquals(topRated, viewModel.upComingMovies.value)
    }
    @Test
    fun `test getUpComing no movies`() = runTest {
        // Arrange
        val topRated = NowPlayingModel(
            dates = Dates("2025-02-20", "2025-02-19"),
            page = 1,
            results = emptyList(),
            total_pages = 1,
            total_results = 0
        )

        val response = Response.success(topRated)
        coEvery { mockRepositories.getUpComingMovies() } returns response

        // Act
        viewModel.getUpComingMovies()
        advanceUntilIdle()

        // Assert
        assertEquals("Sem filmes", viewModel.errorMessage.value)
    }

    @Test
    fun `test getUpComing error`() = runTest {
        // Arrange
        val response = Response.error<NowPlayingModel>(404, "Not Found".toResponseBody())

        coEvery { mockRepositories.getUpComingMovies() } returns response

        // Act
        viewModel.getUpComingMovies( )

        advanceUntilIdle()

        // Assert
        assertEquals(viewModel.errorMessage.value, response.code().toString())
    }
}