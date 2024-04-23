# GitHub User Repository Viewer

This Android application is written in Kotlin and Java, and built with Gradle. It allows users to view GitHub user profiles and their repositories.

## Features

- **User Search**: Users can search for any GitHub user by their username.
- **User Details**: The application displays details of the searched user, including their avatar, full name, number of followers, and number of users they are following.
- **Repository List**: The application fetches and displays a list of repositories of the searched user. The list includes all repositories, public repositories, forked repositories, and archived repositories.
- **Repository Filter**: Users can filter the list of repositories based on certain criteria. The filter options include "All", "Public", "Forks", and "Archived".
- **Repository Search**: Users can search for a specific repository within the list of repositories.

## Code Structure

The code is organized into several classes, each with a specific role:

- `MainActivity`: This is the entry point of the application. It handles user search and navigation to the `RepositoryActivity`.
- `RepositoryActivity`: This activity displays the list of repositories for a specific GitHub user. It allows the user to filter the repositories and search for a specific repository.
- `GitHubUserAdapter` and `RepositoryAdapter`: These are the adapters for the `RecyclerView` used to display the list of GitHub users and repositories, respectively.
- `GitHubApiClient`: This is the API client used to fetch data from the GitHub API.
- `GitHubUserDetail`: This is a data class that represents the details of a GitHub user.
- `CustomItemDecorator` and `CustomSpinnerAdapter`: These are custom classes used for decorating the `RecyclerView` and customizing the `Spinner`, respectively.

## How to Run

To run the application, you need to have Android Studio installed. Open the project in Android Studio, build it, and run it on an emulator or a connected Android device.

In the `GitHubApiClient.kt` file, there is a constant variable named `AUTH_TOKEN`. This variable is used to store the GitHub personal access token which is required for making authenticated requests to the GitHub API.

```kotlin
private const val AUTH_TOKEN = "<Replace Your Token Here>"
```

The `AUTH_TOKEN` is included in the header of every HTTP request made to the GitHub API. This is done in the `Interceptor` which is part of the `OkHttpClient`:

```kotlin
addInterceptor(Interceptor { chain ->
    val request = chain.request().newBuilder()
        .addHeader("Authorization", "token $AUTH_TOKEN")
        .addHeader("Accept", "token $ACCEPT")
        .build()
    chain.proceed(request)
})
```

To use this application with your GitHub account, you need to replace `"<Replace Your Token Here>"` with your personal access token from GitHub.

Please note that you should never hardcode your personal access token in your source code. It's recommended to store it securely and load it at runtime. For more information on how to create a personal access token for the GitHub API, you can refer to the [GitHub documentation](https://docs.github.com/en/github/authenticating-to-github/keeping-your-account-and-data-secure/creating-a-personal-access-token).
### Discussion
This application demonstrates how to interact with the GitHub API using Retrofit and display the data in an Android application. 
It showcases the use of Retrofit for making network requests, Gson for parsing JSON responses, and RecyclerView for displaying lists of data.

This GitHub User Repository Viewer application is a well-structured project that provides a good foundation for a GitHub client application.
However, there are several areas where it could be improved and additional features that could be added:

1. **Security**: The GitHub personal access token is currently hardcoded in the `GitHubApiClient.kt` file. This is a security risk and it's not a good practice. The token should be stored securely and loaded at runtime. One way to do this is by using the Android Keystore system or storing the token in a server and retrieving it when needed.

2. **Pagination**: The application currently fetches all repositories of a user at once. This could lead to performance issues if a user has a large number of repositories. Implementing pagination would solve this issue. The GitHub API supports pagination, so it would be a matter of updating the `GitHubApiClient` and the `RepositoryAdapter` to handle paginated data.

3. **Error Handling**: The application lacks proper error handling. For example, if fetching the repositories fails, the application should display an error message to the user. Implementing a robust error handling system would improve the user experience.

4. **User Interface**: The user interface of the application is quite basic. It could be improved by implementing a modern, user-friendly design. This could include using Material Design components, adding animations, and improving the layout of the repository list.

5. **Additional Features**: There are several features that could be added to the application to make it more useful. For example, the application could allow users to star/unstar repositories, follow/unfollow users, view the readme of a repository, and view the issues and pull requests of a repository.

6. **Testing**: The application currently lacks tests. Adding unit tests and UI tests would ensure that the application works as expected and would make it easier to add new features or make changes to the existing code.

7. **Documentation**: While the code is well-structured and uses descriptive names for classes, methods, and variables, it lacks comments and documentation. Adding comments to the code and writing documentation would make it easier for other developers to understand and contribute to the project.

### Disclaimer
This application is for educational purposes only and is not intended for production use. 
It is a simple example of how to interact with the GitHub API using Retrofit and display the data in an Android application. 
The use of personal access tokens in this application is for demonstration purposes only, and you should follow best practices for handling sensitive information in your own applications.

### License
This project is licensed under the Apache License, Version 2.0 (the "License");

### Author
This project is authored by [monxarat](Monxarat)