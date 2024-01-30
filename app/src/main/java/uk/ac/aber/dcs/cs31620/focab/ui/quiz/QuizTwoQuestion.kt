package uk.ac.aber.dcs.cs31620.focab.ui.quiz

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import uk.ac.aber.dcs.cs31620.focab.R
import uk.ac.aber.dcs.cs31620.focab.model.Translation
import uk.ac.aber.dcs.cs31620.focab.ui.navigation.Screen
import uk.ac.aber.dcs.cs31620.focab.ui.theme.md_theme_dark_onSurface
import uk.ac.aber.dcs.cs31620.focab.ui.theme.md_theme_light_onSurface

var questionTwoNumber = 0

/**
 * Displays a quiz question, including a prompt to type the correct translation and a "Confirm" button to submit the answer.
 * If the answer is correct, increments the score and the current question number. If the answer is incorrect,
 * only increments the current question number.
 * If the current question number is equal to the total number of questions, displays the score.
 *
 * @param navController the navigation controller for the current screen
 */

@Composable
fun QuizTwoQuestion(
    navController: NavHostController,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.background),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center) {

        Log.d("QuestionNumber", questionTwoNumber.toString())

        val questionList =
            navController.previousBackStackEntry?.savedStateHandle?.get<List<Translation>>("questionList")
        if (questionList == null) {
        } else {
            Log.d("QuiTwoQuestion", questionList.toString())
            if ((questionTwoNumber) == questionList.size) {
                Text("Your score is $quizTwoScore out of $questionTwoNumber",
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center,
                    fontSize = 30.sp,
                    color = MaterialTheme.colorScheme.onBackground)
            } else {
                PoseQuestionTwo(
                    questionList[questionTwoNumber].secondWord,
                    questionList[questionTwoNumber].firstWord,
                    navController)
            }
        }
    }
}

/**
 * A composable function that displays the content of the second quiz screen, including a text field for
 * the user to type their translation and a button to confirm their answer.
 *
 * @param navController The navigation controller for navigating to other screens.
 * @param originalTranslation The original translation to be translated by the user.
 * @param firstWord The first word of the translation pair.
 */

@Composable
fun PoseQuestionTwo(
    originalTranslation: String,
    firstWord: String,
    navController: NavHostController,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.background),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    )
    {
        Text("Type the correct Translation",
            fontSize = 30.sp,
            color = MaterialTheme.colorScheme.onBackground)
        Spacer(modifier = Modifier.height(100.dp))
        Box(modifier = Modifier
            .height(50.dp)
            .width(250.dp)
        ) {
            Image(painter = painterResource(id = R.drawable.focabtableft),
                contentDescription = stringResource(id = R.string.focab_left_tab),
                modifier = Modifier.fillMaxSize())
            Text(
                firstWord,
                modifier = Modifier.align(Alignment.Center),
                color = md_theme_dark_onSurface
            )
        }


        var userTranslation by remember { mutableStateOf("") }

        Spacer(modifier = Modifier.height(15.dp))

        var matches = false

        TextField(value = userTranslation, onValueChange = {
            userTranslation = it
        },
            modifier = Modifier.background(color = MaterialTheme.colorScheme.onBackground)
        )
        Spacer(modifier = Modifier.height(15.dp))

        if (userTranslation.trim().equals(originalTranslation.trim(), true)) {
            matches = true
        }
        Box(
            modifier = Modifier
                .height(50.dp)
                .width((150.dp))
        ) {
            Confirm(matches, navController)
        }
    }
}

/**
 * A composable function that displays a confirmation button for the user to submit their translation in the
 * second quiz screen.
 *
 * @param matches A boolean value indicating whether the user's translation matches the original translation.
 * @param navController The navigation controller for navigating to other screens.
 */

@Composable
fun Confirm(matches: Boolean, navController: NavHostController) {
    Button(
        modifier = Modifier.fillMaxSize(),
        onClick = {
            if (matches) {
                Log.i(quizTwo, "This is correct")
                questionTwoNumber += 1
                quizTwoScore += 1
                navController.popBackStack()
            } else {
                Log.i(quizTwo, "This is incorrect")
                questionTwoNumber += 1
                navController.popBackStack()
            }
        },
    ) {
        Text("Confirm", textAlign = TextAlign.Center)
    }
}


