package aej.dino.netflixcloneapps.ui.component

import aej.dino.netflixcloneapps.R
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.*
import androidx.compose.ui.res.*
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable fun ErrorUi(
  message: String,
  @DrawableRes imageRes: Int? = null,
  onButtonClick: () -> Unit,
  modifier: Modifier = Modifier.fillMaxSize(),
  title: String = stringResource(R.string.something_error_title),
  buttonText: String = stringResource(R.string.try_again)
) {
  Column(
    modifier = modifier,
    verticalArrangement = Arrangement.Center,
    horizontalAlignment = Alignment.CenterHorizontally
  ) {
    if(imageRes != null) {
      Image(painter = painterResource(id = imageRes), contentDescription = null)
      Spacer(modifier = Modifier.height(16.dp))
    }
    Text(
      modifier = Modifier
        .fillMaxWidth()
        .align(Alignment.CenterHorizontally),
      text = title,
      style = MaterialTheme.typography.titleMedium.copy(
        textAlign = TextAlign.Center
      )
    )
    Spacer(modifier = Modifier.height(4.dp))
    Text(
      modifier = Modifier.fillMaxWidth(),
      text = message,
      style = MaterialTheme.typography.bodyMedium.copy(
        textAlign = TextAlign.Center, color = MaterialTheme.colorScheme.onSurface
      )
    )
    Spacer(modifier = Modifier.height(16.dp))
    Button(onClick = { onButtonClick.invoke() }) {
      Text(text = buttonText, color = MaterialTheme.colorScheme.surface)
    }
  }
}