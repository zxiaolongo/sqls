package com.example.sqls

import android.content.Context
import android.util.Log
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

@Database(entities = [User::class], version = 2)
abstract class UserDataBase : RoomDatabase() {
    companion object {
        private val TAG: String? = UserDataBase::class.simpleName
        private var instance: UserDataBase? = null
        fun get(context: Context): UserDataBase {
            if (instance == null) {
                instance = Room.databaseBuilder(context, UserDataBase::class.java, "user_.db")
                    //是否允许在主线程进行查询
//                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()// 设置升级策略，失败会回滚到上一个版本
                    .addMigrations(ADD_FIELD_MIGRATION_1_2)
                    .addCallback(object : Callback() {
                        override fun onCreate(db: SupportSQLiteDatabase) {
                            super.onCreate(db)
                            Log.e(TAG, "onCreate db_name is=" + db.path)
                        }
                    })
                    .build()
            }
            return instance!!
        }
        /**
         * 增加字段升级(增加一个String 类型 的字段sex)
         */
        private val ADD_FIELD_MIGRATION_1_2 = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("ALTER TABLE User ADD COLUMN sex Text")
            }
        }
    }

    abstract fun userDao(): UserDao
}






