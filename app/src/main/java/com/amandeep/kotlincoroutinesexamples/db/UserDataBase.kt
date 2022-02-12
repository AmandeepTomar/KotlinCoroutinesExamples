package com.amandeep.kotlincoroutinesexamples.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.amandeep.kotlincoroutinesexamples.db.dao.UserDao
import com.amandeep.kotlincoroutinesexamples.model.User
import java.util.concurrent.locks.Lock

@Database(entities = arrayOf(User::class), version = 1)
abstract class UserDataBase : RoomDatabase() {
    abstract fun userDao(): UserDao

    companion object {
        @Volatile
        private var instance: UserDataBase? = null
        private val LOCK = Any();

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance?: buildDataBase(context).also {
                instance=it
            }
        }

        private fun buildDataBase(context: Context) = Room.databaseBuilder(
            context.applicationContext,
            UserDataBase::class.java,
            "UserDataBaseMy"
        ).build()
    }

}