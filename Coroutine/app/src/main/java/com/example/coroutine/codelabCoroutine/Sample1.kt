package com.example.coroutine.codelabCoroutine

fun main() {
//    sample1()
    sample2()
}

fun sample1() {
    val thread = Thread {
        println("${Thread.currentThread()} has run.")
    }
    thread.start()
}

fun sample2() {
    val states = arrayOf("Starting", "Doing Task 1", "Doing Task 2", "Ending")
    repeat(3) {
        Thread {
            println("${Thread.currentThread()} has started")
            for (i in states) {
                println("${Thread.currentThread()} - $i")
                Thread.sleep(50)
            }
        }.start()
    }
}
