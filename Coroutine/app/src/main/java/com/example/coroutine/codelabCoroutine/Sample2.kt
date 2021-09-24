package com.example.coroutine.codelabCoroutine

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

fun main() {
    repeat(3) {
        GlobalScope.launch {
            println("Hi from ${Thread.currentThread()}")
        }
    }
}