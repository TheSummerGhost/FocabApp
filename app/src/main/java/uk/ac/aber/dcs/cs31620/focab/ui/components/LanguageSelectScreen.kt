package uk.ac.aber.dcs.cs31620.focab.ui.components

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import uk.ac.aber.dcs.cs31620.focab.R
import uk.ac.aber.dcs.cs31620.focab.model.LanguageViewModel
import uk.ac.aber.dcs.cs31620.focab.model.LanguagesLifecycleHandler
import uk.ac.aber.dcs.cs31620.focab.model.TranslationsViewModel
//import uk.ac.aber.dcs.cs31620.focab.model.LanguageViewModel
//import uk.ac.aber.dcs.cs31620.focab.model.LanguagesLifecycleHandler
import uk.ac.aber.dcs.cs31620.focab.ui.navigation.Screen
import uk.ac.aber.dcs.cs31620.focab.ui.theme.FocabTheme

/**
 * Composable function that generates the language selection screen.
 *
 * @param navController NavController for navigating between screens in the app.
 *
 */

@Composable
fun LanguageSelectScreen(navController: NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.background)
    ) {
        LanguageSelectScreenContent(navController)
    }
}

/**
 * Composable function that generates the content of the language selection screen.
 *
 * @param navController NavController for navigating between screens in the app.
 * @param languageViewModel ViewModel for the language selection screen. Default value is the default view model.
 */

@Composable
fun LanguageSelectScreenContent(
    navController: NavHostController,
    languageViewModel: LanguageViewModel = viewModel(),
) {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = CenterHorizontally
    ) {

        LanguagesLifecycleHandler {
            if (it == Lifecycle.Event.ON_START) {
                languageViewModel.onStart()
            } else if (it == Lifecycle.Event.ON_PAUSE) {
                languageViewModel.onPause()
            }
        }

        Text(text = stringResource(R.string.app_name),
            textAlign = TextAlign.Center,
            fontSize = 70.sp,
            color = MaterialTheme.colorScheme.onSurface)

        Spacer(modifier = Modifier.height(20.dp))


        val previousFirstLanguage = languageViewModel.cachedFirstLanguage
        val previousSecondLanguage = languageViewModel.cachedSecondLanguage

        TextInput(
            text = languageViewModel.firstLanguageText,
            onValueChange = {
                    languageViewModel.firstLanguageText = it
            },
            placeHolder = previousFirstLanguage
        )

        TextInput(text = languageViewModel.secondLanguageText,
            onValueChange = {
                    languageViewModel.secondLanguageText = it
            },
            placeHolder = previousSecondLanguage
        )

        Spacer(modifier = Modifier.height(20.dp))

        Box(modifier = Modifier
            .align(CenterHorizontally)
            .height(50.dp)
            .width(150.dp)
        ) {
            var changed = false
            var duplicateLanguage = false
            if (
                languageViewModel.firstLanguageText != previousFirstLanguage || languageViewModel.secondLanguageText != previousSecondLanguage) {
                changed = true
            }
            if (languageViewModel.firstLanguageText == languageViewModel.secondLanguageText) {
                duplicateLanguage = true
            }
            Log.d("changed after if", changed.toString())
            ContinueButton(navController = navController, changed, duplicateLanguage)
        }
    }
}

/**
 * Composable function that generates a text input field.
 *
 * @param text Text value for the text input field.
 * @param placeHolder Placeholder text for the text input field.
 * @param onValueChange Callback function that is triggered when the text in the text input field changes.
 */

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TextInput(
    text: String,
    placeHolder : String,
    onValueChange: (String) -> Unit = {},
) {
    OutlinedTextField(
        value = text.replaceFirstChar { it.uppercase() },
        onValueChange = onValueChange,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 50.dp),
        placeholder = { Text(placeHolder) }
    )
}

/**
 * Composable function that generates a "continue" button.
 *
 * @param navController NavController for navigating between screens in the app.
 * @param changed Flag that indicates whether the language selection has changed.
 * @param translationsViewModel ViewModel for the translations screen. Default value is the default view model.
 */

@Composable
fun ContinueButton(
    navController: NavHostController,
    changed : Boolean,
    duplicateLanguage : Boolean,
    translationsViewModel: TranslationsViewModel = viewModel(),
) {
    Button(
        modifier = Modifier.fillMaxSize(),
        enabled = !duplicateLanguage,
        onClick = {
            Log.d("TranslationDBBeforeChange", translationsViewModel.translationList.toString())
            if (changed) {
                translationsViewModel.deleteAllTranslations()
            }

            navController.navigate(Screen.Translations.route)
            Log.d("TranslationDBAfterChange", translationsViewModel.translationList.toString())
        },
    ) {
        Text("Continue", textAlign = TextAlign.Center)
    }
}

@Composable
@Preview
fun PreviewLanguageSelect() {
    FocabTheme() {
        val navController = rememberNavController()
        LanguageSelectScreen(navController)
    }
}

