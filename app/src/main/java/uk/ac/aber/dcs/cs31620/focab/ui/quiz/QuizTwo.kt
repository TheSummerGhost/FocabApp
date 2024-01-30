package uk.ac.aber.dcs.cs31620.focab.ui.quiz

import android.util.Log
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import uk.ac.aber.dcs.cs31620.focab.model.TranslationsViewModel
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import uk.ac.aber.dcs.cs31620.focab.model.Translation
import uk.ac.aber.dcs.cs31620.focab.ui.navigation.Screen
import uk.ac.aber.dcs.cs31620.focab.ui.theme.FocabTheme
import java.util.*

const val quizTwo = "Quiz Two"

/**
*A composable function that displays the top-level screen for the second quiz, which is a list of questions.
*When the user clicks on a question, they are taken to the QuizTwoQuestion screen to answer the question.
*@param navController The navigation controller for navigating to other screens.
*@param translationsViewModel The view model for the translations screen.
 */

@Composable
fun QuizTwoTopLevel(
    navController: NavHostController,
    translationsViewModel: TranslationsViewModel = viewModel(),
) {
    val translationsList by translationsViewModel.quizOneTranslationList.observeAsState()

    Surface(
    ) {
        QuizTwo(
            translationsList = translationsList,
            navController
        )
    }
}

/**
*
*A composable function that displays a list of questions for the second quiz. When the user clicks on a question,
*they are taken to the QuizTwoQuestion screen to answer the question.
*@param translationsList The list of translations to use for the questions in the quiz.
*@param navController The navigation controller for navigating to other screens.
 */

@Composable
fun QuizTwo(
    translationsList: List<Translation>?,
    navController: NavHostController,
) {
    GenerateQuestion(translationsList, navController, Screen.QuizTwoQuestion.route)
}

