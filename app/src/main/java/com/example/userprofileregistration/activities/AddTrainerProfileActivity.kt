package com.example.userprofileregistration.activities

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import com.example.userprofileregistration.Entities.TrainerProfileList
import com.example.userprofileregistration.R
import com.example.userprofileregistration.ViewModels.TrainerProfileListViewModel
import com.example.userprofileregistration.databinding.ActivityAddTrainerProfileBinding

class AddTrainerProfileActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddTrainerProfileBinding
    private lateinit var viewModel: TrainerProfileListViewModel
    private var profileId: Int = -1


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_add_trainer_profile)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        binding = ActivityAddTrainerProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this)[TrainerProfileListViewModel::class.java]

        binding.btnSave.setOnClickListener {

            val name = binding.nameET.text.toString()
            val description = binding.detailsET.text.toString()
            val followers = binding.followersET.text.toString()
            val posts = binding.postET.text.toString()

            val profile = TrainerProfileList(name = name, description = description, followers = followers, posts = posts)
            viewModel.insertProfileList(profile)

            finish()

        }





    }
}