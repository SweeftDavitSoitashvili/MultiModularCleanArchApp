import Versions.appcompat_version
import Versions.constraint_version
import Versions.core_version
import Versions.coroutine_version
import Versions.koin_version
import Versions.materials_version
import Versions.nav_version
import Versions.retrofit_version
import Versions.room_version

object Versions {
    val koin_version = "3.1.4"
    val coroutine_version = "1.6.0-RC"
    val nav_version = "2.3.5"
    val constraint_version = "2.1.2"
    val core_version = "1.7.0"
    val appcompat_version = "1.4.0"
    val materials_version = "1.4.0"
    val room_version = "2.4.0-alpha04"
    val retrofit_version = "2.9.0"
}

object Dependencies {
    val koin = "io.insert-koin:koin-android:$koin_version"
    val coroutine = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${coroutine_version}"
    val nav_dynamic_features = "androidx.navigation:navigation-dynamic-features-fragment:$nav_version"
    val constraint = "androidx.constraintlayout:constraintlayout:$constraint_version"
    val core = "androidx.core:core-ktx:$core_version"
    val appcompat = "androidx.appcompat:appcompat:$appcompat_version"
    val materials = "com.google.android.material:material:$materials_version"
    val room = "androidx.room:room-ktx:$room_version"
    val room_compiler = "androidx.room:room-compiler:$room_version"
    val retrofit =  "com.squareup.retrofit2:retrofit:$retrofit_version"
    val gson_factory = "com.squareup.retrofit2:converter-gson:$retrofit_version"
}
