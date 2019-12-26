package com.changyu.celo.userList.view

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.changyu.celo.R


class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        val iv = findViewById<ImageView>(R.id.iv_icon)
        val name = intent.getStringExtra("name")
        val tvName = findViewById<TextView>(R.id.tv_name)
        tvName.setText(name);
        val details = intent.getStringExtra("details")
        val tvDetail = findViewById<TextView>(R.id.tv_Details)
        tvDetail.setText("Details: "+details);
        val image = intent.getStringExtra("image")
        Glide.with(this).load(image).into(iv)

    }

}
