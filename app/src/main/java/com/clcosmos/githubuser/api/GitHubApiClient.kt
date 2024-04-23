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

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Singleton object that provides a Retrofit instance for accessing the GitHub API.
 */
object GitHubApiClient {

    /**
     * The base URL for the GitHub API.
     */
    private const val BASE_URL = "https://api.github.com/"

    /**
     * The authorization token for accessing the GitHub API.
     * This token is used for demonstration purposes only. In a real-world application, you should
     *  never hardcode your authorization token in the source code. Instead, you should store it
     *  securely and load it at runtime. For more information, see the following link:
     *  https://developer.android.com/distribute/best-practices/develop/secure-credentials
     *
     * For more information on how to create a personal access token for the GitHub API, see the
     * following link: https://docs.github.com/en/github/authenticating-to-github/keeping-your-account-and-data-secure/creating-a-personal-access-token
     * The token should have the following scopes: "repo", "read:org", and "user". For more
     * information on scopes, see the following link: https://docs.github.com/en/developers/apps/scopes-for-oauth-apps
     *
     */
    private const val AUTH_TOKEN = "<Replace Your Token Here>"

    /**
     * The accept header value for the GitHub API.
     */
    private const val ACCEPT = "application/vnd.github+json"

    /**
     * The Retrofit instance for accessing the GitHub API.
     * The instance is created lazily to avoid unnecessary memory usage. It is created the first
     * time it is accessed and then reused for subsequent calls. For more information on lazy
     * initialization, see the following link: https://kotlinlang.org/docs/reference/delegated-properties.html#lazy-properties
     */
    private val retrofit: Retrofit by lazy {
        val httpClient = OkHttpClient.Builder().apply {
            addInterceptor(Interceptor { chain ->
                val request = chain.request().newBuilder()
                    .addHeader("Authorization", "token $AUTH_TOKEN")
                    .addHeader("Accept", "token $ACCEPT")
                    .build()
                chain.proceed(request)
            })
        }

        // Create a Retrofit instance with the base URL, a Gson converter factory, and an HTTP client
        // that includes an interceptor for adding the authorization token and accept header.
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(httpClient.build())
            .build()
    }

    /**
     * The GitHub API instance for making requests.
     */
    val api: GitHubApi by lazy {
        retrofit.create(GitHubApi::class.java)
    }
}