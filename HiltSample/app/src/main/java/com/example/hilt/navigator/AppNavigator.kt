package com.example.hilt.navigator

enum class Screens {
    BUTTONS,
    LOGS
}

// 인터페이스이므로 생성자 삽입을 사용 x
interface AppNavigator {
    fun navigateTo(screens: Screens)
}