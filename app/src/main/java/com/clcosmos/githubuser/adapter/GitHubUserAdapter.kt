/*
 * Copyright (C) 2024 The Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package com.clcosmos.githubuser.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.clcosmos.githubuser.MainActivity
import com.clcosmos.githubuser.R
import com.clcosmos.githubuser.RepositoryActivity
import com.clcosmos.githubuser.api.GitHubApiClient
import com.clcosmos.githubuser.api.entity.GitHubUser
import com.clcosmos.githubuser.api.entity.GitHubUserDetail
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

/**
 * Adapter for displaying GitHub users in a RecyclerView.
 *
 * @property context The context in which the adapter is being used.
 * @property users The list of GitHub users to display.
 */
class GitHubUserAdapter(
    private val context: Context,
    private var users: MutableList<GitHubUser>
) : RecyclerView.Adapter<GitHubUserAdapter.UserViewHolder>() {
    private var usersDetail : MutableList<GitHubUserDetail> = mutableListOf()
    private var filteredUsers: MutableList<GitHubUser> = mutableListOf()

    /**
     * ViewHolder for displaying a GitHub user.
     *
     * @property itemView The view representing a single GitHub user.
     */
    inner class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val avatarImageView: ImageView = itemView.findViewById(R.id.avatarImageView)
        val nameTextView: TextView = itemView.findViewById(R.id.nameTextView)
        val emailTextView: TextView = itemView.findViewById(R.id.emailTextView)
        val companyTextView: TextView = itemView.findViewById(R.id.companyTextView)
        val locationTextView: TextView = itemView.findViewById(R.id.locationTextView)
    }

    /**
     * Creates a new ViewHolder for a GitHub user.
     *
     * @param parent The ViewGroup into which the new View will be added after it is bound to an adapter position.
     * @param viewType The view type of the new View.
     * @return A new UserViewHolder that holds a View of the given view type.
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.list_item_github_user, parent, false)
        return UserViewHolder(view)
    }

    /**
     * Binds the data of a GitHub user to the ViewHolder.
     *
     * @param holder The ViewHolder to be updated.
     * @param position The position of the item within the adapter's data set.
     */
    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val user = filteredUsers[position]
        val userDetail = usersDetail.getOrNull(position)

        // Load the user's avatar image into the ImageView
        Glide.with(context)
            .load(user.avatarUrl)
            .circleCrop()
            .placeholder(R.drawable.circle_background)
            .into(holder.avatarImageView)

        // set the user's name, email, company, and location
        // if the userDetail is null, set the default values
        holder.nameTextView.text = user.login
        holder.emailTextView.text = "--"
        holder.companyTextView.text = "--"
        holder.locationTextView.text = "--"

        // Check if userDetail is null, if not, update the UI with user details
        userDetail?.let {
            holder.nameTextView.text = it.name ?: user.login
            holder.emailTextView.text = it.email ?: "--"
            holder.companyTextView.text = it.company ?: "--"
            holder.locationTextView.text = it.location ?: "--"
        }

        // Open the RepositoryActivity when the user clicks on the item
        holder.itemView.setOnClickListener {
            val intent = Intent(context, RepositoryActivity::class.java).apply {
                putExtra(MainActivity.EXTRA_USER_DETAIL, userDetail)
            }
            context.startActivity(intent)
        }

        // Fetch user details asynchronously if not already fetched
        if (userDetail == null) {
            GlobalScope.launch(Dispatchers.IO) {
                val userDetail = GitHubApiClient.api.getUserDetail(user.login)
                usersDetail.add(userDetail)
                launch(Dispatchers.Main) {
                    notifyItemChanged(position)
                }
            }
        }
    }

    /**
     * Returns the total number of items in the data set held by the adapter.
     *
     * @return The total number of items in this adapter.
     */
    override fun getItemCount(): Int {
        return filteredUsers.size
    }

    /**
     * Filters the GitHub users based on a query.
     *
     * @param query The query to filter the GitHub users.
     */
    fun filter(query: String) {
        filteredUsers = if (query.isEmpty()) {
            users.toMutableList()
        } else {
            users.filter { user ->
                user.login.contains(query, ignoreCase = true)
            }.toMutableList()
        }
        notifyDataSetChanged()
    }

    /**
     * Adds new GitHub users to the adapter and updates the RecyclerView.
     *
     * @param newUsers The new GitHub users to add.
     */
    fun addUsers(newUsers: List<GitHubUser>) {
        users.clear()
        users.addAll(newUsers)
        filteredUsers.clear()
        filteredUsers.addAll(newUsers)
        notifyDataSetChanged()
    }
}