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

    @Test
    fun testTest3Creation() {
        val test3 = Test3(name = "Test3", age = 30)
        assertEquals("Test3", test3.name)
        assertEquals(30, test3.age)
    }

    @Test
    fun testTest3DefaultAge() {
        val test3 = Test3(name = "Test4")
        assertEquals("Test4", test3.name)
        assertEquals(0, test3.age)
    }

    @Test
    fun testTest4Creation() {
        val test4 = Test4(name = "Test4", age = 40)
        assertEquals("Test4", test4.name)
        assertEquals(40, test4.age)
    }

    @Test
    fun testTest4DefaultAge() {
        val test4 = Test4(name = "Test4")
        assertEquals("Test4", test4.name)
        assertEquals(0, test4.age)
    }

    @Test
    fun testTest5Creation() {
        val test5 = Test5(name = "Test5", age = 50)
        assertEquals("Test5", test5.name)
        assertEquals(50, test5.age)
    }

    @Test
    fun testTest5DefaultAge() {
        val test5 = Test5(name = "Test5")
        assertEquals("Test5", test5.name)
        assertEquals(0, test5.age)
    }

    @Test
    fun testTest6Creation() {
        val test6 = Test6(name = "Test6", age = 60)
        assertEquals("Test6", test6.name)
        assertEquals(60, test6.age)
    }

    @Test
    fun testTest6DefaultAge() {
        val test6 = Test6(name = "Test6")
        assertEquals("Test6", test6.name)
        assertEquals(0, test6.age)
    }
}
