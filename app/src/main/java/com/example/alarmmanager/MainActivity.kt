package com.example.alarmmanager

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    var alarmManager: AlarmManager? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var intent = Intent(this, AlarmBroadCastReceiver::class.java)
        alarmManager = getSystemService(ALARM_SERVICE) as AlarmManager
        btnSetAlarm.setOnClickListener {
            setAlarm()
        }
        btnCancelAlarm.setOnClickListener {
            val pendingIntent = PendingIntent.getBroadcast(
                this.applicationContext, 0, intent, 0
            )
            alarmManager!!.cancel(pendingIntent)
            intent.putExtra("state", 1)
            sendBroadcast(intent)
        }
    }

    private fun setAlarm() {
        val i: Int = edtSecond.getText().toString().toInt()
        var intent = Intent(this, AlarmBroadCastReceiver::class.java)
        intent.putExtra("state", 0)
        val pendingIntent = PendingIntent.getBroadcast(
            this.applicationContext, 0, intent, 0
        )
        val alarmManager = getSystemService(ALARM_SERVICE) as AlarmManager
        alarmManager[AlarmManager.RTC_WAKEUP, System.currentTimeMillis()
                + i * 1000] = pendingIntent
        Toast.makeText(this, "Alarm set in $i seconds", Toast.LENGTH_LONG).show()
    }
}