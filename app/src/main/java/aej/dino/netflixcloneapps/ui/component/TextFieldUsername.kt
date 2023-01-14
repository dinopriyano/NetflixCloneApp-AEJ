package aej.dino.netflixcloneapps.ui.component

import aej.dino.netflixcloneapps.R
import aej.dino.netflixcloneapps.ui.theme.Gray
import aej.dino.netflixcloneapps.ui.theme.Placeholder
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TextFieldUsername(
    modifier: Modifier = Modifier,
    username: String,
    onValueChange: (String) -> Unit
){
    TextField(
        modifier = modifier.fillMaxWidth(),
        value = username,
        onValueChange = { onValueChange(it) },
        colors = TextFieldDefaults.textFieldColors(
            containerColor = Gray,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            textColor = Color.White
        ),
        label = {
            Text(text = stringResource(R.string.username), color = Placeholder)
        },
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Text
        ),
        shape = RoundedCornerShape(16.dp)
    )
}