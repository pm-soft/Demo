import org.gradle.api.artifacts.dsl.DependencyHandler

object AppDependencies {
    private val kotlinStdLib = "org.jetbrains.kotlin:kotlin-stdlib-jdk8:${Versions.kotlin}"
    private val kotlinCoroutines = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.kotlinx}"

    // android ui
    private val appcompat = "androidx.appcompat:appcompat:${Versions.appcompat}"
    private val coreKtx = "androidx.core:core-ktx:${Versions.coreKtx}"
    private val constraintLayout = "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}"
    private val material = "com.google.android.material:material:${Versions.material}"
    private val navigationFragment = "androidx.navigation:navigation-fragment:${Versions.androidXNavigation}"
    private val navigationUiKtx = "androidx.navigation:navigation-ui-ktx:${Versions.androidXNavigation}"

    // room
    private val roomCompiler = "androidx.room:room-compiler:${Versions.room}"
    private val roomRuntime = "androidx.room:room-runtime:${Versions.room}"

    // test libs
    private val junit = "junit:junit:${Versions.junit}"
    private val extJUnit = "androidx.test.ext:junit:${Versions.extJunit}"
    private val espressoCore = "androidx.test.espresso:espresso-core:${Versions.espresso}"

    val appLibraries = arrayListOf<String>().apply {
        add(kotlinStdLib)
        add(kotlinCoroutines)
        add(coreKtx)
        add(appcompat)
        add(constraintLayout)
        add(material)
        add(navigationFragment)
        add(navigationUiKtx)
        add(roomRuntime)
    }

    val kaptLibraries = arrayListOf<String>().apply {
        add(roomCompiler)
    }

    val androidTestLibraries = arrayListOf<String>().apply {
        add(extJUnit)
        add(espressoCore)
    }

    val testLibraries = arrayListOf<String>().apply {
        add(junit)
    }
}

//util functions for adding the different type dependencies from build.gradle file
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

fun DependencyHandler.androidTestImplementation(list: List<String>) {
    list.forEach { dependency ->
        add("androidTestImplementation", dependency)
    }
}

fun DependencyHandler.testImplementation(list: List<String>) {
    list.forEach { dependency ->
        add("testImplementation", dependency)
    }
}
