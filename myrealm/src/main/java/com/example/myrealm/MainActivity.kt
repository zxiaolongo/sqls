package com.example.myrealm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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
            val query = RealmHelper.getInstanc().query(User::class.java)
            tvContent.setText(query.toString())
        }

        findViewById<Button>(R.id.bt_add).setOnClickListener {
            lifecycleScope.launch {
                val user = User()
                user.id = 1
                user.firstName = "zhangsna"
                user.lastName = "aaaa"
                val user2 = User()
                user2.id = 2
                user2.firstName = "lisi"
                user2.lastName = "bbbb"
                RealmHelper.getInstanc().add(user, user2)
                val query = RealmHelper.getInstanc().query(User::class.java)
                tvContent.setText(query.toString())
            }
        }
        findViewById<Button>(R.id.bt_delate).setOnClickListener {
            lifecycleScope.launch {
                RealmHelper.getInstanc().delete(User::class.java, 1, 2)
                val query = RealmHelper.getInstanc().query(User::class.java);
                tvContent.setText(query.toString())
            }
        }
        findViewById<Button>(R.id.bt_update).setOnClickListener {
            lifecycleScope.launch {
                val user2 = User()
                user2.id = 2
                user2.firstName = "lisi"
                user2.lastName = "bbbb"
                RealmHelper.getInstanc().update(user2)
                val query = RealmHelper.getInstanc().query(User::class.java);
                tvContent.setText(query.toString())
            }
        }
        findViewById<Button>(R.id.bt_update_add).setOnClickListener {
        }
        findViewById<Button>(R.id.bt_query).setOnClickListener {
            lifecycleScope.launch {
                val query = RealmHelper.getInstanc().query(User::class.java);
                tvContent.setText(query.toString())
            }
        }
    }

}