package com.example.managementstudyingprogress.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.managementstudyingprogress.data.entity.SubjectEntity
import com.example.managementstudyingprogress.data.entity.SubjectLabEntity
import androidx.room.DeleteTable

/**
 * SubjectLabsDao - interface of communication with `subjectsLabs` table
 * - marked with @Dao annotation (Data Access Object)
 * - contains custom functions-mappers for management data in table
 */

@Dao
interface SubjectLabsDao{
    // function for fetching all labs from table
    @Query("SELECT * FROM subjectsLabs")
    suspend fun getAllSubjectLabs(): List<SubjectLabEntity>

    // function for fetching all labs from table and filtering them by subjectId
    @Query("SELECT * FROM subjectsLabs WHERE subject_id = :subjectId")
    suspend fun getSubjectLabsBySubjectId(subjectId: Int): List<SubjectLabEntity>

    // function for adding new lab to subject
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addSubjectLab(subjectLabEntity: SubjectLabEntity)

    // function for updating a comment for lab
    @Update
    suspend fun updateSubjectLab(subjectLabEntity: SubjectLabEntity)

    // function for deleting lab from subject
    @Delete
    suspend fun deleteSubjectLab(subjectLabEntity: SubjectLabEntity)

    @Query("SELECT MAX(id) FROM subjectsLabs")
    suspend fun getMaxLabId(): Int?

}