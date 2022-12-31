package aej.dino.netflixcloneapps

import aej.dino.netflixcloneapps.data.AppMovieContainer
import aej.dino.netflixcloneapps.data.DefaultAppMovieContainer
import android.app.Application

class MovieApplication : Application() {
    lateinit var appMovieContainer: AppMovieContainer
    override fun onCreate() {
        super.onCreate()
        appMovieContainer = DefaultAppMovieContainer()
    }
}