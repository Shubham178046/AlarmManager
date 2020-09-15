package com.example.alarmmanager

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.media.MediaPlayer
import android.util.Log


class AlarmBroadCastReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        Log.d("AlarmBroadCastReceiver", "onReceive: "+"Call")
        var state = intent!!.getIntExtra("state", 0)
        val service_intent = Intent(context, RingtonePlayingService::class.java)
        service_intent.putExtra("extra", state);
        context!!.startService(service_intent);
        /* val vibrator = context!!.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
         vibrator.vibrate(2000)*/
    }

}