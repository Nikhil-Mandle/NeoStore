package com.nikhilproject.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "address_table")
data class AddressEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val address: String,
    val landmark: String,
    val city: String,
    val state: String,
    val zipCode: String,
    val country: String
)