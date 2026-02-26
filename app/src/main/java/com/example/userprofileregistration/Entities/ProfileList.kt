package com.example.userprofileregistration.Entities

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "profile_list")
data class ProfileList(
    @PrimaryKey(autoGenerate = true)
    val profileId: Int = 0,
    val name:String,
    val description:String,
    val followers:String,
    val posts:String

)
