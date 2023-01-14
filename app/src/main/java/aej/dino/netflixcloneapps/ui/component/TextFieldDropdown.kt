package aej.dino.netflixcloneapps.ui.component

import aej.dino.netflixcloneapps.ui.theme.Gray
import aej.dino.netflixcloneapps.ui.theme.Placeholder
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
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
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.toSize

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TextFieldDropdown(
    modifier: Modifier = Modifier,
    text: String,
    label: String,
    itemsDropdown: List<String>,
    onValueChange: (String) -> Unit,
) {
    var expanded by remember { mutableStateOf(false) }
    var rowSize by remember { mutableStateOf(Size.Zero) }

    Box(modifier = modifier
        .fillMaxWidth()
        .onGloballyPositioned { layoutCoordinates ->
            rowSize = layoutCoordinates.size.toSize()
        }) {
        TextField(
            modifier = modifier.fillMaxWidth(),
            value = text,
            onValueChange = { onValueChange(it) },
            colors = TextFieldDefaults.textFieldColors(
                containerColor = Gray,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                textColor = Color.White
            ),
            label = { Text(text = label, color = Placeholder) },
            trailingIcon = {
                IconButton(onClick = { expanded = true }) {
                    Icon(
                        imageVector = Icons.Filled.ArrowDropDown,
                        contentDescription = null,
                        tint = Placeholder
                    )
                }
            },
            shape = RoundedCornerShape(16.dp)
        )
        DropdownMenu(
            modifier = modifier
                .background(Gray)
                .width(with(LocalDensity.current) { rowSize.width.toDp() }),
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            itemsDropdown.forEach {
                DropdownMenuItem(
                    modifier = modifier,
                    onClick = {
                        onValueChange(it)
                        expanded = false
                    },
                    text = {
                        Text(text = it, color = Color.White)
                    }
                )
            }
        }
    }
}

@Preview
@Composable
private fun PreviewTextFieldDropdown() {
    var text by remember { mutableStateOf("") }
    val list = listOf("Male", "Female")
    TextFieldDropdown(
        text = text,
        label = "Gender",
        itemsDropdown = list,
        onValueChange = { text = it })
}