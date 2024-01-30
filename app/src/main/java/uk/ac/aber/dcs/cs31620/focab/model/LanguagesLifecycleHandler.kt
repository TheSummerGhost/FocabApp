package uk.ac.aber.dcs.cs31620.focab.model

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver

/**
 * Registers a LifecycleEventObserver to the current LocalLifecycleOwner.
 *
 * When the provided `onEvent` function is called, the given `Lifecycle.Event` will be passed as an argument.
 *
 * The LifecycleEventObserver will be removed when the effect is disposed.
 *
 * @param onEvent a function to be called when a Lifecycle.Event occurs
 */

@Composable
fun LanguagesLifecycleHandler (
    onEvent: (Lifecycle.Event) -> Unit
) {
    val eventHandler by rememberUpdatedState(newValue = onEvent)
    val lifecycleOwner by rememberUpdatedState(newValue = LocalLifecycleOwner.current)

    DisposableEffect(key1 = lifecycleOwner) {
        val observer = LifecycleEventObserver {_, event ->
            eventHandler(event)
        }
        lifecycleOwner.lifecycle.addObserver(observer)

        onDispose { lifecycleOwner.lifecycle.removeObserver(observer) }
    }
}