package com.tsamy.lydiatechnicaltest.data.remote.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ContactsDto(
    @Json(name = "results") val results: List<ContactDto>,
    @Json(name = "info") val info: InfoDto
)

@JsonClass(generateAdapter = true)
data class ContactDto(
    @Json(name = "gender") val gender: String,
    @Json(name = "name") val name: NameDto,
    @Json(name = "location") val location: LocationDto,
    @Json(name = "email") val email: String,
    @Json(name = "dob") val dob: DobDto,
    @Json(name = "phone") val phone: String,
    @Json(name = "cell") val cell: String,
    @Json(name = "picture") val picture: PictureDto,
    @Json(name = "nat") val nat: String
)

@JsonClass(generateAdapter = true)
data class NameDto(
    @Json(name = "title") val title: String,
    @Json(name = "first") val first: String,
    @Json(name = "last") val last: String
)

@JsonClass(generateAdapter = true)
data class LocationDto(
    @Json(name = "street") val street: StreetDto,
    @Json(name = "city") val city: String,
    @Json(name = "state") val state: String,
    @Json(name = "country") val country: String,
    @Json(name = "postcode") val postcode: String,
    @Json(name = "coordinates") val coordinates: CoordinatesDto,
    @Json(name = "timezone") val timezone: TimezoneDto
)

@JsonClass(generateAdapter = true)
data class StreetDto(
    @Json(name = "number") val number: Int,
    @Json(name = "name") val name: String
)

@JsonClass(generateAdapter = true)
data class CoordinatesDto(
    @Json(name = "latitude") val latitude: String,
    @Json(name = "longitude") val longitude: String
)

@JsonClass(generateAdapter = true)
data class TimezoneDto(
    @Json(name = "offset") val offset: String,
    @Json(name = "description") val description: String
)

@JsonClass(generateAdapter = true)
data class DobDto(
    @Json(name = "date") val date: String,
    @Json(name = "age") val age: Int
)

@JsonClass(generateAdapter = true)
data class PictureDto(
    @Json(name = "large") val large: String,
    @Json(name = "medium") val medium: String,
    @Json(name = "thumbnail") val thumbnail: String
)