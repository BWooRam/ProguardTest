package com.hyundaiht.proguardtest.ui.data

import org.junit.Assert.assertEquals
import org.junit.Test

class DataClassesTest {

    @Test
    fun testUserCreation() {
        val user = User(name = "Alice", age = 25)
        assertEquals("Alice", user.name)
        assertEquals(25, user.age)
    }

    @Test
    fun testUserDefaultAge() {
        val user = User(name = "Bob")
        assertEquals("Bob", user.name)
        assertEquals(0, user.age)
    }

    @Test
    fun testTestCreation() {
        val test = com.hyundaiht.proguardtest.ui.data.Test(name = "Test1", age = 10)
        assertEquals("Test1", test.name)
        assertEquals(10, test.age)
    }

    @Test
    fun testTestDefaultAge() {
        val test = com.hyundaiht.proguardtest.ui.data.Test(name = "Test2")
        assertEquals("Test2", test.name)
        assertEquals(0, test.age)
    }

    @Test
    fun testTest2Creation() {
        val test2 = Test2(name = "Test3", age = 30)
        assertEquals("Test3", test2.name)
        assertEquals(30, test2.age)
    }

    @Test
    fun testTest2DefaultAge() {
        val test2 = Test2(name = "Test4")
        assertEquals("Test4", test2.name)
        assertEquals(0, test2.age)
    }
}
