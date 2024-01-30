package uk.ac.aber.dcs.cs31620.focab.datasource

import android.content.Context
import android.util.Log
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import uk.ac.aber.dcs.cs31620.focab.model.TranslationDao
import uk.ac.aber.dcs.cs31620.focab.model.Translation

const val tag = "Database"

@Database(entities = [Translation::class], version = 1)
abstract class FocabRoomDatabase : RoomDatabase() {

    /**
     * Returns the [TranslationDao] for this database
     */

    abstract fun translationDao() : TranslationDao

    companion object {
        private var instance : FocabRoomDatabase? = null
        private val coroutineScope = CoroutineScope(Dispatchers.IO)

        /**
         * Get a singleton instance of the database.
         *
         * @param context The context of the app
         * @return The singleton instance of the database
         */

        @Synchronized
        fun getDatabase(context : Context) : FocabRoomDatabase? {
            Log.i(tag, "At beginning of set up")
            if (instance == null) {
                Log.i(tag, "Instance is null")
                instance = Room.databaseBuilder<FocabRoomDatabase>(
                    context.applicationContext,
                    FocabRoomDatabase::class.java,
                    "focab_database"
                )
                    .build()
            }
            return instance
        }
    }
}

