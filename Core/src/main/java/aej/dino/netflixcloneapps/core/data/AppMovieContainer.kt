package aej.dino.netflixcloneapps.core.data

import aej.dino.netflixcloneapps.core.data.local.LocalDataSource
import aej.dino.netflixcloneapps.core.data.local.datastore.MovieDataStore
import aej.dino.netflixcloneapps.core.data.local.room.MovieDatabase
import aej.dino.netflixcloneapps.core.data.remote.RemoteDataSource
import aej.dino.netflixcloneapps.core.data.remote.network.MovieService
import aej.dino.netflixcloneapps.core.domain.usecase.AuthUseCase
import aej.dino.netflixcloneapps.core.domain.usecase.MovieUseCase
import android.content.Context
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

interface AppMovieContainer {
    val remoteDataSource: RemoteDataSource
    val movieRepository: MovieRepository
    val localDataSource: LocalDataSource
    val authRepository: AuthRepository
    val movieUseCase: MovieUseCase
    val authUseCase: AuthUseCase
}

class DefaultAppMovieContainer(
    private val context: Context
) : AppMovieContainer {

    private val BASE_URL = "http://54.236.81.227/api/"
    private val BASE_URL_TMDB = "https://api.themoviedb.org/3/"

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

    private val retrofitTmdb: Retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BASE_URL_TMDB)
        .client(provideRetrofitClient())
        .build()

    private val retrofitService: MovieService by lazy {
        retrofit.create(MovieService::class.java)
    }

    private val retrofitTmdbService: MovieService by lazy {
        retrofitTmdb.create(MovieService::class.java)
    }

    private val movieDatabase: MovieDatabase by lazy {
        MovieDatabase.getInstance(context)
    }

    private val dataStore: MovieDataStore by lazy {
        MovieDataStore(context)
    }

    override val remoteDataSource: RemoteDataSource by lazy { RemoteDataSource(retrofitService, retrofitTmdbService) }

    override val localDataSource: LocalDataSource by lazy { LocalDataSource(movieDatabase.userDao(), dataStore) }

    override val movieRepository: MovieRepository by lazy { MovieRepository(remoteDataSource, localDataSource) }

    override val authRepository: AuthRepository by lazy { AuthRepository(localDataSource, remoteDataSource) }

    override val movieUseCase: MovieUseCase by lazy { MovieUseCase(movieRepository) }

    override val authUseCase: AuthUseCase by lazy { AuthUseCase(authRepository) }

}