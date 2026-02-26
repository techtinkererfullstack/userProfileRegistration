package com.example.userprofileregistration.Repositories

import com.example.userprofileregistration.Dao.ProfileListDao
import com.example.userprofileregistration.Entities.ProfileList

class ProfileListRepo(private val profileListDao: ProfileListDao) {

    fun getAllProfilesList(): List<ProfileList> {
        return profileListDao.getAllProfilesList()
    }

    fun getProfileListById(id: Int): ProfileList? {
        return profileListDao.getProfileListById(id)
    }
    fun insertProfileList(profile: ProfileList) {
        profileListDao.insertProfileList(profile)
    }
    fun deleteProfileList(profile: ProfileList) {
        profileListDao.deleteProfileList(profile)
    }
    fun updateProfileList(profile: ProfileList) {
        profileListDao.updateProfileList(profile)
    }

}