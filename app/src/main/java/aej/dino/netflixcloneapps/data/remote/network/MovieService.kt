package aej.dino.netflixcloneapps.data.remote.network

import aej.dino.netflixcloneapps.data.remote.response.ListMovieResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieService {

    @GET("movie/now_playing")
    suspend fun getNowPlayingMovie(
        @Query("api_key") apiKey: String = "0a597bad68c0b95d5fab612cff9d8891",
        @Query("language") language: String = "en-US"
    ): ListMovieResponse

}