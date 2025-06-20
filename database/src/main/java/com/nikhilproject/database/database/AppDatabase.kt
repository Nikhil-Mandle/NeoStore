package com.nikhilproject.database.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.nikhilproject.database.dao.AddressDao
import com.nikhilproject.database.entity.AddressEntity

@Database(entities = [AddressEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun addressDao(): AddressDao
}