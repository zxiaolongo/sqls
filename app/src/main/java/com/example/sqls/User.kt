package com.example.sqls

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.lang.reflect.Constructor

@Entity   //默认情况下，Room 将类名称用作数据库表名称。
data class User(
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "first_name") val firstName: String?,
    @ColumnInfo(name = "last_name") val lastName: String?,
    val sex: String?
) {
    constructor(
        id: Int,
        firstName: String?,
        lastName: String?,
    ) : this(id, firstName, lastName, null)
}

