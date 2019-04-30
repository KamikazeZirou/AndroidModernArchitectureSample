package com.example.architecturelearning

import android.support.v7.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fragmentManager = supportFragmentManager
        val transaction = fragmentManager.beginTransaction()
        val fragment = UserProfileFragment()
        val args = Bundle()
        args.putString(UserProfileFragment.UID_KEY, "KamikazeZirou")
        fragment.arguments = args

        transaction.add(R.id.container, fragment)
        transaction.commit()

    }
}
