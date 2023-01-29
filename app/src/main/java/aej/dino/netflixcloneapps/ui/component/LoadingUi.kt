package aej.dino.netflixcloneapps.ui.component

import aej.dino.netflixcloneapps.ui.theme.RedNetflix
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable fun LoadingUi(
  modifier: Modifier = Modifier
) {
  Box(modifier = modifier, contentAlignment = Alignment.Center) {
    CircularProgressIndicator(
      modifier = Modifier
        .size(48.dp),
      strokeWidth = 4.dp,
      color = RedNetflix
    )
  }
}