package com.msharibahmed.shaadidotcomassignment.data.local.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.msharibahmed.shaadidotcomassignment.utils.AppConstants.DATABASE_TABLE_NAME
import com.msharibahmed.shaadidotcomassignment.utils.Status

@Entity(tableName = DATABASE_TABLE_NAME)
data class MatchProfile(
    @PrimaryKey
    @ColumnInfo(name = "uuid")
    var uuid: String, // match->login_uuid for->unique user id->a2c06b3c-3f00-47ae-b0c8-5a81a20df263
    @ColumnInfo(name = "first_name")
    var firstName: String?, // match->firstname: Moon
    @ColumnInfo(name = "last_name")
    var lastName: String?, // match->lastname: Delsing
    @ColumnInfo(name = "image_url")
    var imageUrl: String?, // match->profile_url-> https://randomuser.me/api/portraits/women/63.jpg
    @ColumnInfo(name = "age")
    var age: Int?, // match->age: 43
    @ColumnInfo(name = "email")
    var email: String?, // match->email: moon.delsing@example.com
    @ColumnInfo(name = "country")
    var country: String?, // match->country: Netherlands
    @ColumnInfo(name = "status")
    var status: Status = Status.PENDING, // match->status: Pending(default)
)

