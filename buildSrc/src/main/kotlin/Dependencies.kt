import org.gradle.api.artifacts.dsl.DependencyHandler

object Dependencies {
  
  const val composeBom = "androidx.compose:compose-bom:${Versions.composeBom}"
  const val coreKtx = "androidx.core:core-ktx:${Versions.coreKtx}"
  const val lifecycleRuntime = "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.lifecycleRuntime}"
  const val activityCompose = "androidx.activity:activity-compose"
  const val composeUi = "androidx.compose.ui:ui"
  const val composeToolingPreview = "androidx.compose.ui:ui-tooling-preview"
  const val materialYou = "androidx.compose.material3:material3"
  const val materialTwo = "androidx.compose.material:material"
  const val materialIconCore = "androidx.compose.material:material-icons-core"
  const val materialIconExtended = "androidx.compose.material:material-icons-extended"
  const val composeConstraintLayout = "androidx.constraintlayout:constraintlayout-compose:${Versions.composeConstraintLayout}"
  const val viewModelCompose = "androidx.lifecycle:lifecycle-viewmodel-compose:${Versions.viewModelCompose}"
  const val runtimeLiveData = "androidx.compose.runtime:runtime-livedata"
  
  const val junit = "junit:junit:${Versions.junit}"
  const val testJunit = "androidx.test.ext:junit:${Versions.testJunit}"
  const val espressoCore = "androidx.test.espresso:espresso-core:${Versions.espressoCore}"

  const val junit4UiTest = "androidx.compose.ui:ui-test-junit4"
  const val uiTooling = "androidx.compose.ui:ui-tooling"
  const val testManifest = "androidx.compose.ui:ui-test-manifest"

  const val coil = "io.coil-kt:coil-compose:${Versions.coil}"

  // compose navigation
  const val composeNavigation = "androidx.navigation:navigation-compose:${Versions.composeNavigation}"

  // retrofit
  const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
  const val gson = "com.squareup.retrofit2:converter-gson:${Versions.gson}"
  const val interceptor = "com.squareup.okhttp3:logging-interceptor:${Versions.interceptor}"

  // room
  const val roomRuntime = "androidx.room:room-runtime:${Versions.roomRuntime}"
  const val roomCompiler = "androidx.room:room-compiler:${Versions.roomCompiler}"
  const val roomKtx = "androidx.room:room-ktx:${Versions.roomKtx}"

  // datastore preferences
  const val dataStore = "androidx.datastore:datastore-preferences:${Versions.dataStore}"

  // splash-screen
  const val splashScreen = "androidx.core:core-splashscreen:${Versions.splashScreen}"

  // system ui controller
  const val systemUiController = "com.google.accompanist:accompanist-systemuicontroller:${Versions.systemUiController}"


  val kaptLibraries = listOf(
    roomCompiler
  )

  val implementationLibraries = listOf(
    coreKtx,
    lifecycleRuntime,
    activityCompose,
    composeUi,
    composeToolingPreview,
    materialYou,
    materialTwo,
    materialIconCore,
    materialIconExtended,
    composeConstraintLayout,
    viewModelCompose,
    runtimeLiveData,
    coil,
    composeNavigation,
    retrofit,
    gson,
    interceptor,
    roomRuntime,
    roomKtx,
    dataStore,
    splashScreen,
    systemUiController
  )

  val implementationPlatformLibraries = listOf(
    composeBom
  )

  val androidTestImplementationLibraries = listOf(
    composeBom,
    testJunit,
    espressoCore,
    junit4UiTest
  )

  val debugImplementationLibraries = listOf(
    uiTooling,
    testManifest
  )

  val testImplementationLibraries = listOf(
    junit
  )

}

fun DependencyHandler.kapt(list: List<String>) {
  list.forEach { dependency ->
    add("kapt", dependency)
  }
}

fun DependencyHandler.implementation(list: List<String>) {
  list.forEach { dependency ->
    add("implementation", dependency)
  }
}

fun DependencyHandler.implementationPlatform(list: List<String>) {
  list.forEach { dependency ->
    add("implementation", platform(dependency))
  }
}

fun DependencyHandler.androidTestImplementation(list: List<String>) {
  list.forEach { dependency ->
    add("androidTestImplementation", dependency)
  }
}

fun DependencyHandler.debugImplementation(list: List<String>) {
  list.forEach { dependency ->
    add("debugImplementation", dependency)
  }
}

fun DependencyHandler.testImplementation(list: List<String>) {
  list.forEach { dependency ->
    add("testImplementation", dependency)
  }
}