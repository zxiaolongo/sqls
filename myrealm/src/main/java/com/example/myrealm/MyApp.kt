package com.example.myrealm

import android.app.Application
import io.realm.Realm
import io.realm.RealmConfiguration

class MyApp : Application() {
    companion object {
        lateinit var instance: MyApp
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        Realm.init(this);//初始化realm

        //这时候会创建一个叫做 default.realm的Realm文件，一般来说，
        // 这个文件位于/data/data/包名/files/。通过realm.getPath()来获得该Realm的绝对路径。
//        val mRealm = Realm.getDefaultInstance()


        //这时候会创建一个叫做 default.realm的Realm文件，一般来说，
        // 这个文件位于/data/data/包名/files/。通过realm.getPath()来获得该Realm的绝对路径
//        val config: RealmConfiguration = RealmConfiguration.Builder()
//            .name("myrealm.realm") //文件名
//            .schemaVersion(2) //版本号
//            .addModule(UserModules())
//            .migration(UserMigration())
//            .build()
//        realm = Realm.getInstance(config)

        //config = new RealmConfiguration.Builder()
        //                .name("picceHome.realm")
        //                .schemaVersion(13)
        //                .migration(new MigrationVison())
        //                .modules(new UatModel())
        //                .build();
    }
}