package com.tsamy.lydiatechnicaltest.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.tsamy.lydiatechnicaltest.data.local.dao.ContactDao
import com.tsamy.lydiatechnicaltest.data.local.model.ContactDb
import com.tsamy.lydiatechnicaltest.data.local.model.RemoteKeyDb
import com.tsamy.lydiatechnicaltest.data.local.dao.RemoteKeyDao

@Database(entities = [ContactDb::class, RemoteKeyDb::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun contactDao(): ContactDao
    abstract fun remoteKeyDao(): RemoteKeyDao
}