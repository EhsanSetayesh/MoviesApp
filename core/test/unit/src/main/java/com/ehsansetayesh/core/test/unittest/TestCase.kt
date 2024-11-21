package com.ehsansetayesh.core.test.unittest

import kotlinx.coroutines.test.TestScope

/**
 * The TestCase and SuspendTestCase classes provide a structured way to define test cases using the Given-When-Then pattern.
 * The given() method sets up the initial state of the test.
 * The whenever() method performs the action being tested.
 * The then() method verifies the expected results.
 * The SuspendTestCase class allows suspend functions to be used in the different phases of the test case, making it suitable for testing asynchronous code utilize Coroutines.
 */
class TestCase {

    var given: (() -> Unit)? = null
    var whenever: (() -> Unit)? = null
    var then: (() -> Unit)? = null

    fun given(block: () -> Unit) {
        given = block
    }

    fun whenever(block: () -> Unit) {
        whenever = block
    }

    fun <T> whenever(input: T, block: T.() -> Unit) {
        whenever = {
            block(input)
        }
    }

    fun then(block: () -> Unit) {
        then = block
    }
}

class SuspendTestCase {

    var given: (suspend TestScope.() -> Unit)? = null
    var whenever: (suspend TestScope.() -> Unit)? = null
    var then: (suspend TestScope.() -> Unit)? = null

    fun given(block: suspend TestScope.() -> Unit) {
        given = block
    }

    fun whenever(block: suspend TestScope.() -> Unit) {
        whenever = block
    }

    fun <T> whenever(input: T, block: suspend T.() -> Unit) {
        whenever = {
            block(input)
        }
    }

    fun then(block: suspend TestScope.() -> Unit) {
        then = block
    }
}