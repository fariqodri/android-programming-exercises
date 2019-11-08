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

    private lateinit var mMessenger: Messenger

    override fun onBind(intent: Intent): IBinder {
        mMessenger = Messenger(IncomingHandler(this))
        return mMessenger.binder
    }


    internal class IncomingHandler(context: Context, private val applicationContext: Context = context.applicationContext) : Handler() {
        override fun handleMessage(msg: Message) {
            when(msg.what) {
                MSG_GET_TIME -> {
                    val currTime = msg.obj as Long
                    val dateFormatter = SimpleDateFormat("dd/MM/yyyy HH:mm:ss", Locale("id-ID"))
                    try {
                        msg.replyTo.send(Message.obtain(null, MSG_GET_TIME, dateFormatter.format(currTime)))
                    } catch (e: RemoteException) {
                        e.printStackTrace()
                    }
//                    Toast.makeText(applicationContext, dateFormatter.format(currTime), Toast.LENGTH_LONG).show()
                }
                else -> {
                    super.handleMessage(msg)
                }
            }
        }
    }
}
