package com.tsamy.lydiatechnicaltest.domain.entity

data class Contact(
    val id: Int,
    val email: String,
    val firstname: String,
    val lastname: String,
    val dob: String,
    val address: Address,
    val phone: String,
    val cell: String,
    val mediumImageUrl: String,
    val largeImageUrl: String
)

data class Address(
    val number: Int,
    val street: String,
    val city: String,
    val country: String,
    val lat: String,
    val lng: String,
    val postCode: String,
)