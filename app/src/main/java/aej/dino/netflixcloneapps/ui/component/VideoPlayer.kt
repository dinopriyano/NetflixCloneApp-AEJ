package aej.dino.netflixcloneapps.ui.component

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView

@Composable
fun VideoPlayer(
  videoId: String,
  modifier: Modifier
) {
  AndroidView(
    modifier = modifier,
    factory = {
      YouTubePlayerView(context = it)
    },
    update = {
      it.addYouTubePlayerListener(object: AbstractYouTubePlayerListener() {
        override fun onReady(youTubePlayer: YouTubePlayer) {
          youTubePlayer.loadVideo(videoId, 0f)
        }
      })
    }
  )

}