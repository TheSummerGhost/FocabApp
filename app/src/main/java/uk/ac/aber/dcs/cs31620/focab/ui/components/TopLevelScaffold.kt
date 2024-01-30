package uk.ac.aber.dcs.cs31620.focab.ui.components

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.CoroutineScope
import uk.ac.aber.dcs.cs31620.focab.ui.theme.FocabTheme

/**
 * Composable function that generates a top-level scaffold, containing a navigation bar and a floating action button.
 *
 * @param navController NavController for navigating between screens in the app.
 * @param floatingActionButton Composable function representing the floating action button.
 * @param snackbarContent Composable function that displays the snackbar content.
 * @param coroutineScope CoroutineScope for the composable function.
 * @param snackbarHostState State object for the snackbar host.
 * @param pageContent Composable function that generates the main content of the scaffold.
 */

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopLevelScaffold(
    navController : NavHostController,
    floatingActionButton: @Composable () -> Unit = {},
    snackbarContent: @Composable (SnackbarData) -> Unit = {},
    coroutineScope: CoroutineScope,
    snackbarHostState : SnackbarHostState? = null,
    pageContent: @Composable (innerPadding : PaddingValues) -> Unit = {},
) {
    Scaffold (
        bottomBar = { MainPageNavigationBar(navController) },
        content = { innerPadding ->
            pageContent(innerPadding)
        },
        floatingActionButton = floatingActionButton,
        snackbarHost = {
            snackbarHostState?.let {
                snackbarHostState?.let {
                    SnackbarHost(hostState = snackbarHostState) { data ->
                         snackbarContent(data)
                    }
                }
            }
        }
    )
}

@Preview
@Composable
fun ShowTopLevelScaffold() {
    FocabTheme() {
        val navController = rememberNavController()
//        TopLevelScaffold(navController)
    }
}