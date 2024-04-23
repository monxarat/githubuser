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

package com.clcosmos.githubuser.api

import com.clcosmos.githubuser.api.entity.GitHubRepository
import com.clcosmos.githubuser.api.entity.GitHubUser
import com.clcosmos.githubuser.api.entity.GitHubUserDetail
import com.clcosmos.githubuser.api.entity.LanguageResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Url

/**
 * Interface representing the GitHub API.
 */
interface GitHubApi {
    /**
     * Fetches a list of GitHub users.
     *
     * @return A list of GitHub users.
     */
    @GET("users")
    suspend fun getUsers(): List<GitHubUser>

    /**
     * Fetches the details of a specific GitHub user.
     *
     * @param login The username of the GitHub user.
     * @return The details of the GitHub user.
     */
    @GET("users/{login}")
    suspend fun getUserDetail(@Path("login") login: String): GitHubUserDetail

    /**
     * Fetches the repositories of a specific GitHub user.
     *
     * @param login The username of the GitHub user.
     * @return A list of the GitHub user's repositories.
     */
    @GET("users/{login}/repos?per_page=100")
    suspend fun getUserRepositories(@Path("login") login: String): List<GitHubRepository>

    /**
     * Fetches the languages used in a specific GitHub repository.
     *
     * @param url The URL of the GitHub repository.
     * @return The languages used in the GitHub repository.
     */
    @GET
    fun getLanguages(@Url url: String): Call<LanguageResponse>
}