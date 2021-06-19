package com.aplikasi.readinglistrevisi.activities

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.provider.Settings
import android.widget.Toast

class MyAirPlaneReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        if(Settings.System.getInt(context.contentResolver,
            Settings.Global.AIRPLANE_MODE_ON, 0)==0){
            Toast.makeText(context, "Airplane MODE : OFF", Toast.LENGTH_SHORT).show()
        } else {
        Toast.makeText(context, "AirPlane MODE : ON", Toast.LENGTH_SHORT).show() }

}}