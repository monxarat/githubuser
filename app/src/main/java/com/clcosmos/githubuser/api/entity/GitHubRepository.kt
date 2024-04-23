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

import com.google.gson.annotations.SerializedName

/**
 * Data class representing a GitHub repository.
 *
 * @property id The unique identifier of the repository.
 * @property nodeId The node id of the repository.
 * @property name The name of the repository.
 * @property fullName The full name of the repository.
 * @property private Indicates if the repository is private.
 * @property owner The owner of the repository.
 * @property htmlUrl The HTML URL of the repository.
 * @property description The description of the repository.
 * @property fork Indicates if the repository is a fork.
 * @property url The URL of the repository.
 * @property forksUrl The URL to fetch the forks of the repository.
 * @property keysUrl The URL to fetch the keys of the repository.
 * @property collaboratorsUrl The URL to fetch the collaborators of the repository.
 * @property teamsUrl The URL to fetch the teams of the repository.
 * @property hooksUrl The URL to fetch the hooks of the repository.
 * @property issueEventsUrl The URL to fetch the issue events of the repository.
 * @property eventsUrl The URL to fetch the events of the repository.
 * @property assigneesUrl The URL to fetch the assignees of the repository.
 * @property branchesUrl The URL to fetch the branches of the repository.
 * @property tagsUrl The URL to fetch the tags of the repository.
 * @property blobsUrl The URL to fetch the blobs of the repository.
 * @property gitTagsUrl The URL to fetch the git tags of the repository.
 * @property gitRefsUrl The URL to fetch the git refs of the repository.
 * @property treesUrl The URL to fetch the trees of the repository.
 * @property statusesUrl The URL to fetch the statuses of the repository.
 * @property languagesUrl The URL to fetch the languages of the repository.
 * @property stargazersUrl The URL to fetch the stargazers of the repository.
 * @property contributorsUrl The URL to fetch the contributors of the repository.
 * @property subscribersUrl The URL to fetch the subscribers of the repository.
 * @property subscriptionUrl The URL to fetch the subscription of the repository.
 * @property commitsUrl The URL to fetch the commits of the repository.
 * @property gitCommitsUrl The URL to fetch the git commits of the repository.
 * @property commentsUrl The URL to fetch the comments of the repository.
 * @property issueCommentUrl The URL to fetch the issue comments of the repository.
 * @property contentsUrl The URL to fetch the contents of the repository.
 * @property compareUrl The URL to fetch the compare of the repository.
 * @property mergesUrl The URL to fetch the merges of the repository.
 * @property archiveUrl The URL to fetch the archive of the repository.
 * @property downloadsUrl The URL to fetch the downloads of the repository.
 * @property issuesUrl The URL to fetch the issues of the repository.
 * @property pullsUrl The URL to fetch the pulls of the repository.
 * @property milestonesUrl The URL to fetch the milestones of the repository.
 * @property notificationsUrl The URL to fetch the notifications of the repository.
 * @property labelsUrl The URL to fetch the labels of the repository.
 * @property releasesUrl The URL to fetch the releases of the repository.
 * @property deploymentsUrl The URL to fetch the deployments of the repository.
 * @property createdAt The creation date of the repository.
 * @property updatedAt The update date of the repository.
 * @property pushedAt The push date of the repository.
 * @property gitUrl The git URL of the repository.
 * @property sshUrl The SSH URL of the repository.
 * @property cloneUrl The clone URL of the repository.
 * @property svnUrl The SVN URL of the repository.
 * @property homepage The homepage of the repository.
 * @property size The size of the repository.
 * @property stargazersCount The count of stargazers of the repository.
 * @property watchersCount The count of watchers of the repository.
 * @property language The main language of the repository.
 * @property hasIssues Indicates if the repository has issues.
 * @property hasProjects Indicates if the repository has projects.
 * @property hasDownloads Indicates if the repository has downloads.
 * @property hasWiki Indicates if the repository has a wiki.
 * @property hasPages Indicates if the repository has pages.
 * @property hasDiscussions Indicates if the repository has discussions.
 * @property forksCount The count of forks of the repository.
 * @property mirrorUrl The mirror URL of the repository.
 * @property archived Indicates if the repository is archived.
 * @property disabled Indicates if the repository is disabled.
 * @property openIssuesCount The count of open issues of the repository.
 * @property license The license of the repository.
 * @property allowForking Indicates if the repository allows forking.
 * @property isTemplate Indicates if the repository is a template.
 * @property webCommitSignoffRequired Indicates if the repository requires web commit signoff.
 * @property topics The topics of the repository.
 * @property visibility The visibility of the repository.
 * @property forks The count of forks of the repository.
 * @property openIssues The count of open issues of the repository.
 * @property watchers The count of watchers of the repository.
 * @property defaultBranch The default branch of the repository.
 */
