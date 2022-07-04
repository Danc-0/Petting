package com.danc.petting.data.room

import androidx.room.*
import androidx.room.OnConflictStrategy
import com.danc.petting.domain.models.local.LocalPetsItem
import kotlinx.coroutines.flow.Flow

@Dao
interface DogsDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addFavourite(vararg localPetsItem: LocalPetsItem)

    @Query("SELECT * FROM petsanddogs ORDER BY petsDogs ASC")
    fun getAllFavouriteDogs(): Flow<List<LocalPetsItem>>

//    @Query("DELETE FROM petsanddogs")
//    suspend fun deleteFromFavorites(localPetsItem: LocalPetsItem)

    @Query("DELETE FROM petsanddogs")
    suspend fun deleteAll()

}