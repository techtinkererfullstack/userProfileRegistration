package com.example.userprofileregistration.activities

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.userprofileregistration.R
import com.example.userprofileregistration.ViewModels.ProfileListViewModel
import com.example.userprofileregistration.adapters.ProfileListAdapter
import com.example.userprofileregistration.databinding.ActivityProfileListBinding

class ProfileListActivity : AppCompatActivity() {
    private lateinit var binding: ActivityProfileListBinding
    private lateinit var viewModel: ProfileListViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_profile_list)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding = ActivityProfileListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.profileRecyclerViewTrainers.layoutManager = LinearLayoutManager(this)
        viewModel = ViewModelProvider(this)[ProfileListViewModel::class.java]

        binding.fabAddProfile.setOnClickListener {
            val intent = Intent(this, AddProfileActivity::class.java)
            startActivity(intent)
        }

        binding.profileRecyclerViewTrainers.layoutManager = LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,
            false)


        viewModel.profileLiveData.observe(this) { profileList ->
            val adapter = ProfileListAdapter(profileList, onEditClick = {
                viewModel.updateProfileList(it)
            }, onDeleteClick = {
                viewModel.deleteProfileList(it)
            })
            binding.profileRecyclerViewTrainers.adapter = adapter
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.getAllProfilesList()

    }
}