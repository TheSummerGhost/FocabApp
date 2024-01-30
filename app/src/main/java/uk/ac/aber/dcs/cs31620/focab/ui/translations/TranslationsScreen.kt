package uk.ac.aber.dcs.cs31620.focab.ui.translations

import android.app.Application
import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import uk.ac.aber.dcs.cs31620.focab.R
import uk.ac.aber.dcs.cs31620.focab.datasource.FocabRepo
import uk.ac.aber.dcs.cs31620.focab.model.Translation
import uk.ac.aber.dcs.cs31620.focab.model.TranslationSearch
import uk.ac.aber.dcs.cs31620.focab.model.TranslationsViewModel
import uk.ac.aber.dcs.cs31620.focab.ui.components.DefaultSnackbar
import uk.ac.aber.dcs.cs31620.focab.ui.components.FilterButtons
import uk.ac.aber.dcs.cs31620.focab.ui.components.SearchBar
import uk.ac.aber.dcs.cs31620.focab.ui.components.TopLevelScaffold
import uk.ac.aber.dcs.cs31620.focab.ui.navigation.Screen
import uk.ac.aber.dcs.cs31620.focab.ui.theme.FocabTheme

const val TranslationsScreen = "TranslationsScreen"

/**
 * A composable function that displays a screen for displaying and searching translations.
 *
 * @param navController The navigation controller for navigating to other screens.
 * @param translationsViewModel The view model for interacting with the translations repository.
 */

@Composable
fun TranslationsScreen(
    navController: NavHostController,
    translationsViewModel: TranslationsViewModel = viewModel(),
) {

    val translationsList by translationsViewModel.translationList.observeAsState(listOf())

    ScreenContent(
        translationsList = translationsList,
        translationSearch = translationsViewModel.translationSearch,
        updateSearchCriteria = { translationSearch ->
            translationsViewModel.updateTranslationSearch(translationSearch)
        },
        navController = navController
    )

}

/**
 * A composable function that displays the content of the translations screen.
 *
 * @param translationsList The list of translations to be displayed.
 * @param translationSearch The search criteria for filtering the translations.
 * @param updateSearchCriteria A function for updating the search criteria.
 * @param navController The navigation controller for navigating to other screens.
 */

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScreenContent(
    translationsList: List<Translation> = listOf(),
    translationSearch: TranslationSearch = TranslationSearch(),
    updateSearchCriteria: (TranslationSearch) -> Unit = {},
    navController: NavHostController,
) {
    val coroutineScope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }

    TopLevelScaffold(
        navController = navController,
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    navController.navigate(Screen.AddTranslation.route)
                },
            ) {
                Icon(
                    imageVector = Icons.Filled.Add,
                    contentDescription = stringResource(R.string.add_translation)
                )
            }
        },
        snackbarContent = { data ->
            DefaultSnackbar(data = data,
                modifier = Modifier.padding(bottom = 4.dp),
                onDismiss = { data.dismiss() }
            )
        },
        coroutineScope = coroutineScope,
        snackbarHostState = snackbarHostState

    ) { innerPadding ->
        Surface(modifier = Modifier
            .padding(innerPadding)
            .fillMaxSize()
        )
        {
            Column(modifier = Modifier
                .fillMaxWidth()
                .padding(top = 50.dp), horizontalAlignment = CenterHorizontally) {

                /**
                 * A composable function that displays a search bar for searching translations.
                 *
                 * @param modifier Modifier to be applied to the search bar.
                 * @param translationSearch The search criteria for filtering translations.
                 * @param updateSearchCriteria A function for updating the search criteria.
                 */

                SearchBar(modifier = Modifier
                    .fillMaxWidth()
                    .align(CenterHorizontally),
                    translationSearch = translationSearch) {
                    updateSearchCriteria(it)
                }

                Spacer(modifier = Modifier.height(20.dp))

                /**
                 * A composable function that displays a row of buttons for filtering translations.
                 *
                 * @param modifier Modifier to be applied to the buttons.
                 * @param translationSearch The search criteria for filtering translations.
                 * @param updateSearchCriteria A function for updating the search criteria.
                 */

                FilterButtons(modifier = Modifier
                    .size(60.dp),
                    translationSearch = translationSearch) {
                    updateSearchCriteria(it)
                }

                Spacer(modifier = Modifier.height(50.dp))

                LazyColumn(
//                contentPadding = PaddingValues(vertical = 100.dp)
                ) {
                    items(items = translationsList) { translation ->
                        Log.i(TranslationsScreen, translationsList.toString())
                        TranslationTabPair(translation = translation)
                        Spacer(modifier = Modifier.height(20.dp))
                    }

                }
            }
        }
    }
}



@Preview
@Composable
fun PreviewTranslationsScreen() {
    FocabTheme() {
        val navController = rememberNavController()
//        TranslationsScreen(navController)
    }

}
