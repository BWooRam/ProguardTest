plugins {
    // Android 애플리케이션 빌드를 위한 플러그인
    alias(libs.plugins.android.application)
    // Kotlin 언어를 Android 프로젝트에서 사용하기 위한 플러그인
    alias(libs.plugins.kotlin.android)
    // Jetpack Compose를 사용하기 위한 Kotlin 컴파일러 플러그인
    alias(libs.plugins.kotlin.compose)
    // 테스트 커버리지 측정을 위한 JaCoCo 플러그인
    jacoco
}

android {
    // 앱의 고유한 네임스페이스 (R 클래스 등이 생성되는 패키지명)
    namespace = "com.hyundaiht.proguardtest"
    // 앱을 컴파일할 때 사용할 Android SDK 버전
    compileSdk = 36

    lint {
        // Lint 에러 발생 시 빌드를 중단할지 여부 (true: 중단함)
        abortOnError = true
        // 릴리즈 빌드 시 Lint 검사를 수행할지 여부(lintVitalRelease 치명적인 오류만 빠르게 검사)
        checkReleaseBuilds = true
        // warningsAsErrors = true // 기본 경고까지 에러로 처리되는 것을 방지

        htmlReport = true
        xmlReport = true
        
        // 커스텀 Lint 규칙(MissingKeepAnnotation)을 명시적으로 에러로 처리하도록 추가
        fatal.add("MissingKeepAnnotation")
    }

    signingConfigs {
        // 릴리즈 빌드를 위한 서명(Signing) 설정
        create("release") {
            // 키스토어 파일 경로 (프로젝트 루트 폴더 기준)
            storeFile = file("C:\\Users\\Z3A211103\\AndroidStudioProjects\\ProguardTest\\app\\key.jks")
            // 키스토어 비밀번호
            storePassword = "123456"
            // 키 별칭(Alias)
            keyAlias = "key0"
            // 키 비밀번호devu
            keyPassword = "123456"
        }
    }

    defaultConfig {
        // 앱의 고유 식별자 (Google Play Store 등에서 앱을 구분하는 ID)
        applicationId = "com.hyundaiht.proguardtest"
        // 앱이 실행될 수 있는 최소 Android SDK 버전
        minSdk = 28
        // 앱이 타겟으로 하는 Android SDK 버전 (이 버전에 맞춰 최적화됨)
        targetSdk = 36
        // 앱의 내부 버전 번호 (업데이트 시마다 증가시켜야 함)
        versionCode = 1
        // 사용자에게 보여지는 앱의 버전 문자열
        versionName = "1.0"

        // 안드로이드 UI 테스트(Instrumentation Test)를 실행하기 위한 러너 설정
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        // 디버그 빌드 설정 (개발 중 테스트용)
        debug {
            // 코드 난독화 및 축소(Minify) 활성화 여부 (true: 활성화)
            isMinifyEnabled = true
            // 테스트 커버리지 측정을 위해 활성화
            enableUnitTestCoverage = true
            enableAndroidTestCoverage = true
            // ProGuard/R8 규칙 파일 설정
            // getDefaultProguardFile: 안드로이드 기본 최적화 규칙 파일
            // "proguard-rules.pro": 프로젝트 커스텀 규칙 파일
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
        // 릴리즈 빌드 설정 (실제 배포용)
        release {
            // 코드 난독화 및 축소(Minify) 활성화 여부 (true: 활성화)
            isMinifyEnabled = true
            // ProGuard/R8 규칙 파일 설정
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            // 위에서 정의한 "release" 서명 설정 적용
            signingConfig = signingConfigs.getByName("release")
        }
    }
    
    compileOptions {
        // Java 소스 코드를 컴파일할 때 사용할 Java 버전
        sourceCompatibility = JavaVersion.VERSION_11
        // 생성된 바이트코드가 실행될 Java 버전
        targetCompatibility = JavaVersion.VERSION_11
    }
    
    kotlinOptions {
        // Kotlin 코드를 컴파일할 때 타겟으로 할 JVM 버전
        jvmTarget = "11"
    }
    
    buildFeatures {
        // Jetpack Compose 사용 활성화
        compose = true
    }
}

// JaCoCo 버전 설정
jacoco {
    toolVersion = "0.8.11"
}

// 테스트 커버리지 리포트 생성 태스크 설정 (HTML, XML 형식으로 결과 확인 가능)
tasks.register<JacocoReport>("jacocoTestReport") {
    // 단위 테스트 실행 후 리포트를 생성하도록 의존성 설정
    dependsOn("testDebugUnitTest")

    reports {
        xml.required.set(true)
        html.required.set(true)
        // HTML 리포트가 생성될 경로 지정 (기본값: build/reports/jacoco/jacocoTestReport/html)
        html.outputLocation.set(layout.buildDirectory.dir("reports/jacoco/html"))
    }

    // [방법 1] Include 방식: 특정 패키지(예: data 패키지)만 명시적으로 포함
    val includes = listOf(
        "**/ui/data/*.*"
    )

    // [방법 2] Exclude 방식: 전체에서 UI 및 자동 생성 코드만 제외 (참고용 주석)
    // val excludes = listOf(
    //     "**/R.class",
    //     "**/R\$*.class",
    //     "**/BuildConfig.*",
    //     "**/Manifest*.*",
    //     "**/*Test*.*",
    //     "android/**/*.*",
    //     "**/*\$Lambda\$*.*",
    //     "**/*\$inlined\$*.*",
    //     // UI 및 Compose 관련 클래스 제외
    //     "**/MainActivityKt.*",
    //     "**/MainActivity.*",
    //     "**/MainActivity\$*.*",
    //     "**/ui/theme/*.*",
    //     "**/*Composable*.*"
    // )

    val debugTree = fileTree("${project.buildDir}/tmp/kotlin-classes/debug") {
        include(includes)
        // exclude(excludes) // Exclude 방식을 사용할 때는 이 주석을 해제하고 include(includes)를 주석 처리하세요.
    }
    val mainSrc = "${project.projectDir}/src/main/java"

    sourceDirectories.setFrom(files(mainSrc))
    classDirectories.setFrom(files(debugTree))

    executionData.setFrom(fileTree(project.buildDir) {
        include(
            "outputs/unit_test_code_coverage/debugUnitTest/testDebugUnitTest.exec"
        )
    })
}

// 테스트 커버리지 검증 태스크 설정
tasks.register<JacocoCoverageVerification>("jacocoTestCoverageVerification") {
    // 리포트 생성 후 커버리지 검증을 수행하도록 의존성 설정
    dependsOn("jacocoTestReport")

    // [방법 1] Include 방식: 특정 패키지(예: data 패키지)만 명시적으로 포함
    val includes = listOf(
        "**/ui/data/*.*"
    )

    // [방법 2] Exclude 방식: 전체에서 UI 및 자동 생성 코드만 제외 (참고용 주석)
    // val excludes = listOf(
    //     "**/R.class",
    //     "**/R\$*.class",
    //     "**/BuildConfig.*",
    //     "**/Manifest*.*",
    //     "**/*Test*.*",
    //     "android/**/*.*",
    //     "**/*\$Lambda\$*.*",
    //     "**/*\$inlined\$*.*",
    //     // UI 및 Compose 관련 클래스 제외
    //     "**/MainActivityKt.*",
    //     "**/MainActivity.*",
    //     "**/MainActivity\$*.*",
    //     "**/ui/theme/*.*",
    //     "**/*Composable*.*"
    // )

    val debugTree = fileTree("${project.buildDir}/tmp/kotlin-classes/debug") {
        include(includes)
        // exclude(excludes) // Exclude 방식을 사용할 때는 이 주석을 해제하고 include(includes)를 주석 처리하세요.
    }
    val mainSrc = "${project.projectDir}/src/main/java"

    sourceDirectories.setFrom(files(mainSrc))
    classDirectories.setFrom(files(debugTree))

    executionData.setFrom(fileTree(project.buildDir) {
        include(
            "outputs/unit_test_code_coverage/debugUnitTest/testDebugUnitTest.exec"
        )
    })

    // 커버리지 기준 설정
    violationRules {
        rule {
            // 검증 기준: 전체 코드(BUNDLE)
            element = "BUNDLE"

            // 라인 커버리지가 70% 미만이면 빌드 실패
            limit {
                counter = "LINE"
                value = "COVEREDRATIO"
                minimum = "0.70".toBigDecimal()
            }
            
            // 브랜치(조건문) 커버리지가 70% 미만이면 빌드 실패
            limit {
                counter = "BRANCH"
                value = "COVEREDRATIO"
                minimum = "0.70".toBigDecimal()
            }
        }
    }
}

// 빌드 시 커버리지 검증을 강제하려면 check 태스크에 의존성 추가
tasks.named("check") {
    dependsOn("jacocoTestCoverageVerification")
}

// 빌드 태스크가 구성될 때 실행되는 블록 (Task Configuration Avoidance API 사용 권장)
tasks.withType<Task>().configureEach {
    // assembleRelease 태스크(릴리즈 APK 빌드)가 실행되기 전에 
    // 1. 전체 Lint 검사(lintRelease)
    // 2. 테스트 커버리지 검증(jacocoTestCoverageVerification)
    // 을 먼저 실행하도록 강제합니다.
    if (name == "assembleRelease") {
        dependsOn("jacocoTestCoverageVerification")
    }
}

dependencies {
    // 커스텀 Lint 규칙 모듈(":lint-rules")을 프로젝트의 Lint 검사에 포함
    lintChecks(project(":lint-rules"))

    // AndroidX Core KTX (Kotlin 확장 기능)
    implementation(libs.androidx.core.ktx)
    // AndroidX Lifecycle Runtime KTX (생명주기 관련 Kotlin 확장 기능)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    // Compose와 Activity 통합을 위한 라이브러리
    implementation(libs.androidx.activity.compose)
    // Compose BOM(Bill of Materials) - Compose 관련 라이브러리들의 버전을 일관되게 관리
    implementation(platform(libs.androidx.compose.bom))
    // Compose UI 기본 라이브러리
    implementation(libs.androidx.compose.ui)
    // Compose UI 그래픽 라이브러리
    implementation(libs.androidx.compose.ui.graphics)
    // Compose UI 미리보기(Preview) 도구
    implementation(libs.androidx.compose.ui.tooling.preview)
    // Compose Material 3 디자인 시스템 라이브러리
    implementation(libs.androidx.compose.material3)
    // AndroidX Annotation 라이브러리 (@Keep 등 어노테이션 사용)
    implementation(libs.androidx.annotation)
    // Google Gson 라이브러리 (JSON 파싱/직렬화)
    implementation(libs.gson)
    
    // 단위 테스트(Unit Test)를 위한 JUnit 라이브러리
    testImplementation(libs.junit)
    // 안드로이드 UI 테스트를 위한 AndroidX JUnit 확장 라이브러리
    androidTestImplementation(libs.androidx.junit)
    // 안드로이드 UI 테스트를 위한 Espresso Core 라이브러리
    androidTestImplementation(libs.androidx.espresso.core)
    // UI 테스트 환경에서도 Compose BOM 적용
    androidTestImplementation(platform(libs.androidx.compose.bom))
    // Compose UI 테스트를 위한 JUnit4 라이브러리
    androidTestImplementation(libs.androidx.compose.ui.test.junit4)
    
    // 디버그 환경에서만 사용하는 Compose UI 툴링 라이브러리
    debugImplementation(libs.androidx.compose.ui.tooling)
    // 디버그 환경에서만 사용하는 Compose UI 테스트 매니페스트
    debugImplementation(libs.androidx.compose.ui.test.manifest)
}
