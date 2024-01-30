package uk.ac.aber.dcs.cs31620.focab.ui.translations

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHost
import androidx.navigation.NavHostController
import uk.ac.aber.dcs.cs31620.focab.R
import uk.ac.aber.dcs.cs31620.focab.model.LanguageViewModel
import uk.ac.aber.dcs.cs31620.focab.model.Translation
import uk.ac.aber.dcs.cs31620.focab.model.TranslationsViewModel
import uk.ac.aber.dcs.cs31620.focab.model.translationsViewModel
import uk.ac.aber.dcs.cs31620.focab.ui.theme.FocabTheme

/**
 * A composable function that displays a screen for adding translations.
 *
 * @param navController The navigation controller for navigating to other screens.
 * @param translationsViewModel The view model for interacting with the translations repository.
 */

@Composable
fun AddTranslationScreen(
    navController: NavHostController,
    translationsViewModel: TranslationsViewModel = viewModel(),
) {
    AddTranslationScreenContent(
        navController = navController,
        insertTranslation = { newTranslation ->
            translationsViewModel.insertTranslation(newTranslation)
        }
    )
}

/**
 * A composable function that displays the content for the add translation screen.
 *
 * @param navController The navigation controller for navigating to other screens.
 * @param insertTranslation A function for inserting a new translation into the repository.
 * @param languageViewModel The view model for interacting with the language repository.
 */

@Composable
fun AddTranslationScreenContent(
    navController: NavHostController,
    insertTranslation: (Translation) -> Unit = {},
    languageViewModel: LanguageViewModel = viewModel(),
) {
    var firstWord by rememberSaveable {
        mutableStateOf("")
    }

    var secondWord by rememberSaveable {
        mutableStateOf("")
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.background)
            .padding(all = 10.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    )
    {
        ConstraintLayout(
            modifier = Modifier.fillMaxSize()
        ) {
            val (textBoxes, okayButton) = createRefs()
            Column(
                modifier = Modifier
                    .constrainAs(textBoxes) {
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                    },

                )
            {
                CreateTextField(
                    languageName = if (languageViewModel.firstLanguageText == "")
                        languageViewModel.cachedFirstLanguage
                    else
                        languageViewModel.firstLanguageText,
                    wordToAdd = firstWord,
                    modifier = Modifier,
                    updateWord = {
                        firstWord = it
                    }
                )

                CreateTextField(
                    languageName = languageViewModel.secondLanguageText,
                    wordToAdd = secondWord,
                    modifier = Modifier,
                    updateWord = {
                        secondWord = it
                    }
                )
            }

            Button(
                onClick = {
                    insertTranslation(
                        firstWord = firstWord,
                        secondWord = secondWord,
                        doInsert = { newTranslation ->
                            insertTranslation(newTranslation)
                        }
                    )
                    navController.navigateUp()
                },
                modifier = Modifier
                    .padding(all = 10.dp)
                    .size(100.dp)
                    .constrainAs(okayButton) {
                        start.linkTo(textBoxes.end)
                        top.linkTo(textBoxes.top)
                        bottom.linkTo(textBoxes.bottom)
                    },
                shape = RoundedCornerShape(28.dp),
                contentPadding = PaddingValues(15.dp),
                colors = ButtonDefaults.buttonColors(),
                enabled = firstWord != "" && secondWord != ""
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxSize(),
                    contentAlignment = Alignment.Center
                )
                {
                    Icon(
                        modifier = Modifier
                            .size(60.dp),
                        imageVector = Icons.Default.Add,
                        contentDescription = stringResource(id = R.string.add_new_translation),
                    )
                }
            }
        }
    }
}

/**
*
* Inserts a new translation into the repository.
* @param firstWord The first word in the translation.
* @param secondWord The second word in the translation.
* @param doInsert A function that is called with the new translation when it is inserted.
 */

private fun insertTranslation(
    firstWord: String,
    secondWord: String,
    doInsert: (Translation) -> Unit = {},
) {
    if (firstWord.isNotEmpty() && secondWord.isNotEmpty()) {
        val translation = Translation(
            id = 0,
            firstWord = firstWord,
            secondWord = secondWord
        )
        doInsert(translation)
    }
}

/**
*
*A composable function that displays a text field for adding a word in a specific language.
*@param languageName The name of the language for the text field.
*@param wordToAdd The word to be added.
*@param modifier Modifier to be applied to the text field.
*@param updateWord A function to be called when the text in the text field is updated.
 */

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateTextField(
    languageName: String,
    wordToAdd: String,
    modifier: Modifier,
    updateWord: (String) -> Unit,
) {
    val maxLength = 20
    OutlinedTextField(
        value = wordToAdd.replaceFirstChar { it.uppercase() },
        onValueChange = {
            if (it.length <= maxLength) {
                updateWord(it)
            }
        },
        label = { Text(text = languageName) },
        placeholder = { Text(text = "Enter your word") },
        maxLines = 1
    )
}