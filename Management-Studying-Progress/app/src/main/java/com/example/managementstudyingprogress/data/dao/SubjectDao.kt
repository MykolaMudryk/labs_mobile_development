package com.example.managementstudyingprogress.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.managementstudyingprogress.data.entity.SubjectEntity

/**
 * SubjectDao - interface of communication with `subjects` table
 * - marked with @Dao annotation (Data Access Object)
 * - contains custom functions-mappers for management data in table
 */

@Dao
interface SubjectDao {
    // function for fetching all subjects from table
    @Query("SELECT * FROM subjects")
    suspend fun getAllSubjects(): List<SubjectEntity>

    // function for fetching single Subject by id
    @Query("SELECT * FROM subjects WHERE id = :id")
    suspend fun getSubjectById(id: Int): SubjectEntity?

    @Query("SELECT MAX(id) FROM subjects")
    suspend fun getMaxSubjectId(): Int?

    // function for adding new value Subject to table
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addSubject(subject: SubjectEntity)

    @Update
    suspend fun updateSubject(subject: SubjectEntity)

    @Delete
    suspend fun deleteSubject(subject: SubjectEntity)
}
