package com.ehsansetayesh.core.test.unittest

import kotlinx.coroutines.test.runTest
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext

/**
 * This framework provides a set of functions that enforce the Given-When-Then structure, making test cases more readable and maintainable.
 * The testCase() function takes a lambda expression that configures the given(), whenever(), and then() methods of the test case.
 * The coroutineTestCase() function takes an additional context argument, which specifies the coroutine context in which the test case should be executed.
 * Both functions invoke the given(), whenever(), and then() methods of the test case instance, and throw a NotImplementedError if the then() or whenever() methods are not implemented.
 */
inline fun testCase(testCase: TestCase.() -> Unit) {
    TestCase().apply(testCase).apply {
        given?.invoke()
        whenever?.invoke() ?: throw NotImplementedError("You need to implement whenever")
        then?.invoke() ?: throw NotImplementedError("You need to implement then")
    }
}

inline fun coroutineTestCase(
    context: CoroutineContext = EmptyCoroutineContext,
    crossinline testCase: SuspendTestCase.() -> Unit
) {
    runTest(context) {
        SuspendTestCase().apply(testCase).apply {
            given?.invoke(this@runTest)
            whenever?.invoke(this@runTest) ?: throw NotImplementedError("You need to implement whenever")
            then?.invoke(this@runTest) ?: throw NotImplementedError("You need to implement then")
        }
    }
}