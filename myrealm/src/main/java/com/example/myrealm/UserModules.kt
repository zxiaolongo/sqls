package com.example.myrealm

import io.realm.annotations.RealmModule
//通过使用::class，您可以获得 KClass 的一个实例
//通过使用::class.java，您可以获得 Class 的一个实例
@RealmModule(classes = [User::class])
class UserModules {
}