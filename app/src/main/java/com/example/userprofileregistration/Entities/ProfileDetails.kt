package com.example.userprofileregistration.Entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "profile_details")
data class ProfileDetails(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val profileId: Int,
    val location: String,
    val email: String
)
