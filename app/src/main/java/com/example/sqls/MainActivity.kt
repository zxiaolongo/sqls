package com.example.sqls

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val tvContent = findViewById<TextView>(R.id.tv_content)

        lifecycleScope.launch {
            val users: List<User> = UserDataBase.get(applicationContext).userDao().getAll()
            tvContent.setText(users.toString())
        }

        findViewById<Button>(R.id.bt_add).setOnClickListener {
            val user = User(1,"zhangsan","ss")
            val user2 = User(2,"zhangsan","ss")
            lifecycleScope.launch {
                //取代for(int i=0;i<10;++i)    [1,10]
                UserDataBase.get(applicationContext).userDao().insertAll(user,user2)
                val users: List<User> = UserDataBase.get(applicationContext).userDao().getAll()
                tvContent.setText(users.toString())

            }
        }
        findViewById<Button>(R.id.bt_delate).setOnClickListener {
            val user = User(1,"zhangsan","ss")
            lifecycleScope.launch {
                UserDataBase.get(applicationContext).userDao().delete(user)
                val users: List<User> = UserDataBase.get(applicationContext).userDao().getAll()
                tvContent.setText(users.toString())
            }

        }
        findViewById<Button>(R.id.bt_update).setOnClickListener {
            val user = User(1,"zhangsan","ss_update")
            lifecycleScope.launch {
                UserDataBase.get(applicationContext).userDao().update(user)
                val users: List<User> = UserDataBase.get(applicationContext).userDao().getAll()
                tvContent.setText(users.toString())
            }

        }
        findViewById<Button>(R.id.bt_update_add).setOnClickListener {
            val user = User(1,"zhangsan","ss_update","boy")
            lifecycleScope.launch {
                UserDataBase.get(applicationContext).userDao().insertAll(user)
                val users: List<User> = UserDataBase.get(applicationContext).userDao().getAll()
                tvContent.setText(users.toString())
            }
        }
        findViewById<Button>(R.id.bt_query).setOnClickListener {

        }
    }
}