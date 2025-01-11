package data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import data.ArtObject


@Dao
interface ArtDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertArtObject(artObject: ArtObject)

    @Query("SELECT * FROM art_objects WHERE objectID = :objId LIMIT 1")
    suspend fun getArtObjectById(objId: Int): ArtObject?
}
