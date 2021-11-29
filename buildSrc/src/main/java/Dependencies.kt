import Versions.koin_version

object Versions {
    val koin_version = "3.1.4"
    val coroutine_version = "1.6.0-RC"
}

object Dependencies {
    val koin = "io.insert-koin:koin-android:$koin_version"
    val coroutine = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutine_version}"
}