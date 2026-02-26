package com.example.userprofileregistration.activities

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import com.example.userprofileregistration.Entities.ProfileList
import com.example.userprofileregistration.R
import com.example.userprofileregistration.ViewModels.ProfileListViewModel
import com.example.userprofileregistration.databinding.ActivityAddProfileBinding

class AddProfileActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddProfileBinding
    private lateinit var viewModel: ProfileListViewModel
    private var profileId: Int = -1


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_add_profile)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        binding = ActivityAddProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this)[ProfileListViewModel::class.java]

        binding.btnSave.setOnClickListener {

            val name = binding.nameET.text.toString()
            val description = binding.detailsET.text.toString()
            val followers = binding.followersET.text.toString()
            val posts = binding.postET.text.toString()

            val profile = ProfileList(name = name, description = description, followers = followers, posts = posts)
            viewModel.insertProfileList(profile)

            finish()

        }





    }
}