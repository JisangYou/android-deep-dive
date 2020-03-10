package com.example.memo.data

import android.content.Context
import android.util.Log
import androidx.room.*
import kotlinx.coroutines.CoroutineScope

@Database(entities = [Memo::class], version = 2, exportSchema = false)
@TypeConverters(Converters::class)
abstract class MemoDatabase : RoomDatabase() {
    abstract fun memoDao(): MemoDao

    companion object {
        /**
         * Java 변수를 Main Memory에 저장하겠다라는 것을 명시
         * Main Memory에 read & write를 보장하는 키워드
         *
         *
         * 앱이 단일 프로세스에서 실행되는 경우 AppDatabase 개체를 인스턴스화할 때 싱글톤 디자인 패턴을 따라야 합니다.
         * 각 RoomDatabase 인스턴스는 리소스를 상당히 많이 소비하며 단일 프로세스 내에서 여러 인스턴스에 액세스할 필요가 거의 없습니다.
         *
         *
         */
        @Volatile
        private var INSTANCE: MemoDatabase? = null

        fun getInstance(context: Context): MemoDatabase {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        MemoDatabase::class.java,
                        "memo_history_database"
                    )
                        .fallbackToDestructiveMigration().enableMultiInstanceInvalidation().build()
                    INSTANCE = instance
                }
                return instance
            }
        }

        fun destroyInstance() {
            INSTANCE = null
        }
    }
}