package com.amandeep.kotlincoroutinesexamples.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Not save variable name in RoomDataBase with camel case like passwordHash save like password_hash
 * */
@Entity
data class User (
    val username:String,
    @ColumnInfo(name = "password_hash")
    val passwordHash:Int,
    val info:String
){
    @PrimaryKey(autoGenerate = true)
    var id:Long=0
}

