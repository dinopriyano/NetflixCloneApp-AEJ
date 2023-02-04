package aej.dino.netflixcloneapps.core.data.remote.network

import aej.dino.netflixcloneapps.core.data.remote.request.LoginRequest
import aej.dino.netflixcloneapps.core.data.remote.request.RegisterRequest
import aej.dino.netflixcloneapps.core.data.remote.response.ListMovieResponse
import aej.dino.netflixcloneapps.core.data.remote.response.LoginResponse
import aej.dino.netflixcloneapps.core.data.remote.response.MovieResponse
import aej.dino.netflixcloneapps.core.data.remote.response.RegisterResponse
import aej.dino.netflixcloneapps.core.data.remote.response.UpdateResponse
import aej.dino.netflixcloneapps.core.data.remote.response.WebResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieService {

    @GET("movie/now_playing")
    suspend fun getNowPlayingMovie(
        @Query("api_key") apiKey: String = "0a597bad68c0b95d5fab612cff9d8891",
        @Query("language") language: String = "en-US"
    ): ListMovieResponse

    @GET("movie/popular")
    suspend fun getPopularMovie(
        @Query("api_key") apiKey: String = "0a597bad68c0b95d5fab612cff9d8891",
        @Query("language") language: String = "en-US"
    ): ListMovieResponse

    @GET("movie/upcoming")
    suspend fun getUpcomingMovie(
        @Query("api_key") apiKey: String = "0a597bad68c0b95d5fab612cff9d8891",
        @Query("language") language: String = "en-US"
    ): ListMovieResponse

    @GET("movie/{movieId}")
    suspend fun getMovieDetail(
        @Path("movieId") movieId: String,
        @Query("api_key") apiKey: String = "0a597bad68c0b95d5fab612cff9d8891"
    ): MovieResponse

    @POST("v1/user/register")
    suspend fun register(
        @Body register: RegisterRequest
    ): WebResponse<RegisterResponse>

    @POST("v1/user/login")
    suspend fun login(
        @Body login: LoginRequest
    ): WebResponse<LoginResponse>

    @PUT("v1/user")
    suspend fun updateUser(
        @Body register: RegisterRequest
    ): WebResponse<UpdateResponse>

}