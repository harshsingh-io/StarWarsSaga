
# StarWars
An Android app built with Kotlin, consuming [StarWars API](https://swapi.dev/) to display characters of the popular StarWars Movie. It is built with the MVVM pattern and the latest Material components.

## Tech Stack.
- [Kotlin](https://developer.android.com/kotlin) - Kotlin is a programming language that can run on JVM. Google has announced Kotlin as one of its officially supported programming languages in Android Studio; and the Android community is migrating at a pace from Java to Kotlin
- [Android KTX](https://developer.android.com/kotlin/ktx.html) - Provide concise, idiomatic Kotlin to Jetpack and Android platform APIs.
- [Lifecycle](https://developer.android.com/topic/libraries/architecture/lifecycle) - Perform actions in response to a change in the lifecycle status of another component, such as activities and fragments.
- [Viewmodel](https://developer.android.com/topic/libraries/architecture/viewmodel) -The ViewModel class is designed to store and manage UI-related data in a lifecycle conscious way
- [LiveData](https://developer.android.com/topic/libraries/architecture/livedata) -  A lifecycle-aware data holder with the observer pattern
- [Kotlin Coroutines](https://developer.android.com/kotlin/coroutines) - A concurrency design pattern that you can use on Android to simplify code that executes asynchronously.
- [View Binding](https://developer.android.com/topic/libraries/data-binding/) - Allows you to bind UI components in your layouts to data sources in your app using a declarative format rather than programmatically.
- [Paging 3 library](https://developer.android.com/topic/libraries/architecture/paging/v3-overview) -  The Paging library helps you load and display pages of data from a larger dataset from local storage or over network. This approach allows your app to use both network bandwidth and system resources more efficiently.
- [Retrofit](https://square.github.io/retrofit) -  Retrofit is a REST Client for Java and Android by Square inc under Apache 2.0 license. Its a simple network library that used for network transactions. By using this library we can seamlessly capture JSON response from web service/web API.
- [GSON](https://github.com/square/gson) - JSON Parser,used to parse requests on the data layer for Entities and understands Kotlin non-nullable and default parameters.
- [Flow](https://developer.android.com/kotlin/flow) - In coroutines, a flow is a type that can emit multiple values sequentially, as opposed to suspend functions that return only a single value.
- [Hilt](https://developer.android.com/training/dependency-injection/hilt-android) -  A dependency injection library for Android that reduces the boilerplate of doing manual dependency injection in your project
- [Navigation Components](https://developer.android.com/guide/navigation/navigation-getting-started) -  Helps you implement navigation, from simple button clicks to more complex patterns, such as app bars and the navigation drawer.
- [Logging Interceptor](https://github.com/square/okhttp/blob/master/okhttp-logging-interceptor/README.md) -  logs HTTP request and response data.
- [Material Libarary](https://material.io/develop/android) -  Modular and customizable Material Design UI components for Android
- [GitHub Actions](https://github.com/features/actions) - GitHub Actions makes it easy to automate all your software workflows, now with world-class CI/CD. Build, test, and deploy your code right from GitHub. Make code reviews, branch management, and issue triaging work the way you want.
- [Glide](https://github.com/bumptech/glide)- An image loading and caching library for Android focused on smooth scrolling.
- [Timber](https://github.com/JakeWharton/timber)- A logger with a small, extensible API which provides utility on top of Android's normal Log class.
- [SavedStateHandle](https://developer.android.com/guide/navigation/navigation-programmatic) - Getting results from a destination when navigating from one fragment to another
- [SafeApiCall](https://github.com/harshsingh-io/StarWarsSaga/blob/main/app/src/main/java/com/codeenemy/assignmentapp/network/SafeApiCall.kt) Making safe API calls
 
<h2>🚀 Demo</h2>

<img src="https://github.com/harshsingh-io/StarWarsSaga/blob/main/demoscreen.gif" alt="project-screenshot" width="180" height="320">

<h2>Project Screenshots:</h2>

<img src="https://github.com/harshsingh-io/StarWarsSaga/blob/main/Screenshot_20240112-002139577.jpg" alt="project-screenshot" width="180" height="400"> <img src="https://github.com/harshsingh-io/StarWarsSaga/blob/main/Screenshot_20240112-002157706.jpg" alt="project-screenshot" width="180" height="400"> <img src="https://github.com/harshsingh-io/StarWarsSaga/blob/main/Screenshot_20240112-002217888.jpg" alt="project-screenshot" width="180" height="400"> <img src="https://github.com/harshsingh-io/StarWarsSaga/blob/main/Screenshot_20240112-002227577.jpg" alt="project-screenshot" width="180" height="400"> 

## File Structure MVVM


```markdown
└── codeenemy
    └── assignmentapp
        ├── adapters
        │   ├── CharactersAdapter.kt
        │   └── FilmAdapter.kt
        ├── data
        │   └── datasources
        │       └── CharacterPagingSource.kt
        │   └── repositories
        │       └── CharactersRepository.kt
        ├── di
        │   └── AppModule.kt
        ├── models
        │   ├── Character.kt
        │   ├── FilmResponse.kt
        │   ├── HomeWorldResponse.kt
        │   └── PeopleResponse.kt
        ├── network
        │   ├── ApiService.kt
        │   └── SafeApiCall.kt
        ├── ui
        │   ├── CharactersDetailsFragment.kt
        │   ├── CharactersFragment.kt
        │   ├── MainActivity.kt
        │   └── SplashFragment.kt
        ├── utils
        │   ├── Constants.kt
        │   ├── HideSoftKeyboard.kt
        │   └── Resource.kt
        └── viewmodels
            ├── CharacterDetailsViewModel.kt
            └── CharactersViewModel.kt
        └── StarWarsApp.kt
```


