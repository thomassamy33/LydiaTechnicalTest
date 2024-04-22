package com.tsamy.lydiatechnicaltest.data.remote.mapper

import com.tsamy.lydiatechnicaltest.data.local.model.ContactDb
import com.tsamy.lydiatechnicaltest.data.remote.model.ContactDto
import com.tsamy.lydiatechnicaltest.domain.entity.Address
import com.tsamy.lydiatechnicaltest.domain.entity.Contact

fun ContactDto.toDb(): ContactDb =
    ContactDb(
        id = null,
        email = this.email,
        firstname = this.name.first,
        lastname = this.name.last,
        dob = this.dob.date,
        street = this.location.street.name,
        number = this.location.street.number,
        city = this.location.city,
        country = this.location.country,
        lat = this.location.coordinates.latitude,
        lng = this.location.coordinates.longitude,
        postCode = this.location.postcode,
        cell = this.cell,
        phone = this.phone,
        mediumImageUrl = this.picture.medium,
        largeImageUrl = this.picture.large,
    )

fun ContactDb.toEntity(): Contact {
    return Contact(
        id = this.id ?: 0,
        email = this.email,
        firstname = this.firstname,
        lastname = this.lastname,
        dob = this.dob,
        address = Address(
            street = this.street,
            number = this.number,
            city = this.city,
            country = this.country,
            lat = this.lat,
            lng = this.lng,
            postCode = this.postCode
        ),
        cell = this.cell,
        phone = this.phone,
        mediumImageUrl = this.mediumImageUrl,
        largeImageUrl = this.largeImageUrl
    )
}