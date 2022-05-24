package com.yazid.cobacobaauth.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.yazid.cobacobaauth.database.dao.CarDao
import com.yazid.cobacobaauth.database.dao.UserDao
import com.yazid.cobacobaauth.database.entity.Car
import com.yazid.cobacobaauth.database.entity.User

@Database(
    entities = [
        User::class,
        Car::class
    ],
    version = 2,
    exportSchema = false
)
abstract class RoomDb : RoomDatabase() {

    abstract val userDao: UserDao
    abstract val carDao: CarDao

    companion object {
        @Volatile
        private var INSTANCE: RoomDb? = null

        fun getInstance(context: Context): RoomDb {
            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        RoomDb::class.java,
                        "tes.db"
                    )
                        .fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}