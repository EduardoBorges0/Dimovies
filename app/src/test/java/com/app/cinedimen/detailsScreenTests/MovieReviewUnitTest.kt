package com.app.cinedimen.detailsScreenTests

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.app.cinedimen.data.model.AuthorDetails
import com.app.cinedimen.data.model.Collection
import com.app.cinedimen.data.model.Genre
import com.app.cinedimen.data.model.MovieReviewModel
import com.app.cinedimen.data.model.MovieReviewsResponse
import com.app.cinedimen.data.model.MoviesDetails
import com.app.cinedimen.data.model.ProductionCompany
import com.app.cinedimen.data.model.ProductionCountry
import com.app.cinedimen.data.model.SpokenLanguage
import com.app.cinedimen.data.repositories.detailscreen.RepositoriesMovieDetails
import com.app.cinedimen.data.repositories.detailscreen.RepositoriesMovieReview
import com.app.cinedimen.presentation.viewmodel.detailscreen.MovieDetailsViewModel
import com.app.cinedimen.presentation.viewmodel.detailscreen.MovieReviewViewModel
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

class MovieReviewUnitTest {
    @get:Rule
    val rule = InstantTaskExecutorRule()

    private val testDispatcher = StandardTestDispatcher()
    private lateinit var mockRepositories: RepositoriesMovieReview
    private lateinit var viewModel: MovieReviewViewModel

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        mockRepositories = mockk(relaxed = true)
        viewModel = MovieReviewViewModel(mockRepositories)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `test getMovieReview success`() = runTest {
        // Arrange
        val review = MovieReviewsResponse(
            id = 12110,
            page = 1,
            results = listOf(
                MovieReviewModel(
                    author = "JohnDoe",
                    authorDetails = AuthorDetails(
                        name = "John Doe",
                        username = "johndoe123",
                        avatarPath = "/path_to_avatar.jpg",
                        rating = 8 // Avaliação do usuário para o filme
                    ),
                    content = "Coraline é uma obra-prima da animação stop-motion! A história é envolvente, os visuais são incríveis e a trilha sonora complementa perfeitamente a atmosfera do filme.",
                    createdAt = "2024-02-20T10:30:00Z",
                    id = "review_12345",
                    updatedAt = "2024-02-21T08:00:00Z",
                    url = "https://www.example.com/review_12345"
                )
            ),
            total_pages = 1, // Apenas uma página de reviews nesta resposta fictícia
            total_results = 1 // Apenas um review incluído
        )

        val response = Response.success(review)
        coEvery { mockRepositories.getMovieReviews(12110) } returns response

        // Act
        viewModel.getMovieReview(12110)

        advanceUntilIdle()

        // Assert
        assertEquals(review, viewModel.movieReview.value)
    }

    @Test
    fun `test getMovieReview error`() = runTest {
        // Arrange
        val response = Response.error<MovieReviewsResponse>(404, "Not Found".toResponseBody())

        coEvery { mockRepositories.getMovieReviews(12110) } returns response

        // Act
        viewModel.getMovieReview(12110)

        advanceUntilIdle()

        // Assert
        assertEquals("Sem Comentários", viewModel.errorMessage.value)
    }
}