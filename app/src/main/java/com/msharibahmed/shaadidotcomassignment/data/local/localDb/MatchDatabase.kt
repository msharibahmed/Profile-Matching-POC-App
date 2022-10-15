package com.msharibahmed.shaadidotcomassignment.data.local.localDb

import androidx.room.Database
import androidx.room.RoomDatabase
import com.msharibahmed.shaadidotcomassignment.data.local.dao.IMatchDao
import com.msharibahmed.shaadidotcomassignment.data.local.entities.MatchProfile

@Database(entities = [MatchProfile::class], version = 1, exportSchema = false)
abstract class MatchDatabase : RoomDatabase() {
    abstract fun matchDao(): IMatchDao
}
