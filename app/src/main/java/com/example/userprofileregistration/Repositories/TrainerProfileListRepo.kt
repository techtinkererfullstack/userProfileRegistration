package com.example.userprofileregistration.Repositories

import com.example.userprofileregistration.Dao.TrainerProfileListDao
import com.example.userprofileregistration.Entities.TrainerProfileList

class TrainerProfileListRepo(private val trainerProfileListDao: TrainerProfileListDao) {

    fun getAllProfilesList(): List<TrainerProfileList> {
        return trainerProfileListDao.getAllProfilesList()
    }

    fun getProfileListById(id: Int): TrainerProfileList? {
        return trainerProfileListDao.getProfileListById(id)
    }
    fun insertProfileList(profile: TrainerProfileList) {
        trainerProfileListDao.insertProfileList(profile)
    }
    fun deleteProfileList(profile: TrainerProfileList) {
        trainerProfileListDao.deleteProfileList(profile)
    }
    fun updateProfileList(profile: TrainerProfileList) {
        trainerProfileListDao.updateProfileList(profile)
    }

}