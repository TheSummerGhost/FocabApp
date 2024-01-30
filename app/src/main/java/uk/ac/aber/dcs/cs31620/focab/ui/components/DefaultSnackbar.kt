package uk.ac.aber.dcs.cs31620.focab.ui.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarData
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

/**
 * Composable function that generates a snackbar with a message and an optional action.
 *
 * @param data SnackbarData object that holds the message and action label for the snackbar.
 * @param modifier Modifier to be applied to the snackbar.
 * @param onDismiss Callback function that is triggered when the snackbar is dismissed.
 */

@Composable
fun DefaultSnackbar(
    data: SnackbarData,
    modifier: Modifier = Modifier,
    onDismiss: () -> Unit
) {
    Snackbar(
        modifier = Modifier.padding(5.dp),
        content = {
            Text(text = data.visuals.message)
        },
        action = {
            data.visuals.actionLabel?.let {
                actionLabel ->
                TextButton(
                    onClick = {
                        onDismiss()
                    }
                ) {
                    Text(text = actionLabel)
                }
            }
        }
    )
}