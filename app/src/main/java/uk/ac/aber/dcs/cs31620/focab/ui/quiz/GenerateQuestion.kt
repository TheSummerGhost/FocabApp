package uk.ac.aber.dcs.cs31620.focab.ui.quiz

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import uk.ac.aber.dcs.cs31620.focab.model.Translation
import uk.ac.aber.dcs.cs31620.focab.ui.navigation.Screen
import java.util.*

/**
 * A composable function that displays a column that, when clicked, navigates to a question screen
 * with a list of translations.
 *
 * @param translationsList The list of translations to be displayed in the question screen.
 * @param navController The navigation controller for navigating to other screens.
 * @param route The route to the question screen.
 */

@Composable
fun GenerateQuestion(
    translationsList: List<Translation>?,
    navController: NavHostController,
    route :String
) {
    if (translationsList == null) {

    } else {
        Column(modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.background)
            .clickable {
                val questionList: MutableList<Translation> = mutableListOf()
                var questionSize = 5
                if (translationsList.size <= 5) {
                    questionSize = translationsList.size
                }
                for (i in 1..questionSize) {
                    questionList.add(translationsList[i - 1])
                }
                navController.currentBackStackEntry?.savedStateHandle?.set(
                    key = "questionList",
                    value = questionList
                )
                navController.navigate(route)
            },
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text("Tap for the question",
                modifier = Modifier.fillMaxWidth(),
                fontSize = 35.sp,
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.onBackground,
            )
        }
    }
}
