package com.hyundaiht.proguardtest.ui.data

import androidx.annotation.Keep

@Keep
data class Test3(
    val name: String,
    val age: Int = 0
)