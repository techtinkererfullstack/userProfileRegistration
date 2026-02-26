package com.example.userprofileregistration.ViewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.userprofileregistration.Database.AppDatabase
import com.example.userprofileregistration.Entities.ProfileList
import com.example.userprofileregistration.Repositories.ProfileListRepo

class ProfileListViewModel(application: Application): AndroidViewModel(application) {
    private val repository: ProfileListRepo
    val profileLiveData = MutableLiveData<List<ProfileList>>()

    init {
        val dao = AppDatabase.getDatabase(application).profileListDao()
        repository = ProfileListRepo(dao)
        getAllProfilesList()
    }

    fun getAllProfilesList() {
        profileLiveData.value = repository.getAllProfilesList()
    }

    fun insertProfileList(profile: ProfileList) {
        repository.insertProfileList(profile)
        getAllProfilesList()
    }

    fun deleteProfileList(profile: ProfileList) {
        repository.deleteProfileList(profile)
        getAllProfilesList()
    }
    fun updateProfileList(profile: ProfileList) {
        repository.updateProfileList(profile)
        getAllProfilesList()
    }

    fun getProfileListById(id: Int): ProfileList? {
        return repository.getProfileListById(id)
    }

}