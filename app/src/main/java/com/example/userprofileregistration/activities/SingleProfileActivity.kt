package com.example.userprofileregistration.activities

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.userprofileregistration.R
import com.example.userprofileregistration.databinding.ActivitySingleProfileBinding

class SingleProfileActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySingleProfileBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivitySingleProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val trainerName = intent.getStringExtra("trainerName")
        val trainerDescription = intent.getStringExtra("trainerDescription")
        val trainerFollowers = intent.getStringExtra("trainerFollowers")
        val trainerPosts = intent.getStringExtra("trainerPosts")
        val trainerImg = intent.getStringExtra("trainerImg")


        binding.nameTV.text = trainerName
        binding.professionTV.text = trainerDescription
        binding.followersTV.text = trainerFollowers
        binding.postsTV.text = trainerPosts
        binding.profileImage.setImageURI(android.net.Uri.parse(trainerImg))




    }
}