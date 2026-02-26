package com.example.userprofileregistration.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.recyclerview.widget.RecyclerView
import com.example.userprofileregistration.Entities.ProfileList
import com.example.userprofileregistration.R
import com.example.userprofileregistration.databinding.ProfileCardBinding


class ProfileListAdapter(
    private val profileList: List<ProfileList>,
    private val onEditClick: (ProfileList) -> Unit,
    private val onDeleteClick: (ProfileList) -> Unit
): RecyclerView.Adapter<ProfileListAdapter.ProfileViewHolder>() {


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
        val profile = profileList[position]
        holder.binding.profileName.text = profile.name
        holder.binding.details.text = profile.description
        holder.binding.followers.text = "👤 ${ profile.followers }"
        holder.binding.posts.text = "\uD83D\uDDBC\uFE0F ${ profile.posts }"
        holder.binding.btnMenu.setOnClickListener {
            showPopupMenu(it, profile)
        }



    }

    override fun getItemCount(): Int = profileList.size


    private fun showPopupMenu(view: View, profile: ProfileList) {

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