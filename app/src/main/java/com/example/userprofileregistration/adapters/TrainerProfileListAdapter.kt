package com.example.userprofileregistration.adapters

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.recyclerview.widget.RecyclerView
import com.example.userprofileregistration.Entities.TrainerProfileList
import com.example.userprofileregistration.R
import com.example.userprofileregistration.activities.SingleProfileActivity
import com.example.userprofileregistration.databinding.ProfileCardBinding


class TrainerProfileListAdapter(
    private val trainerProfileList: List<TrainerProfileList>,
    private val onEditClick: (TrainerProfileList) -> Unit,
    private val onDeleteClick: (TrainerProfileList) -> Unit
): RecyclerView.Adapter<TrainerProfileListAdapter.ProfileViewHolder>() {


    class ProfileViewHolder( val binding: ProfileCardBinding): RecyclerView.ViewHolder(binding.root)



    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ProfileViewHolder {
       val binding = ProfileCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ProfileViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: ProfileViewHolder,
        position: Int
    ) {
        val trainerProfile = trainerProfileList[position]
        holder.binding.profileName.text = trainerProfile.name
        holder.binding.details.text = trainerProfile.description
        holder.binding.followers.text = "👤 ${ trainerProfile.followers }"
        holder.binding.posts.text = "\uD83D\uDDBC\uFE0F ${ trainerProfile.posts }"
        holder.binding.btnMenu.setOnClickListener {
            showPopupMenu(it, trainerProfile)
        }

        holder.binding.root.setOnClickListener {
            val context = holder.binding.root.context
            val intent = Intent(context, SingleProfileActivity::class.java)
            intent.putExtra("trainerProfileId", trainerProfile.profileId)
            context.startActivity(intent)

        }
    }

    override fun getItemCount(): Int = trainerProfileList.size


    private fun showPopupMenu(view: View, profile: TrainerProfileList) {

        val popup = PopupMenu(view.context, view)
        popup.menuInflater.inflate(R.menu.card_menu, popup.menu)

        popup.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                R.id.menu_edit -> {
                    onEditClick(profile)
                    true
                }
                R.id.menu_delete -> {
                    onDeleteClick(profile)
                    true
                }
                else -> false
            }
        }
        popup.show()
    }

}