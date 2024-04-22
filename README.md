# LydiaTechnicalTest

## Getting Started
The application is in **Kotlin 1.9**.  
The views are mainly designed with **Jetpack Compose**.
The architecture of the application is **MVI** (Model - View - Intent).

## Architecture detail

The main idea of this architecture is to make clear separation between UI and logic.
A view model is created to generate data states describing the **View** data.
An **Interaction** is an action that can be fired from the UI to the view model.
An **Event** is an action that can be fired from the view model to the UI.

For each screen, there is the following architecture :

```
xxx
└── XxxScreen.kt
    ├── logic
    │   ├── XxxEvent.kt
    │   ├── XxxInteraction.kt
    │   └── XxxViewModel.kt
    └── views
        └── Xxx.kt

```

## Library

### Room

I opted for the Room library instead of SQLDelight in this project primarily because I had never
used Room before and wanted to experiment with this technology.
While SQLDelight is a solid solution that I have experimented with in my previous project, I chose
to delve into the Room ecosystem to explore the solutions it offers.

### Paging

I chose Paging3 because I had never used it before. In previous projects, I typically implemented
pagination from scratch, without utilizing specialized libraries.
However, I found that Paging3 offers significant advantages over manual pagination implementation,
particularly in its ability to mediate between remote and local data sources seamlessly.

### Retrofit / Okhttp

I preferred to use Retrofit/OkHttp rather than Ktor for network calls in this project.
Ktor is a powerful and modern library for network calls on the Kotlin platform, but I chose
Retrofit/OkHttp because of my familiarity with these libraries.
Additionally, I find that these libraries are quicker to integrate into a project of this size.

### Hilt

I chose Hilt because I had already had the opportunity to use it in previous projects and I was very
satisfied with it.

### Other

- Hilt: A dependency injection library for Android, based on Dagger 2, facilitating dependency
  injection in your application.
- AndroidX Hilt Navigation Compose: Integration between AndroidX Navigation and Hilt, making
  dependency injection easy in Jetpack Compose navigation destinations.
- Coil: An image loading library for Android, offering simple and efficient usage to display images
  from URLs, files, or resources.
- Coil-compose: An extension of Coil for Jetpack Compose, allowing easy image loading in composed
  user interfaces.
- AndroidX Navigation Compose: A library for navigation between destinations in a Jetpack Compose
  Android application, using a navigation graph defined in Kotlin.
- AndroidX Compose: Jetpack Compose's toolkit for creating Android user interfaces declaratively.
- AndroidX Room: A data persistence library that simplifies access to SQLite on Android by using
  annotations to define SQL queries and code generation.
- Retrofit: A library for creating HTTP clients on Android and Java, simplifying asynchronous
  network calls.
- Material3: An update to Material Design, bringing new design guidelines and components for Android
  applications.
- Moshi: A JSON serialization and deserialization library for Android and Java, offering simple and
  efficient usage for working with JSON data.
- AndroidX Paging: A library for data pagination in an Android application, facilitating the gradual
  loading of large datasets.
- AndroidX Room Paging: A Room extension that makes it easy to integrate pagination with data stored
  in a Room database.
- MockK: A mocking library for unit tests in Kotlin, allowing easy creation of mock objects for test
  dependencies.
- Kotlinx Coroutines Test: A testing library for Kotlin coroutines, enabling efficient testing of
  asynchronous code using Kotlin coroutines.

| Library                                       | Version    |
|-----------------------------------------------|------------|
| androidx.core:core-ktx                        | 1.13.0     |
| androidx.paging:paging-runtime-ktx            | 3.2.1      |
| androidx.room:room-compiler                   | 2.6.1      |
| androidx.room:room-runtime                    | 2.6.1      |
| androidx.room:room-paging                     | 2.6.1      |
| androidx.paging:paging-compose                | 3.2.1      |
| com.google.dagger:hilt-android                | 2.49       |
| com.google.dagger:hilt-android-compiler       | 2.49       |
| androidx.hilt:hilt-navigation-compose         | 1.2.0      |
| junit:junit                                   | 4.13.2     |
| androidx.test.ext:junit                       | 1.1.5      |
| androidx.test.espresso:espresso-core          | 3.5.1      |
| androidx.lifecycle:lifecycle-runtime-ktx      | 2.7.0      |
| androidx.activity:activity-compose            | 1.9.0      |
| androidx.compose:compose-bom                  | 2024.04.01 |
| com.squareup.moshi:moshi-kotlin               | 1.14.0     |
| com.squareup.okhttp3:logging-interceptor      | 4.10.0     |
| com.squareup.retrofit2:retrofit               | 2.9.0      |
| com.squareup.retrofit2:converter-moshi        | 2.9.0      |
| androidx.fragment:fragment-ktx                | 1.6.2      |
| androidx.core:core-splashscreen               | 1.0.1      |
| androidx.navigation:navigation-compose        | 2.7.7      |
| androidx.room:room-common                     | 2.6.1      |
| androidx.room:room-ktx                        | 2.6.1      |
| io.coil-kt:coil                               | 2.4.0      |
| io.coil-kt:coil-compose-base                  | 2.4.0      |
| io.mockk:mockk                                | 1.13.5     |
| org.jetbrains.kotlinx:kotlinx-coroutines-test | 1.7.1      |
