package com.msharibahmed.shaadidotcomassignment.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.msharibahmed.shaadidotcomassignment.data.local.entities.MatchProfile
import com.msharibahmed.shaadidotcomassignment.data.local.entities.Status
import kotlinx.coroutines.flow.Flow

@Dao
interface IMatchDao {
    @Query("SELECT * FROM match_data_table")
    fun getAll(): Flow<List<MatchProfile>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(list: List<MatchProfile>)

    @Query("UPDATE match_data_table SET `status` = :status WHERE uuid = :uuid")
    suspend fun update(uuid: String, status: Status)
}