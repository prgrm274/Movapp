package com.programmer2704.movapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [MovieEntity::class], version = 1)
abstract class AppDB : RoomDatabase() {

    abstract fun movieDAO(): MovieDao
}