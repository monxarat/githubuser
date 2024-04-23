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

package com.clcosmos.githubuser

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatEditText
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.clcosmos.githubuser.adapter.GitHubUserAdapter
import com.clcosmos.githubuser.api.GitHubApiClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

/**
 * MainActivity is the entry point of the application.
 * It displays a list of GitHub users and allows the user to search for a specific user.
 * Clicking on a user navigates to their repositories.
 */
class MainActivity : AppCompatActivity() {

    /**
     * Key for passing user detail data between activities.
     */
    companion object {
        const val EXTRA_USER_DETAIL = "extra_user_detail"
    }

    /**
     * RecyclerView for displaying the list of GitHub users.
     */
    private lateinit var userListView: RecyclerView

    /**
     * Adapter for the userListView.
     */
    private lateinit var adapter: GitHubUserAdapter

    /**
     * Called when the activity is starting.
     * This is where most initialization should go.
     *
     * @param savedInstanceState If the activity is being re-initialized after previously being shut down then this Bundle contains the data it most recently supplied in onSaveInstanceState(Bundle).
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize userListView
        userListView = findViewById(R.id.userListView)
        userListView.layoutManager = LinearLayoutManager(this)

        // Initialize search field
        val editSearch = findViewById<AppCompatEditText>(R.id.edit_esearch)
        editSearch.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable?) {
                // Filter users based on search query
                adapter.filter(s.toString())
            }
        })

        // Initialize adapter with an empty list of users
        adapter = GitHubUserAdapter(this, mutableListOf())
        userListView.adapter = adapter

        // Load initial list of users
        loadUsers()
    }

    /**
     * Fetches the list of GitHub users from the API and updates the adapter.
     * If a user is clicked, navigates to the RepositoryActivity.
     */
    private fun loadUsers() {
        GlobalScope.launch(Dispatchers.IO) {
            val users = GitHubApiClient.api.getUsers()
            launch(Dispatchers.Main) {
                // Update adapter with the list of users
                adapter.addUsers(users)
            }
        }
    }
}