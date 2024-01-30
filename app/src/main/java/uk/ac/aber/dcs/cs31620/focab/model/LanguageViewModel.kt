package uk.ac.aber.dcs.cs31620.focab.model

import android.app.Application
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.File

/**
 * ViewModel for storing and interacting with the selected languages for the app.
 *
 * @param application: The application context.
 */

class LanguageViewModel (application: Application) : AndroidViewModel(application) {

    private var firstLanguage: File = File(application.filesDir, "firstlanguage.txt")
    private var secondLanguage: File = File(application.filesDir, "secondlanguage.txt")

    var firstLanguageText by mutableStateOf("")
    var secondLanguageText by mutableStateOf("")

    var cachedFirstLanguage = ""
    var cachedSecondLanguage = ""

    fun onStart() {
        viewModelScope.launch(Dispatchers.IO) {
            var firstLangResult = ""
            if (firstLanguage.exists()) {
                firstLangResult = firstLanguage.readText()
            }
            cachedFirstLanguage = firstLangResult
            firstLanguageText = firstLangResult

            var secondLangResult = ""
            if (secondLanguage.exists()) {
                secondLangResult = secondLanguage.readText()
            }
            cachedSecondLanguage = secondLangResult
            secondLanguageText = secondLangResult
        }
    }

    fun onPause() {
        viewModelScope.launch (Dispatchers.IO){
            firstLanguage.writeText(firstLanguageText)
            secondLanguage.writeText(secondLanguageText)
        }
    }
}