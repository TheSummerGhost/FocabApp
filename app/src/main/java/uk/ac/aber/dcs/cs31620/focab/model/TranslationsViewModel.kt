package uk.ac.aber.dcs.cs31620.focab.model

import android.app.Application
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import uk.ac.aber.dcs.cs31620.focab.datasource.FocabRepo

const val translationsViewModel = "TranslationsViewModel"

/**

*ViewModel for the translations.
*@property repo Repository for interacting with the database
*@property translationList LiveData object that holds a list of all translations in the database
*@property quizOneTranslationList LiveData object that holds a list of all translations in the database that are part of quiz one
*@property translationSearch Data class that holds all information related to sorting and filtering translations
 */

class TranslationsViewModel(application: Application) :
    AndroidViewModel(application) {
    private val repo: FocabRepo = FocabRepo(application)

    /** LiveData object that holds a list of all translations in the database. */

    var translationList: LiveData<List<Translation>> = repo.getAllTranslations()
        private set

    /** LiveData object that holds a list of all translations in the database that are part of quiz one. */

    var quizOneTranslationList: LiveData<List<Translation>> = repo.getQuizOneTranslations()

    /** Data class that holds all information related to sorting and filtering translations. */

    var translationSearch: TranslationSearch by mutableStateOf(
        TranslationSearch(
            searchString = "",
            sortByAlphAsc = false,
            sortByAlphDesc = false,
            sortByDateAsc = false,
            sortByDateDesc = false
        )
    )
        private set

    /**
    *Insert a new translation into the database.
    *@param newTranslation The translation to be inserted
     */

    fun insertTranslation(newTranslation: Translation) {
        viewModelScope.launch(Dispatchers.IO) {
            repo.insert(newTranslation)
        }
    }

    /**
    *Delete a translation from the database.
    *@param newTranslation The translation to be deleted
     */

    fun deleteTranslation(newTranslation: Translation) {
        viewModelScope.launch(Dispatchers.IO) {
            repo.deleteTranslation(newTranslation)
        }
    }

    /**
    *Delete all translations from the database.
     */

    fun deleteAllTranslations() {
        viewModelScope.launch(Dispatchers.IO) {
            repo.deleteAllTranslations()
        }
    }

    /**
    *
    *Update the translations displayed on the translations screen based on the specified [TranslationSearch] object.
    *@param value The [TranslationSearch] object containing information on how to filter and sort the translations
     */


    fun updateTranslationSearch(value: TranslationSearch) {
        Log.i(translationsViewModel, "updateTranslationSearch")
        getTranslations(value)
    }

    /**
    *
    *Get the translations based on the specified [TranslationSearch] object.
    *@param newTranslationSearch The [TranslationSearch] object containing information on how to filter and sort the translations
     */

    private fun getTranslations(newTranslationSearch: TranslationSearch) {

        if (newTranslationSearch.searchString != translationSearch.searchString) {
            Log.i(translationsViewModel, newTranslationSearch.searchString)
            Log.i(translationsViewModel, translationSearch.searchString)
            translationList = repo.getTranslationsByStringMatch(newTranslationSearch.searchString)
        }

        if (newTranslationSearch.sortByAlphAsc != translationSearch.sortByAlphAsc) {
            translationList = if (newTranslationSearch.sortByAlphAsc) {
                repo.sortAlphAscFirstWord()
            } else {
                repo.sortAlphDescFirstWord()
            }

            Log.i(translationsViewModel, newTranslationSearch.sortByAlphAsc.toString())

        }
        translationSearch = newTranslationSearch
        Log.i(translationsViewModel, translationList.toString())
    }
}