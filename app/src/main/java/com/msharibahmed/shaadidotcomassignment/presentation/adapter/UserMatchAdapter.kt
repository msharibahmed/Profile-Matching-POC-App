package com.msharibahmed.shaadidotcomassignment.presentation.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.msharibahmed.shaadidotcomassignment.R
import com.msharibahmed.shaadidotcomassignment.data.local.entities.MatchProfile
import com.msharibahmed.shaadidotcomassignment.databinding.MatchProfileCardBinding
import com.msharibahmed.shaadidotcomassignment.utils.Status
import javax.inject.Inject


class UserMatchAdapter @Inject constructor(private val glide: RequestManager) :
    ListAdapter<MatchProfile, UserMatchAdapter.MyViewHolder>(userMatchAdapterDiffer) {

    companion object {
        val userMatchAdapterDiffer = object : DiffUtil.ItemCallback<MatchProfile>() {
            override fun areItemsTheSame(oldItem: MatchProfile, newItem: MatchProfile): Boolean =
                oldItem.uuid == newItem.uuid

            override fun areContentsTheSame(oldItem: MatchProfile, newItem: MatchProfile): Boolean =
                oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding =
            MatchProfileCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    private var changeProfileStatusListener: ((MatchProfile, Status) -> Unit)? = null

    fun assignChangeProfileStatusListener(listener: (MatchProfile, Status) -> Unit) {
        changeProfileStatusListener = listener
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val profile = getItem(position)
        holder.bind(profile)
    }

    inner class MyViewHolder(private val binding: MatchProfileCardBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(profile: MatchProfile) {
            binding.apply {
                glide.load(profile.imageUrl).into(ivProfile)
                "${profile.firstName} ${profile.lastName}".also { tvName.text = it }
                "${profile.city} , ${profile.country}".also { tvLocation.text = it }
                "${profile.age} years old".also { tvAge.text = it }
                addGenderIcon(profile.gender, binding)
                uiBasedOnStatusSelection(profile, binding)
                onButtonAccept(profile, binding)
                onButtonReject(profile, binding)
            }
        }

        private fun addGenderIcon(
            gender: String? = "male",
            binding: MatchProfileCardBinding
        ) {
            binding.tvName.apply {
                when (gender) {
                    "female" ->
                        setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_female, 0, 0, 0);

                    else -> setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_male, 0, 0, 0);


                }
            }

        }

        private fun uiBasedOnStatusSelection(
            profile: MatchProfile,
            binding: MatchProfileCardBinding
        ) {
            binding.apply {
                val context = binding.root.context
                when (profile.status) {
                    Status.PENDING -> {
                        groupSelection.visibility = View.VISIBLE
                        tvMessage.visibility = View.GONE
                    }
                    Status.ACCEPTED -> {
                        groupSelection.visibility = View.GONE
                        tvMessage.apply {
                            visibility = View.VISIBLE
                            setTextColor(Color.parseColor("#16AA04"))
                            text = context.getString(R.string.match_accepted_message)
                        }
                    }
                    Status.REJECTED -> {
                        groupSelection.visibility = View.GONE
                        tvMessage.apply {
                            visibility = View.VISIBLE
                            setTextColor(Color.RED)
                            text = context.getString(R.string.match_rejected_message)
                        }
                    }
                }
            }
        }

        private fun onButtonAccept(profile: MatchProfile, binding: MatchProfileCardBinding) {

            binding.apply {
                val context = binding.root.context
                btnAccept.setOnClickListener {
                    changeProfileStatusListener?.invoke(profile, Status.ACCEPTED)
                    tvMessage.text = context.getString(R.string.match_accepted_message)
                    notifyItemChanged(position)
                }
            }
        }

        private fun onButtonReject(profile: MatchProfile, binding: MatchProfileCardBinding) {

            binding.apply {
                val context = binding.root.context
                btnDecline.setOnClickListener {
                    changeProfileStatusListener?.invoke(profile, Status.REJECTED)
                    tvMessage.text = context.getString(R.string.match_rejected_message)
                    notifyItemChanged(position)
                }
            }
        }

    }

}