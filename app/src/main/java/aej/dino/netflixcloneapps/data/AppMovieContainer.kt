package aej.dino.netflixcloneapps.data

import aej.dino.netflixcloneapps.data.local.LocalDataSource
import aej.dino.netflixcloneapps.data.local.datastore.MovieDataStore
import aej.dino.netflixcloneapps.data.local.room.MovieDatabase
import aej.dino.netflixcloneapps.data.remote.RemoteDataSource
import aej.dino.netflixcloneapps.data.remote.network.MovieService
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
}

class DefaultAppMovieContainer(
    private val context: Context
) : AppMovieContainer {

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

    private val movieDatabase: MovieDatabase by lazy {
        MovieDatabase.getInstance(context)
    }

    private val dataStore: MovieDataStore by lazy {
        MovieDataStore(context)
    }

    override val remoteDataSource: RemoteDataSource by lazy { RemoteDataSource(retrofitService) }

    override val movieRepository: MovieRepository by lazy { MovieRepository(remoteDataSource) }

    override val localDataSource: LocalDataSource by lazy { LocalDataSource(movieDatabase.userDao(), dataStore) }

    override val authRepository: AuthRepository by lazy { AuthRepository(localDataSource) }

}