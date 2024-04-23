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

import android.graphics.Typeface
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.AdapterView
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.Spinner
import android.widget.TextView
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatEditText
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.clcosmos.githubuser.adapter.RepositoryAdapter
import com.clcosmos.githubuser.api.GitHubApiClient
import com.clcosmos.githubuser.api.entity.GitHubUserDetail
import com.clcosmos.githubuser.view.CustomItemDecorator
import com.clcosmos.githubuser.view.CustomSpinnerAdapter
import com.clcosmos.githubuser.view.SpinnerItem
import com.google.android.material.textview.MaterialTextView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

/**
 * RepositoryActivity is an activity that displays a list of repositories for a specific GitHub user.
 * It allows the user to filter the repositories based on certain criteria.
 */
class RepositoryActivity : AppCompatActivity() {

    /**
     * Adapter for the RecyclerView that displays the list of repositories.
     */
    private lateinit var repositoryAdapter : RepositoryAdapter

    /**
     * TextView for displaying the number of repositories.
     */
    private val repositoriesNumber: TextView by lazy { findViewById(R.id.repositoriesNumber) }

    /**
     * ProgressBar for indicating loading status.
     */
    private val progressBar: ProgressBar by lazy { findViewById(R.id.progressBar) }

    /**
     * Called when the activity is starting.
     * This is where most initialization should go.
     *
     * @param savedInstanceState If the activity is being re-initialized after previously being shut down then this Bundle contains the data it most recently supplied in onSaveInstanceState(Bundle).
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_repository)
        setSupportActionBar(findViewById(R.id.toolbar))
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        repositoryAdapter = RepositoryAdapter(this, mutableListOf())
        //onBackPressedメソッドが非推奨になったため、onBackPressedDispatcher.onBackPressed()を使用して戻る処理
        onBackPressedDispatcher.addCallback(object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                // call onBackPressed() as normal afterwards
                remove()
                finish()
            }
        })

        // Retrieve GitHubUserDetail from intent
        val userDetail = intent.getParcelableExtra<GitHubUserDetail>(MainActivity.EXTRA_USER_DETAIL)

        if (userDetail == null) {
            finish()
            return
        }

        // Display user details
        val avatarView = findViewById<ImageView>(R.id.avatarImageView)
        Glide.with(this)
            .load(userDetail.avatarUrl)
            .circleCrop()
            .placeholder(R.drawable.circle_background)
            .into(avatarView)

        val usernameTextView = findViewById<TextView>(R.id.usernameTextView)
        usernameTextView.text = userDetail.login

        val fullNameTextView = findViewById<TextView>(R.id.fullNameTextView)
        fullNameTextView.text = userDetail.name ?: ""

        val followersTextView = findViewById<TextView>(R.id.followersTextView)
        followersTextView.text = getString(R.string.followers_count, userDetail.followers)

        val followingTextView = findViewById<TextView>(R.id.followingTextView)
        followingTextView.text = getString(R.string.following_count, userDetail.following)


        // Initialize RecyclerView
        val repositoriesRecyclerView = findViewById<RecyclerView>(R.id.repositoriesRecyclerView)
        val spacingInPixels = resources.getDimensionPixelSize(R.dimen.spacing) // Define your spacing value
        val itemDecorator = CustomItemDecorator(spacingInPixels, getColor(R.color.border))
        repositoriesRecyclerView.addItemDecoration(itemDecorator)
        repositoriesRecyclerView.layoutManager = LinearLayoutManager(this)
        repositoriesRecyclerView.adapter = repositoryAdapter

        val spinner = findViewById<Spinner>(R.id.spinner)
        // Spinnerのアイテムを定義
        val items = listOf(
            SpinnerItem("All", R.drawable.icon_all),
            SpinnerItem("Public",R.drawable.icon_public),
            //SpinnerItem("Sources",R.drawable.icon_asources),
            SpinnerItem("Forks",R.drawable.icon_fork),
            SpinnerItem("Archived",R.drawable.icon_archived),
            //SpinnerItem("Mirrors",R.drawable.icon_mirrors),
            //SpinnerItem("Templates", R.drawable.icon_templates)
        )

        // アダプターを作成してSpinnerにセット
        val adapter = CustomSpinnerAdapter(this, items)
        spinner.adapter = adapter
        // Spinnerの選択イベントをリスナーで処理
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val selectedItem = parent?.getItemAtPosition(position) as SpinnerItem
                val title = view?.findViewById<MaterialTextView>(R.id.textTextView)
                title?.text = "${selectedItem.text} repositories"
                title?.textSize = 18f
                // Create a Typeface object for the bold font
                val boldTypeface = Typeface.defaultFromStyle(Typeface.BOLD)
                // Set the typeface to bold
                title?.typeface = boldTypeface

                // Fetch user's repositories based on the selected item
                fetchRepositories(userDetail.login, selectedItem.text)
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                // 何もしない
            }
        }
        val editSearch = findViewById<AppCompatEditText>(R.id.edit_esearch)
        editSearch.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                // Call filter method of the adapter
                repositoryAdapter.filter(s.toString())
            }

            override fun afterTextChanged(s: Editable?) {
            }
        })

        // Fetch user's repositories
        fetchRepositories(userDetail.login, "All")
    }

    /**
     * Fetches the repositories of a specific GitHub user based on the selected filter criteria.
     *
     * @param username The username of the GitHub user.
     * @param selectedItem The selected filter criteria.
     */
    private fun fetchRepositories(username: String, selectedItem: String) {
        progressBar.visibility = ProgressBar.VISIBLE
        GlobalScope.launch(Dispatchers.IO) {
            val repositories = GitHubApiClient.api.getUserRepositories(username)
            val filteredRepositories = when (selectedItem) {
                "All" -> repositories
                "Public" -> repositories.filter { it.private == false }
                "Forks" -> repositories.filter { it.fork }
                "Archived" -> repositories.filter { it.archived }
                else -> repositories
            }
            launch(Dispatchers.Main) {
                progressBar.visibility = ProgressBar.GONE
                repositoriesNumber.text = getString(R.string.repositories_number, filteredRepositories.size)
                repositoryAdapter.updateData(filteredRepositories)
            }
        }
    }
}