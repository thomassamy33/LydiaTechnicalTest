package com.tsamy.lydiatechnicaltest.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "contact")
data class ContactDb(
    @PrimaryKey(autoGenerate = true)
    val id: Int?,
    val email: String,
    val firstname: String,
    val lastname: String,
    val dob: String,
    val number: Int,
    val street: String,
    val city: String,
    val country: String,
    val lat: String,
    val lng: String,
    val postCode: String,
    val phone: String,
    val cell: String,
    val mediumImageUrl: String,
    val largeImageUrl: String,
)