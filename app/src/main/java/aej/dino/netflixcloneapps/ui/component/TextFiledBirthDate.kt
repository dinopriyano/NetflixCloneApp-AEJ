package aej.dino.netflixcloneapps.ui.component

import aej.dino.netflixcloneapps.R
import aej.dino.netflixcloneapps.ui.theme.Gray
import aej.dino.netflixcloneapps.ui.theme.Placeholder
import android.app.DatePickerDialog
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TextFieldBirthDate(
    modifier: Modifier = Modifier,
    date: String,
    onValueChange: (String) -> Unit
) {
    val year by remember { mutableStateOf(1999) }
    val month by remember { mutableStateOf(0) }
    val day by remember { mutableStateOf(1) }

    val context = LocalContext.current

    val datePicker = DatePickerDialog(context,
        { _, mYear: Int, mMonth: Int, mDayOfMonth: Int ->
            onValueChange("$mDayOfMonth-${mMonth + 1}-$mYear")
        }, year, month, day
    )

    TextField(
        modifier = modifier.fillMaxWidth(),
        value = date,
        onValueChange = { onValueChange(it) },
        colors = TextFieldDefaults.textFieldColors(
            containerColor = Gray,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            textColor = Color.White
        ),
        label = {
            Text(text = stringResource(R.string.birthdate), color = Placeholder)
        },
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Text
        ),
        trailingIcon = {

            IconButton(onClick = { datePicker.show() }) {
                Icon(
                    imageVector = Icons.Filled.CalendarToday,
                    contentDescription = null,
                    tint = Placeholder
                )
            }
        },
        shape = RoundedCornerShape(16.dp)
    )
}

@Preview
@Composable
private fun PreviewFieldBirthDate() {
    var date by remember { mutableStateOf("") }
    TextFieldBirthDate(date = date, onValueChange = { date = it })
}