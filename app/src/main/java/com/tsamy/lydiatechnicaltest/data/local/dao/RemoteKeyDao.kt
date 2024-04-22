package com.tsamy.lydiatechnicaltest.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.tsamy.lydiatechnicaltest.data.local.model.RemoteKeyDb

@Dao
interface RemoteKeyDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertKeys(keys: List<RemoteKeyDb>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertKey(key: RemoteKeyDb)

    @Query("select * from remote_keys where id=:key")
    suspend fun getKeyByContact(key: String): RemoteKeyDb?

    @Query("delete from remote_keys")
    suspend fun clearKeys()
}