package uk.ac.aber.dcs.cs31620.focab.ui.quiz

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
import java.util.*

var questionOneNumber = 0

/**
*
*A composable function that displays a question for the user to answer by choosing the correct
*translation from a list of options.
*@param originalTranslation The correct translation.
*@param firstWord The first word in the translation.
*@param navController The navigation controller for navigating to other screens.
 */

@Composable
fun QuizOneQuestion(
    navController: NavHostController,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.background),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center) {

        Log.d("QuestionNumber", questionOneNumber.toString())

        val questionList =
            navController.previousBackStackEntry?.savedStateHandle?.get<List<Translation>>("questionList")
        if (questionList == null) {
        } else {
            Log.d("QuizOneQuestion", questionList.toString())
            if ((questionOneNumber) == questionList.size) {
                Text("Your score is $quizOneScore out of $questionOneNumber",
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center,
                    fontSize = 30.sp,
                    color = MaterialTheme.colorScheme.onBackground)
            } else {
                PoseQuestion(questionList[questionOneNumber].secondWord,
                    questionList[questionOneNumber].firstWord,
                    navController)
            }
        }
    }
}

/**
*A composable function that displays a quiz question, including five answer buttons with modified versions of the correct answer, as well as the correct answer.
*When an answer button is clicked, the function checks if it is the correct answer and updates the score and question number accordingly.
*@param originalTranslation The original, correct translation.
*@param firstWord The first word in the translation pair.
*@param navController The navigation controller for navigating to other screens.
 */

@Composable
fun PoseQuestion(
    originalTranslation: String,
    firstWord: String,
    navController: NavHostController,
) {
    val modified = mutableListOf(originalTranslation)
    val usedVowels: MutableList<Char> = mutableListOf()
    for (i in originalTranslation.toCharArray()) {
        if (isVowel(i)) {
            usedVowels.add(i)
            break
        }
    }
    for (i in 1..4) {
        for (j in modified[i - 1].toCharArray()) {
            if (isVowel(j)) {
                usedVowels.add(j)
                break
            }
        }
        modified.add(changeFirstVowel(modified[i - 1], usedVowels))
    }
    modified.add(originalTranslation)

    Text("Select the correct Translation",
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
    Spacer(modifier = Modifier.height(15.dp))
    AnswerButton(modified[5], originalTranslation, navController)
    Spacer(modifier = Modifier.height(15.dp))
    AnswerButton(modified[4], originalTranslation, navController)
    Spacer(modifier = Modifier.height(15.dp))
    AnswerButton(modified[3], originalTranslation, navController)
    Spacer(modifier = Modifier.height(15.dp))
    AnswerButton(modified[2], originalTranslation, navController)
    Spacer(modifier = Modifier.height(15.dp))
    AnswerButton(modified[1], originalTranslation, navController)
}

/**
*
*A composable function that displays a clickable box with a translation text inside. When clicked,
*the translation is compared to the correct answer and the quiz score is updated accordingly.
*@param modified The modified translation to be displayed and compared to the correct answer.
*@param answer The correct translation.
*@param navController The navigation controller for navigating to other screens.
 */

@Composable
fun AnswerButton(modified: String, answer: String, navController: NavHostController) {
    Box(
        modifier = Modifier
            .height(50.dp)
            .width(250.dp)
            .clickable(
                onClick = {
                    if (modified == answer) {
                        Log.i(quizOne, "This is correct")
                        questionOneNumber += 1
                        quizOneScore += 1
                        navController.popBackStack()
                    } else {
                        Log.i(quizOne, "This is incorrect")
                        questionOneNumber += 1
                        navController.popBackStack()
                    }
                },
            )
    ) {
        Image(painter = painterResource(id = R.drawable.focabtabright),
            contentDescription = stringResource(id = R.string.focab_right_tab),
            modifier = Modifier.fillMaxSize())
        Text(modified,
            modifier = Modifier.align(Alignment.Center),
            color = md_theme_light_onSurface
        )
    }
}

/**
*
*A function that determines whether a given character is a vowel.
*@param c The character to check.
*@return true if the character is a vowel, false otherwise.
 */

fun isVowel(c: Char): Boolean {
    return c in "aeiouAEIOU"
}

/**
*
*A function that changes the first vowel in the given string to a random vowel that is not already
*present in the string, and is not present in the given list of characters.
*@param s The string to modify.
*@param c The list of characters that cannot be used as the new vowel.
*@return The modified string.
 */

fun changeFirstVowel(s: String, c: MutableList<Char>): String {
    val sb = StringBuilder(s)
    val vowels: MutableList<Char> = mutableListOf('a', 'e', 'i', 'o', 'u')
    for (j in c) {
        if (vowels.contains(j)) {
            vowels.remove(j)
        }
    }
    val random = Random()
    for (i in sb.indices) {
        Log.i(quizOne, i.toString())
        Log.i(quizOne, c.toString())
        if (isVowel(sb[i])) {
            if (vowels.isNotEmpty()) {
                var newVowel = vowels[0]
                if (vowels.size == 1) {
//                var newVowel = vowels[0]
                } else {
                    newVowel = vowels[random.nextInt(vowels.size)]
                    while (newVowel == sb[i]) {
                        newVowel = vowels[random.nextInt(vowels.size)]
                    }
                }
                sb[i] = newVowel
//            Log.i(quizOne, "newVowel: $newVowel")
                break
            }
        }
    }
    return sb.toString()
}
