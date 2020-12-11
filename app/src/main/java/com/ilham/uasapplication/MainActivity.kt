package com.ilham.uasapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import io.realm.Realm


class MainActivity : AppCompatActivity() {
    lateinit var realm : Realm
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        var fr = supportFragmentManager.beginTransaction()
        fr.add(R.id.fl_fragment, HomeFragment())
        fr.commit()
    }
}
