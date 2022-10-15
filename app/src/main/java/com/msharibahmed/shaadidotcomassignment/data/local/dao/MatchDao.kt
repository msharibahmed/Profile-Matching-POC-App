package com.msharibahmed.shaadidotcomassignment.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.msharibahmed.shaadidotcomassignment.data.local.entities.MatchProfile
import com.msharibahmed.shaadidotcomassignment.utils.AppConstants.DATABASE_TABLE_NAME
import com.msharibahmed.shaadidotcomassignment.utils.Status
import kotlinx.coroutines.flow.Flow

@Dao
interface MatchDao {
    @Query("SELECT * FROM $DATABASE_TABLE_NAME")
    fun getAll(): Flow<List<MatchProfile>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(list: List<MatchProfile>)

    @Query("UPDATE $DATABASE_TABLE_NAME SET `status` = :status WHERE uuid = :uuid")
    suspend fun update(uuid: String, status: Status)
}