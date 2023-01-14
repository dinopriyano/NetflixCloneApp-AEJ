package aej.dino.netflixcloneapps.ui.component

import aej.dino.netflixcloneapps.ui.theme.Placeholder
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun GhostButton(
    modifier: Modifier = Modifier,
    text: String,
    onClick: () -> Unit
) {
    TextButton(
        modifier = modifier.fillMaxWidth(),
        colors = ButtonDefaults.textButtonColors(
            contentColor = Placeholder
        ),
        onClick = onClick
    ) {
        Text(text = text)
    }
}