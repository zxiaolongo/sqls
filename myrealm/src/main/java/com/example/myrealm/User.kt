package com.example.myrealm

import io.realm.RealmObject
import io.realm.annotations.Ignore
import io.realm.annotations.PrimaryKey
import io.realm.annotations.Required

open class User : RealmObject() {
    @PrimaryKey
    var id: Int = 0
    var firstName: String? = null
    @Ignore
    var lastName: String? = null
    @Required
    var age: String? = "1"
}
//Realm 目前不支持数据类。你可以在这里看到一个如何在 Kotlin 中编写 Realm 兼容模型类的示例：
//https://github.com/realm/realm-java/tree/master/examples/kotlinExample/src/main/kotlin/io/realm/examples/kotlin/model
