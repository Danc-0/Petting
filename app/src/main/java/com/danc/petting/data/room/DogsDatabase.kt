package com.danc.petting.data.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.danc.petting.domain.models.local.LocalPetsItem
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch


@Database(entities = arrayOf(LocalPetsItem::class), version = 1, exportSchema = false)
abstract class DogsDatabase : RoomDatabase() {

    abstract fun dogsDao(): DogsDao

    private class DogsDatabaseCallback(
        private val scope: CoroutineScope
    ) : RoomDatabase.Callback() {

        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            INSTANCE?.let { database ->
                scope.launch {
                    populateDatabase(database.dogsDao())
                }
            }
        }

        suspend fun populateDatabase(dogsDao: DogsDao) {
            dogsDao.deleteAll()

            var localPetsItem = LocalPetsItem(
                1,
                "Bosco",
                "Bosco Kenya",
                "10 years",
                "Bosco",
                "Congo Forest",
                "kjhuuhuhu",
                "Very independent dog",
                "45",
                "50",
                "10",
                "52"
            )
            dogsDao.addFavourite(localPetsItem)
            localPetsItem = LocalPetsItem(
                2,
                "Bosco",
                "Bosco Kenya",
                "10 years",
                "Bosco",
                "Congo Forest",
                "kjhuuhuhu",
                "Very independent dog",
                "45",
                "50",
                "10",
                "52"
            )
            dogsDao.addFavourite(localPetsItem)

        }
    }


    companion object {

        @Volatile
        private var INSTANCE: DogsDatabase? = null

        fun getDogsDatabase(context: Context, applicationScope: CoroutineScope): DogsDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    DogsDatabase::class.java,
                    "pets_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(DogsDatabaseCallback(applicationScope))
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}