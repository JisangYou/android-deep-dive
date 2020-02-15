package com.example.memo.data

import androidx.room.Dao
import androidx.room.Database
import androidx.room.RoomDatabase

/**
 * 앱이 단일 프로세스에서 실행되는 경우 AppDatabase 개체를 인스턴스화할 때 싱글톤 디자인 패턴을 따라야 합니다.
 * 각 RoomDatabase 인스턴스는 리소스를 상당히 많이 소비하며 단일 프로세스 내에서 여러 인스턴스에 액세스할 필요가 거의 없습니다.
 */

@Database(entities = [Memo::class], version = 1)
abstract class MemoDatabase : RoomDatabase() {
    abstract fun memoDatabaseDao(): MemoDatabaseDao

}