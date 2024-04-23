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
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.clcosmos.githubuser.R
import com.clcosmos.githubuser.api.entity.GitHubRepository
import com.clcosmos.githubuser.view.ViewColor
import java.util.Locale

/**
 * Adapter for displaying GitHub repositories in a RecyclerView.
 *
 * @property context The context in which the adapter is being used.
 * @property repositories The list of GitHub repositories to display.
 */
class RepositoryAdapter(private var context: Context, private var repositories: MutableList<GitHubRepository>) :
    RecyclerView.Adapter<RepositoryAdapter.ViewHolder>() {
    private var filteredRepositories: MutableList<GitHubRepository> = repositories

    /**
     * ViewHolder for displaying a GitHub repository.
     *
     * @property itemView The view representing a single GitHub repository.
     */
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val nameTextView: TextView = itemView.findViewById(R.id.nameTextView)
        private val imgLangColor: ViewColor = itemView.findViewById(R.id.img_lang_color)
        private val languageTextView: TextView = itemView.findViewById(R.id.languageTextView)
        private val starsTextView: TextView = itemView.findViewById(R.id.starsTextView)
        private val descriptionTextView: TextView = itemView.findViewById(R.id.descriptionTextView)
        private val issueTextView: TextView = itemView.findViewById(R.id.issueTextView)
        private val pullRequestTextView: TextView = itemView.findViewById(R.id.pullRequestTextView)
        private val visibilityTextView: TextView = itemView.findViewById(R.id.visibilityTextView)

        /**
         * Binds the data of a GitHub repository to the ViewHolder.
         *
         * @param repository The GitHub repository to be displayed.
         */
        fun bind(repository: GitHubRepository) {
            nameTextView.text = repository.fullName
            imgLangColor.setBackgroundColor(Color.parseColor(getColorForLanguage(repository.language ?: "")))
            languageTextView.text = repository.language ?: "Unknown"

            // Hide the stars count if it is 0
            if (repository.stargazersCount == 0) {
                starsTextView.visibility = View.GONE
            } else {
                starsTextView.visibility = View.VISIBLE
                starsTextView.text = repository.stargazersCount.toString()
            }
            descriptionTextView.text = repository.description ?: ""

            // Hide the issue count if it is 0
            if (repository.openIssuesCount == 0) {
                issueTextView.visibility = View.GONE
            } else {
                issueTextView.visibility = View.VISIBLE
                issueTextView.text = (repository.openIssuesCount).toString()
            }

            // Hide the pull request count if it is 0
            if (repository.watchers == 0) {
                pullRequestTextView.visibility = View.GONE
            } else {
                pullRequestTextView.visibility = View.VISIBLE
                pullRequestTextView.text = (repository.watchers ?: "-").toString()
            }

            // Set the visibility of the repository based on the visibility and archived status
            // If the repository is public, set the visibility to "Public" and set the color to black
            // If the repository is archived, set the visibility to "Public Archived" and set the color to public_archived
            if (repository.visibility.lowercase() == "public") {
                visibilityTextView.text = "Public"
                visibilityTextView.setTextColor(context.getColor(R.color.black))
                visibilityTextView.setBackgroundResource(R.drawable.visibility_round_border)
                if (repository.archived) {
                    visibilityTextView.text = "Public Archived"
                    visibilityTextView.setTextColor(context.getColor(R.color.public_archived))
                    visibilityTextView.setBackgroundResource(R.drawable.visibility_archived_round_border)
                }
            }
        }

        /**
         * Returns the color associated with a programming language.
         *
         * @param language The programming language.
         * @return The color associated with the programming language.
         */
        private fun getColorForLanguage(language: String): String {
            return when (language.lowercase(Locale.ROOT)) {
                "python" -> "#3572a5"
                "javascript" -> "#F7DF1E"
                "java" -> "#b07219"
                "c" -> "#A8B9CC"
                "c++" -> "#00599C"
                "c#" -> "#68217A"
                "ruby" -> "#701516"
                "php" -> "#4F5D95"
                "swift" -> "#F05138"
                "objective-c" -> "#438EFF"
                "kotlin" -> "#a97bff"
                "go" -> "#00ADD8"
                "rust" -> "#000000"
                "typescript" -> "#3178c6"
                "matlab" -> "#0076A8"
                "r" -> "#276DC3"
                "perl" -> "#0298C3"
                "lua" -> "#000080"
                "html" -> "#E34F26"
                "css" -> "#563d7c"
                "sql" -> "#FFD700"
                "shell scripting" -> "#4EAA25"
                "dart" -> "#0175C2"
                "scala" -> "#DC322F"
                "haskell" -> "#5D4F85"
                "lisp" -> "#3F85AF"
                "assembly" -> "#6E4C13"
                "visual basic" -> "#9457A1"
                "groovy" -> "#4298B8"
                "tcl" -> "#E4CC98"
                "cobol" -> "#004B87"
                "jupyter notebook" -> "#da5b0b"
                "tex" -> "#3d6117"
                "dockerfile" -> "#384d54"
                else -> "#FFFFFF"
            }
        }
    }

    /**
     * Creates a new ViewHolder for a GitHub repository.
     *
     * @param parent The ViewGroup into which the new View will be added after it is bound to an adapter position.
     * @param viewType The view type of the new View.
     * @return A new ViewHolder that holds a View of the given view type.
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_repository, parent, false)
        return ViewHolder(view)
    }

    /**
     * Binds the data of a GitHub repository to the ViewHolder.
     *
     * @param holder The ViewHolder to be updated.
     * @param position The position of the item within the adapter's data set.
     */
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val repository = filteredRepositories[position]
        holder.bind(repository)
    }

    /**
     * Returns the total number of items in the data set held by the adapter.
     *
     * @return The total number of items in this adapter.
     */
    override fun getItemCount(): Int {
        return filteredRepositories.size
    }

    /**
     * Updates the data of the adapter with new GitHub repositories and notifies the RecyclerView that the data set has changed.
     *
     * @param newRepositories The new GitHub repositories.
     */
    fun updateData(newRepositories: List<GitHubRepository>) {
        filteredRepositories.clear()
        filteredRepositories.addAll(newRepositories)
        notifyDataSetChanged()
    }

    /**
     * Filters the GitHub repositories based on a query and notifies the RecyclerView that the data set has changed.
     *
     * @param text The query to filter the GitHub repositories.
     */
    fun filter(text: String) {
        filteredRepositories = if (text.isBlank()) {
            repositories
        } else {
            repositories.filter { repository ->
                repository.name.contains(text, ignoreCase = true)
            }.toMutableList()
        }
        notifyDataSetChanged()
    }
}