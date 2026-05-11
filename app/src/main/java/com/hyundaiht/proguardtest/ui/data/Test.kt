package com.hyundaiht.proguardtest.ui.data

import androidx.annotation.Keep

@Keep
data class Test(
    val name: String,
    val age: Int = 0
)