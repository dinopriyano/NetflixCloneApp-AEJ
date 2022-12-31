package aej.dino.netflixcloneapps.data

import aej.dino.netflixcloneapps.data.remote.RemoteDataSource
import aej.dino.netflixcloneapps.data.remote.network.MovieService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

interface AppMovieContainer {
    val remoteDataSource: RemoteDataSource
    val movieRepository: MovieRepository
}

class DefaultAppMovieContainer : AppMovieContainer {

    private val BASE_URL = "https://api.themoviedb.org/3/"

    /**
     * Use the Retrofit builder to build a retrofit object using a kotlinx.serialization converter
     */
    private fun provideRetrofitClient(): OkHttpClient {
        val loggingInterceptor =
            HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
        return OkHttpClient.Builder().addInterceptor(loggingInterceptor).build()
    }

    private val retrofit: Retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BASE_URL)
        .client(provideRetrofitClient())
        .build()

    private val retrofitService: MovieService by lazy {
        retrofit.create(MovieService::class.java)
    }

    override val remoteDataSource: RemoteDataSource by lazy { RemoteDataSource(retrofitService) }

    override val movieRepository: MovieRepository by lazy { MovieRepository(remoteDataSource) }

}