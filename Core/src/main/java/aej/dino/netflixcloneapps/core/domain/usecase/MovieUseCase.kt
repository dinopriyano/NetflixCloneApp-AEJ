package aej.dino.netflixcloneapps.core.domain.usecase

import aej.dino.netflixcloneapps.core.data.MovieRepository
import aej.dino.netflixcloneapps.core.domain.model.Movie
import kotlinx.coroutines.flow.Flow

class MovieUseCase(
    private val movieRepository: MovieRepository
): IMovieInteractor {
    override suspend fun getNowPlayingMovie() = movieRepository.getNowPlayingMovie()

    override suspend fun getMovieDetail(id: String) = movieRepository.getMovieDetail(id)
}