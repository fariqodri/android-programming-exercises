package id.ac.ui.cs.mobileprogramming.fariqodriandana.tutorial4

import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.*
import android.util.Log
import android.widget.Toast
import java.text.SimpleDateFormat
import java.util.*

const val MSG_GET_TIME = 1

class TimeService : Service() {

    override fun onBind(intent: Intent): IBinder {
        return binder
    }

    private val binder = object : ITimeAidlInterface.Stub() {
        override fun getPid(): Int = Process.myPid()

        override fun getTimeString(timeMillis: Long): String {
            val dateFormatter = SimpleDateFormat("dd/MM/yyyy HH:mm:ss", Locale("id-ID"))
            Log.d("time", dateFormatter.format(timeMillis))
            return dateFormatter.format(timeMillis)
        }
    }
}
