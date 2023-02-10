package com.example.myrealm

import android.widget.Toast
import io.realm.Realm
import io.realm.RealmConfiguration
import io.realm.RealmObject
import io.realm.RealmResults


class RealmHelper private constructor() {
    val DB_NAME = "myRealm.realm"
    //一个Realm只能在同一个线程中访问 即创建它的线程中
    private lateinit var mRealm: Realm

    init {
        val config: RealmConfiguration = RealmConfiguration.Builder()
            .name(DB_NAME) //文件名
            .schemaVersion(2) //版本号
            .addModule(UserModules())
            .migration(UserMigration())
            .allowWritesOnUiThread(true)
            .build()
        mRealm = Realm.getInstance(config)
    }

    companion object {
        private var instance: RealmHelper? = null
        fun getInstanc(): RealmHelper {
            if (instance == null) {
                synchronized(RealmHelper::class.java) {
                    if (instance == null) {
                        instance = RealmHelper()
                    }
                }
            }
            return instance!!
        }
    }

    /**
     *（1）使用executeTransaction方法插入数据
     *（2）使用copyToRealmOrUpdate或copyToRealm方法插入数据
    （3）上面都是用可executeTransaction方法插入数据，
    还有另一种方法可以用于插入数据——beginTransaction和commitTransaction
    （4）使用executeTransactionAsync
     * */
    suspend fun add(vararg users: RealmObject) {
        //您可以通过 Realm#copyToRealm 在单个写入事务中批处理.
        // 如果需要导入JSON数据，有Realm#createOrUpdateAllFromJson(JSONArray).
        mRealm.executeTransaction {
            for (user in users) {
                it.copyToRealmOrUpdate(user)//主键
            }
        }
    }

    suspend fun <T : RealmObject> delete(clazz: Class<T>, vararg ids: Int) {
        //先查找到数据
        //先查找到数据
        mRealm.executeTransaction{
            val qurey = it.where(clazz)
            for (id in ids){
                qurey.or()
                .equalTo("id", id)
            }
            val userList: RealmResults<T> = qurey.findAll()
            for (user in userList) {
                user!!.deleteFromRealm()
            }
        }
    }

    suspend fun update(user: User) {
        mRealm.executeTransaction {
            val user =
                mRealm.where(User::class.java).equalTo("id", user.id).findFirst()
            user?.lastName = "ssss"
        }
    }

    suspend fun <T : RealmObject> query(clazz: Class<T>): List<RealmObject>? {
        var userList: List<RealmObject>? = null;
        mRealm.executeTransaction {
            userList = it.where(clazz).findAll()
        }
        return userList
    }
}
/**
不想自己处理
beginTransaction()
, cancelTransaction()
和commitTransaction(),
可以直接调用realm.executeTransaction()方法:
 */
//同步
/**

realm.executeTransaction(object : Transaction() {
fun execute(realm: Realm) {
val user: User = realm.createObject(User::class.java)
user.setName("John")
user.setEmail("john@corporation.com")
}
})
 */
//异步
/**
realm.executeTransactionAsync(new Realm.Transaction() {
@Override
public void execute(Realm bgRealm) {
User user = bgRealm.createObject(User.class);
user.setName("John");
user.setEmail("john@corporation.com");
}
}, new Realm.Transaction.OnSuccess() {
@Override
public void onSuccess() {
// Transaction was a success.
}
}, new Realm.Transaction.OnError() {
@Override
public void onError(Throwable error) {
// Transaction failed and was automatically canceled.
}
});
 */