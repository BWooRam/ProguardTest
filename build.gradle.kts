// Top-level build file where you can add configuration options common to all sub-projects/modules.
// 최상위 빌드 파일로, 모든 하위 프로젝트/모듈에 공통으로 적용되는 설정 옵션을 추가할 수 있습니다.
plugins {
    // Android 애플리케이션 플러그인. apply false는 이 플러그인을 루트 프로젝트에 바로 적용하지 않고, 하위 모듈에서 사용할 수 있도록 선언만 함을 의미합니다.
    alias(libs.plugins.android.application) apply false
    // Kotlin Android 플러그인. Android 프로젝트에서 Kotlin을 사용할 수 있게 해줍니다.
    alias(libs.plugins.kotlin.android) apply false
    // Kotlin Compose 플러그인. Jetpack Compose를 사용하기 위한 Kotlin 컴파일러 플러그인입니다.
    alias(libs.plugins.kotlin.compose) apply false
}
