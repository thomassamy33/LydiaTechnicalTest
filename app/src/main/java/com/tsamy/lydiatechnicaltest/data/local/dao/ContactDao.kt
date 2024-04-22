package com.tsamy.lydiatechnicaltest.data.local.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.tsamy.lydiatechnicaltest.data.local.model.ContactDb

@Dao
interface ContactDao {

    @Query("SELECT * FROM contact WHERE id = :id")
    suspend fun getContactById(id: Int): ContactDb

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(posts: List<ContactDb>)

    @Query("SELECT * FROM contact")
    fun getAll(): PagingSource<Int, ContactDb>

    @Query("DELETE FROM contact")
    suspend fun deleteAll(): Int
}
