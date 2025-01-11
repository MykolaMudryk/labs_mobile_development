package data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import data.ArtObject

@Database(
    entities = [ArtObject::class],
    version = 1,
    exportSchema = false
)
abstract class ArtDatabase : RoomDatabase() {
    abstract fun artDao(): ArtDao
}
