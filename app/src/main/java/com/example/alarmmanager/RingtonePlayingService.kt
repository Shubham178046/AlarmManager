package com.example.alarmmanager

import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.os.IBinder
import android.util.Log


class RingtonePlayingService : Service() {
    var media_song: MediaPlayer? = null
    var startId = 0
    var isRunning = false
    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.d("onStartCommand", "Call")
        val state = intent!!.extras!!.getInt("extra")
        Log.d("TAG", "onStartCommand: "+state)
        var startId: Int = startId
        when (state) {
            0 -> {
                startId = 1
            }
            1 -> {
                startId = 0
            }
            else -> startId = 0
        }
        if (!this.isRunning && startId == 1) {
            Log.d("RingtonePlayingService", "and you want start");
            this.isRunning = true;
            this.startId = 0;
            media_song = MediaPlayer.create(this, R.raw.sample);
            media_song!!.start();
        } else if (this.isRunning && startId == 0) {
            Log.d("RingtonePlayingService", "there is music, and you want end");
            media_song!!.stop();
            media_song!!.reset();

            this.isRunning = false;
            this.startId = 0;
        } else if (!this.isRunning && startId == 0) {
            Log.d("RingtonePlayingService", "there is no music, and you want end");

            this.isRunning = false;
            this.startId = 0;
        } else if (this.isRunning && startId == 1) {
            Log.d("RingtonePlayingService", "there is music, and you want start");
            this.isRunning = true;
            this.startId = 1;
        } else {
            Log.d("else ", "somehow you reached this");
        }
        return START_NOT_STICKY;
    }

    override fun onDestroy() {
        super.onDestroy()
        this.isRunning = false;
    }
}