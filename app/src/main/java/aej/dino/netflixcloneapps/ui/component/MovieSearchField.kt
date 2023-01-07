package aej.dino.netflixcloneapps.ui.component

import aej.dino.netflixcloneapps.ui.theme.NetflixCloneAppsTheme
import aej.dino.netflixcloneapps.ui.theme.RedNetflix
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@ExperimentalMaterial3Api
@Composable
fun MovieSearchField(
  searchValue: String = "",
  onTextChange: (String) -> Unit,
  modifier: Modifier = Modifier
) {
  OutlinedTextField(
    value = searchValue,
    onValueChange = {
      onTextChange.invoke(it)
    },
    modifier = modifier,
    leadingIcon = {
      Icon(imageVector = Icons.Filled.Search, contentDescription = null, tint = Color.Gray)
    },
    shape = RoundedCornerShape(16.dp),
    colors = TextFieldDefaults.textFieldColors(
      unfocusedIndicatorColor = Color.Gray,
      focusedIndicatorColor = MaterialTheme.colorScheme.onSurface,
      containerColor = MaterialTheme.colorScheme.surface
    )
  )
}

@ExperimentalMaterial3Api
@Preview(showBackground = true)
@Composable
fun MovieSearchFieldPreview() {
  var text by remember {
    mutableStateOf("")
  }

  NetflixCloneAppsTheme {
    MovieSearchField(
      text,
      onTextChange = {
        text = it
      },
      modifier = Modifier.fillMaxWidth()
    )
  }
}