data class GitHubRepository(
    val id: Long,
    @SerializedName("node_id") val nodeId: String,
    val name: String,
    @SerializedName("full_name") val fullName: String,
    val private: Boolean,
    val owner: Owner,
    @SerializedName("html_url") val htmlUrl: String,
    val description: String?,
    val fork: Boolean,
    val url: String,
    @SerializedName("forks_url") val forksUrl: String,
    @SerializedName("keys_url") val keysUrl: String,
    @SerializedName("collaborators_url") val collaboratorsUrl: String,
    @SerializedName("teams_url") val teamsUrl: String,
    @SerializedName("hooks_url") val hooksUrl: String,
    @SerializedName("issue_events_url") val issueEventsUrl: String,
    @SerializedName("events_url") val eventsUrl: String,
    @SerializedName("assignees_url") val assigneesUrl: String,
    @SerializedName("branches_url") val branchesUrl: String,
    @SerializedName("tags_url") val tagsUrl: String,
    @SerializedName("blobs_url") val blobsUrl: String,
    @SerializedName("git_tags_url") val gitTagsUrl: String,
    @SerializedName("git_refs_url") val gitRefsUrl: String,
    @SerializedName("trees_url") val treesUrl: String,
    @SerializedName("statuses_url") val statusesUrl: String,
    @SerializedName("languages_url") val languagesUrl: String,
    @SerializedName("stargazers_url") val stargazersUrl: String,
    @SerializedName("contributors_url") val contributorsUrl: String,
    @SerializedName("subscribers_url") val subscribersUrl: String,
    @SerializedName("subscription_url") val subscriptionUrl: String,
    @SerializedName("commits_url") val commitsUrl: String,
    @SerializedName("git_commits_url") val gitCommitsUrl: String,
    @SerializedName("comments_url") val commentsUrl: String,
    @SerializedName("issue_comment_url") val issueCommentUrl: String,
    @SerializedName("contents_url") val contentsUrl: String,
    @SerializedName("compare_url") val compareUrl: String,
    @SerializedName("merges_url") val mergesUrl: String,
    @SerializedName("archive_url") val archiveUrl: String,
    @SerializedName("downloads_url") val downloadsUrl: String,
    @SerializedName("issues_url") val issuesUrl: String,
    @SerializedName("pulls_url") val pullsUrl: String,
    @SerializedName("milestones_url") val milestonesUrl: String,
    @SerializedName("notifications_url") val notificationsUrl: String,
    @SerializedName("labels_url") val labelsUrl: String,
    @SerializedName("releases_url") val releasesUrl: String,
    @SerializedName("deployments_url") val deploymentsUrl: String,
    @SerializedName("created_at") val createdAt: String,
    @SerializedName("updated_at") val updatedAt: String,
    @SerializedName("pushed_at") val pushedAt: String,
    @SerializedName("git_url") val gitUrl: String,
    @SerializedName("ssh_url") val sshUrl: String,
    @SerializedName("clone_url") val cloneUrl: String,
    @SerializedName("svn_url") val svnUrl: String,
    val homepage: String?,
    val size: Int,
    @SerializedName("stargazers_count") val stargazersCount: Int,
    @SerializedName("watchers_count") val watchersCount: Int,
    val language: String?,
    @SerializedName("has_issues") val hasIssues: Boolean,
    @SerializedName("has_projects") val hasProjects: Boolean,
    @SerializedName("has_downloads") val hasDownloads: Boolean,
    @SerializedName("has_wiki") val hasWiki: Boolean,
    @SerializedName("has_pages") val hasPages: Boolean,
    @SerializedName("has_discussions") val hasDiscussions: Boolean,
    @SerializedName("forks_count") val forksCount: Int,
    @SerializedName("mirror_url") val mirrorUrl: String?,
    val archived: Boolean,
    val disabled: Boolean,
    @SerializedName("open_issues_count") val openIssuesCount: Int,
    val license: License?,
    @SerializedName("allow_forking") val allowForking: Boolean,
    @SerializedName("is_template") val isTemplate: Boolean,
    @SerializedName("web_commit_signoff_required") val webCommitSignoffRequired: Boolean,
    val topics: List<String>,
    val visibility: String,
    val forks: Int,
    val openIssues: Int,
    val watchers: Int,
    @SerializedName("default_branch") val defaultBranch: String
)

/**
 * Data class representing the owner of a GitHub repository.
 *
 * @property login The username of the owner.
 * @property id The unique identifier of the owner.
 * @property nodeId The node id of the owner.
 * @property avatarUrl The URL of the owner's avatar.
 * @property gravatarId The gravatar id of the owner.
 * @property url The URL of the owner's GitHub profile.
 * @property htmlUrl The HTML URL of the owner's GitHub profile.
 * @property followersUrl The URL to fetch the followers of the owner.
 * @property followingUrl The URL to fetch the users the owner is following.
 * @property gistsUrl The URL to fetch the gists of the owner.
 * @property starredUrl The URL to fetch the repositories the owner has starred.
 * @property subscriptionsUrl The URL to fetch the subscriptions of the owner.
 * @property organizationsUrl The URL to fetch the organizations the owner is part of.
 * @property reposUrl The URL to fetch the repositories of the owner.
 * @property eventsUrl The URL to fetch the events of the owner.
 * @property receivedEventsUrl The URL to fetch the events received by the owner.
 * @property type The type of the owner (User or Organization).
 * @property siteAdmin Indicates if the owner is a site administrator.
 */
data class Owner(
    val login: String,
    val id: Long,
    @SerializedName("node_id") val nodeId: String,
    @SerializedName("avatar_url") val avatarUrl: String,
    @SerializedName("gravatar_id") val gravatarId: String,
    val url: String,
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
    val type: String,
    @SerializedName("site_admin") val siteAdmin: Boolean
)

/**
 * Data class representing the license of a GitHub repository.
 *
 * @property key The key of the license.
 * @property name The name of the license.
 * @property spdxId The SPDX identifier of the license.
 * @property url The URL of the license.
 * @property nodeId The node id of the license.
 */
data class License(
    val key: String,
    val name: String,
    @SerializedName("spdx_id") val spdxId: String,
    val url: String,
    @SerializedName("node_id") val nodeId: String
)
