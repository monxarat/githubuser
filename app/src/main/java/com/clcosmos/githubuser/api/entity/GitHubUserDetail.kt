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

package com.clcosmos.githubuser.api.entity

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

/**
 * Data class representing detailed information about a GitHub user.
 *
 * @property login The username of the user.
 * @property id The unique identifier of the user.
 * @property nodeId The node id of the user.
 * @property avatarUrl The URL of the user's avatar.
 * @property gravatarId The gravatar id of the user.
 * @property url The URL of the user's GitHub profile.
 * @property htmlUrl The HTML URL of the user's GitHub profile.
 * @property followersUrl The URL to fetch the followers of the user.
 * @property followingUrl The URL to fetch the users the user is following.
 * @property gistsUrl The URL to fetch the gists of the user.
 * @property starredUrl The URL to fetch the repositories the user has starred.
 * @property subscriptionsUrl The URL to fetch the subscriptions of the user.
 * @property organizationsUrl The URL to fetch the organizations the user is part of.
 * @property reposUrl The URL to fetch the repositories of the user.
 * @property eventsUrl The URL to fetch the events of the user.
 * @property receivedEventsUrl The URL to fetch the events received by the user.
 * @property type The type of the user (User or Organization).
 * @property siteAdmin Indicates if the user is a site administrator.
 * @property name The name of the user.
 * @property company The company the user works for.
 * @property blog The URL of the user's blog.
 * @property location The location of the user.
 * @property email The email of the user.
 * @property hireable Indicates if the user is available for hire.
 * @property bio The bio of the user.
 * @property twitterUsername The Twitter username of the user.
 * @property publicRepos The number of public repositories of the user.
 * @property publicGists The number of public gists of the user.
 * @property followers The number of followers of the user.
 * @property following The number of users the user is following.
 * @property createdAt The date and time when the user's account was created.
 * @property updatedAt The date and time when the user's account was last updated.
 */
@Parcelize
data class GitHubUserDetail(
    @SerializedName("login") val login: String,
    @SerializedName("id") val id: Int,
    @SerializedName("node_id") val nodeId: String,
    @SerializedName("avatar_url") val avatarUrl: String,
    @SerializedName("gravatar_id") val gravatarId: String,
    @SerializedName("url") val url: String,
    @SerializedName("html_url") val htmlUrl: String,
    @SerializedName("followers_url") val followersUrl: String,
    @SerializedName("following_url") val followingUrl: String,
    @SerializedName("gists_url") val gistsUrl: String,
    @SerializedName("starred_url") val starredUrl: String,
    @SerializedName("subscriptions_url") val subscriptionsUrl: String,
    @SerializedName("organizations_url") val organizationsUrl: String,
    @SerializedName("repos_url") val reposUrl: String,
    @SerializedName("events_url") val eventsUrl: String,
    @SerializedName("received_events_url") val receivedEventsUrl: String,
    @SerializedName("type") val type: String,
    @SerializedName("site_admin") val siteAdmin: Boolean,
    @SerializedName("name") val name: String?,
    @SerializedName("company") val company: String?,
    @SerializedName("blog") val blog: String?,
    @SerializedName("location") val location: String?,
    @SerializedName("email") val email: String?,
    @SerializedName("hireable") val hireable: Boolean?,
    @SerializedName("bio") val bio: String?,
    @SerializedName("twitter_username") val twitterUsername: String?,
    @SerializedName("public_repos") val publicRepos: Int,
    @SerializedName("public_gists") val publicGists: Int,
    @SerializedName("followers") val followers: Int,
    @SerializedName("following") val following: Int,
    @SerializedName("created_at") val createdAt: String,
    @SerializedName("updated_at") val updatedAt: String
): Parcelable
