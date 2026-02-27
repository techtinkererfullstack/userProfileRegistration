package com.example.userprofileregistration.ViewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.userprofileregistration.Database.AppDatabase
import com.example.userprofileregistration.Entities.TrainerProfileList
import com.example.userprofileregistration.Repositories.TrainerProfileListRepo

class TrainerProfileListViewModel(application: Application): AndroidViewModel(application) {
    private val repository: TrainerProfileListRepo
    val profileLiveData = MutableLiveData<List<TrainerProfileList>>()

    init {
        val dao = AppDatabase.getDatabase(application).trainerProfileListDao()
        repository = TrainerProfileListRepo(dao)
        getAllProfilesList()
    }

    fun getAllProfilesList() {
        profileLiveData.value = repository.getAllProfilesList()
    }

    fun insertProfileList(profile: TrainerProfileList) {
        repository.insertProfileList(profile)
        getAllProfilesList()
    }

    fun deleteProfileList(profile: TrainerProfileList) {
        repository.deleteProfileList(profile)
        getAllProfilesList()
    }
    fun updateProfileList(profile: TrainerProfileList) {
        repository.updateProfileList(profile)
        getAllProfilesList()
    }

    fun getProfileListById(id: Int): TrainerProfileList? {
        return repository.getProfileListById(id)
    }

}