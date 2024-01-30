package uk.ac.aber.dcs.cs31620.focab.datasource

import android.app.Application
import uk.ac.aber.dcs.cs31620.focab.model.Translation

/**
 * The repository for Focab database operations.
 *
 * @param application The application context
 */

class FocabRepo(application : Application) {

    /** The TranslationDao instance used to access the translations table in the database */

    private val translationDao  = FocabRoomDatabase.getDatabase(application)!!.translationDao()

    /** The TranslationDao instance used to access the translations table in the database */

    /**
     * Inserts a single translation into the translations table.
     *
     * @param translation The translation to insert
     */

    suspend fun insert(translation : Translation) {
        translationDao.insertSingleTranslation(translation)
    }

    /**
     * Inserts multiple translations into the translations table.
     *
     * @param translations The translations to insert
     */

    suspend fun insertMultipleTranslations(translations : List<Translation>) {
        translationDao.insertMultipleTranslations(translations)
    }

    /**
     * Returns a LiveData list of all translations in the translations table.
     */

    fun getAllTranslations() = translationDao.getAllTranslations()

    /**
     * Returns a LiveData list of the first four translations in the translations table.
     */

    fun getQuizOneTranslations() = translationDao.getQuizOneTranslations()

    /**
     * Returns a LiveData list of translations in the translations table that match the search string.
     *
     * @param searchString The search string to match against translations in the table
     */

    fun getTranslationsByStringMatch(searchString: String) = translationDao.getTranslationsByStringMatch(searchString)


    /**
     * Deletes all translations from the translations table.
     */

    suspend fun deleteAllTranslations() = translationDao.deleteAll()

    /**
     * Deletes a single translation from the translations table.
     *
     * @param translation The translation to delete
     */

    suspend fun deleteTranslation(translation : Translation) {
        translationDao.deleteTranslation(translation)
    }

    /**
     * Returns a LiveData list of translations in the translations table sorted in ascending order by the first word.
     */

    fun sortAlphAscFirstWord() = translationDao.sortAlphaAscFirstWord()

    /**
     * Returns a LiveData list of translations in the translations table sorted in descending order by the first word.
     */

    fun sortAlphDescFirstWord() = translationDao.sortAlphaDescFirstWord()
}