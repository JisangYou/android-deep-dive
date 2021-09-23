package com.example.coroutine.codelabCoroutine

import org.junit.Test

class Sample1 {
    // 테스트 코드를 활용해서 쓰레드 관련 테스트를 하는 경우와
    // 직접 main 함수에 출력하는 경우에 결과값이 다름.
    @Test
    fun test1() {
        val thread = Thread {
            println("${Thread.currentThread()} has run ~ ")
        }
        thread.start()
        // 여기 호출을 해야만 한다?
        println("thread test1 start!")
    }

    @Test
    fun test2() {
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

}

