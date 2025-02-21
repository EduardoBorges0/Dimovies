package com.app.cinedimen.detailsScreenTests

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.app.cinedimen.data.model.Collection
import com.app.cinedimen.data.model.Genre
import com.app.cinedimen.data.model.MoviesDetails
import com.app.cinedimen.data.model.ProductionCompany
import com.app.cinedimen.data.model.ProductionCountry
import com.app.cinedimen.data.model.SpokenLanguage
import com.app.cinedimen.data.repositories.detailscreen.RepositoriesMovieDetails
import com.app.cinedimen.presentation.viewmodel.detailscreen.MovieDetailsViewModel
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

class MovieDetailsUnitTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    private val testDispatcher = StandardTestDispatcher()
    private lateinit var mockRepositories: RepositoriesMovieDetails
    private lateinit var viewModel: MovieDetailsViewModel

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        mockRepositories = mockk(relaxed = true)
        viewModel = MovieDetailsViewModel(mockRepositories)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `test getMoviesDetails success`() = runTest {
        // Arrange
        val movieDetails = MoviesDetails(
            adult = false,
            backdrop_path = "/path_to_backdrop_image.jpg",
            belongs_to_collection = Collection(
                id = 1,
                name = "Coraline Collection",
                poster_path = "/path_to_collection_poster.jpg",
                backdrop_path = "/path_to_collection_backdrop.jpg"
            ),
            budget = 60000000,
            genres = listOf(
                Genre(id = 16, name = "Animação"),
                Genre(id = 14, name = "Fantasia"),
                Genre(id = 18, name = "Drama")
            ),
            homepage = "https://www.coraline.com",
            id = 12110,
            imdb_id = "tt0327597",
            origin_country = listOf("US"),
            original_language = "en",
            original_title = "Coraline",
            overview = "Coraline Jones, a young girl, discovers a parallel world where everyone has button eyes and things seem better than her real life. But when the other mother wants to keep her, Coraline must find a way to escape.",
            popularity = 85.0,
            poster_path = "/path_to_poster_image.jpg",
            production_companies = listOf(
                ProductionCompany(id = 1, logo_path = "/path_to_logo.jpg", name = "Laika", origin_country = "US")
            ),
            production_countries = listOf(
                ProductionCountry(iso_3166_1 = "US", name = "United States")
            ),
            release_date = "2009-02-06",
            revenue = 124600000,
            runtime = 100,
            spoken_languages = listOf(
                SpokenLanguage(english_name = "English", iso_639_1 = "en", name = "English"),
                SpokenLanguage(english_name = "French", iso_639_1 = "fr", name = "French")
            ),
            status = "Released",
            tagline = "Be careful what you wish for.",
            title = "Coraline",
            video = false,
            vote_average = 7.7,
            vote_count = 12000
        )
        val response = Response.success(movieDetails)
        coEvery { mockRepositories.getMovieDetails(1) } returns response

        // Act
        viewModel.getMovieDetails(1)

        advanceUntilIdle()

        // Assert
        assertEquals(movieDetails, viewModel.movieDetails.value)
    }

    @Test
    fun `test getMoviesDetails error`() = runTest {
        // Arrange
        val response = Response.error<MoviesDetails>(404, "Not Found".toResponseBody())

        coEvery { mockRepositories.getMovieDetails(1) } returns response

        // Act
        viewModel.getMovieDetails(1)

        advanceUntilIdle()

        // Assert
        assertEquals("Filme não encontrado", viewModel.errorMessage.value)
    }

}