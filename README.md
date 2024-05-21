# README

This Android app is built using the MVVM architecture and written in Kotlin. The app utilizes the following libraries:

##  Libraries 
androidx-core-ktx
junit
androidx-junit
androidx-espresso-core
androidx-lifecycle-runtime-ktx
androidx-activity-compose
androidx-compose-bom
androidx-ui
androidx-ui-graphics
androidx-ui-tooling
androidx-ui-tooling-preview
androidx-ui-test-manifest
androidx-ui-test-junit4
androidx-material3
androidx-navigation
accompanist-pager
accompanist-systemuicontroller
coil
coil-svg
coil-gif
retrofit
retrofit-converter-jackson
retrofit-converter-scalars
fasterxml-jackson
converter-gson
okhttp
logging-interceptor
hilt-navigation
hilt-android
hilt-compiler
mockito-core
mockito-kotlin
core-testing
kotlinx-coroutines-test
android-test-core
mockwebserver
Plugins
android-application
jetbrains-kotlin-android
kotlin-android-ksp
dagger-hilt-android

### This app is built using Jetpack Compose, a modern UI framework for Android.

# App Description
The app is designed to display a list of Pokémons, with a pager at the top and a list at the bottom.
The lists are synchronized, so when you scroll one, the other scrolls as well. 
The list at the bottom is paginated and loads 25 items at a time. 
Both lists are scrollable. A haptic feedback indicates when you are scrolling through the list at the bottom.
Clicking on a Pokémon in the pager opens up a detailed view of that Pokémon. 
At the top, there is a search bar to search for a Pokémon by ID or name. 
If the search is successful, a bottom sheet appears with the details of the Pokémon.