package aej.dino.netflixcloneapps

import aej.dino.netflixcloneapps.core.data.AppMovieContainer
import aej.dino.netflixcloneapps.core.data.DefaultAppMovieContainer
import android.app.Application

class MovieApplication : Application() {
    lateinit var appMovieContainer: AppMovieContainer
    override fun onCreate() {
        super.onCreate()
        appMovieContainer = DefaultAppMovieContainer(context = applicationContext)
    }
}