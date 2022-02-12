package com.amandeep.kotlincoroutinesexamples.db.dao

import androidx.room.*
import com.amandeep.kotlincoroutinesexamples.model.User

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUser(user:User):Long

    @Update
    fun updateUser(user: User)

    @Delete
    fun deleteUser(user: User)

    @Query("SELECT * FROM user Where username =:username")
    fun getUserByName(username:String):User
}