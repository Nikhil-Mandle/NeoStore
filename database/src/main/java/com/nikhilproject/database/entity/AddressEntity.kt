package com.nikhilproject.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "address_table")
data class AddressEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
    val street: String,
    val city: String,
    val state: String,
    val zip: String
)