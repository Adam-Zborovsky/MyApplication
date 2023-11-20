package com.example.myapplication

import android.os.Bundle
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.appcompat.widget.SwitchCompat
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import com.google.android.material.navigation.NavigationView

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val switch = findViewById<SwitchCompat>(R.id.switchOnOff)
        val containerRL = findViewById<RelativeLayout>(R.id.idRLContainer)
        val toolbar = findViewById<Toolbar>(R.id.idToolbar)
        val naviview = findViewById<NavigationView>(R.id.idNaviView)
        val tvSwitchShahar = findViewById<TextView>(R.id.tvSwitchYes)
        val tvSwitchAdam = findViewById<TextView>(R.id.tvSwitchNo)
        containerRL.background =
            ResourcesCompat.getDrawable(resources, R.drawable.background_adam, null)

        switch.setOnClickListener {
            if (switch.isChecked) {
                containerRL.background =
                    ResourcesCompat.getDrawable(resources, R.drawable.background_shahar, null)
                naviview.background = containerRL.background
                tvSwitchShahar.setTextColor(ContextCompat.getColor(this,R.color.white))
                tvSwitchAdam.setTextColor(ContextCompat.getColor(this,R.color.blue))

            } else {
                containerRL.background =
                    ResourcesCompat.getDrawable(resources, R.drawable.background_adam, null)
                naviview.background = containerRL.background
                tvSwitchShahar.setTextColor(ContextCompat.getColor(this,R.color.blue))
                tvSwitchAdam.setTextColor(ContextCompat.getColor(this,R.color.white))

            }
        }

    }
}