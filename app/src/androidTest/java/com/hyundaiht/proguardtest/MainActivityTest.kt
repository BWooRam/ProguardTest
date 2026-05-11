package com.hyundaiht.proguardtest

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.assertIsDisplayed
import com.hyundaiht.proguardtest.ui.data.User
import com.hyundaiht.proguardtest.ui.theme.ProguardTestTheme
import org.junit.Rule
import org.junit.Test

class MainActivityTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun greeting_displaysCorrectText() {
        // Given
        val testUser = User(name = "AndroidTest", age = 20)

        // When
        composeTestRule.setContent {
            ProguardTestTheme {
                Greeting(dto = testUser)
            }
        }

        // Then
        composeTestRule.onNodeWithText("Hello AndroidTest!").assertIsDisplayed()
    }
}
