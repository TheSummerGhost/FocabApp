package uk.ac.aber.dcs.cs31620.focab.model

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

/**
 * This interface defines methods for performing database operations on a table of
 * translations. The translations table contains rows of Translation objects, which store
 * a pair of words in different languages and an ID.
 *
 * The TranslationDao interface includes methods for inserting and deleting single
 * and multiple translations, updating a translation, and deleting all translations. It
 * also includes methods for getting all translations, getting the first four translations,
 * and sorting translations by the first word in ascending or descending order.
 * Additionally, there is a method for getting translations that match a search string
 * in their first word. All of these methods return a LiveData object of the
 * corresponding type, allowing the caller to observe changes to the database.
 */

@Dao
interface TranslationDao {

    @Insert
    suspend fun insertSingleTranslation(translation : Translation)

    @Insert suspend fun insertMultipleTranslations(translationsList: List<Translation>)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateTranslation(translation : Translation)

    @Delete
    suspend fun deleteTranslation(translation : Translation)

    @Query("DELETE FROM translations")
    suspend fun deleteAll()

    @Query("SELECT * FROM translations")
    fun getAllTranslations() : LiveData<List<Translation>>

    @Query("SELECT DISTINCT id, firstWord, secondWord FROM translations LIMIT 4")
    fun getQuizOneTranslations() : LiveData<List<Translation>>

    @Query("SELECT * FROM translations ORDER BY firstWord ASC")
    fun sortAlphaAscFirstWord() : LiveData<List<Translation>>

    @Query("SELECT * FROM translations ORDER BY firstWord DESC")
    fun sortAlphaDescFirstWord() : LiveData<List<Translation>>


    @Query("SELECT * FROM translations WHERE firstWord LIKE '%' || :searchString || '%'")
    fun getTranslationsByStringMatch(
        searchString : String?
    ) : LiveData<List<Translation>>

}