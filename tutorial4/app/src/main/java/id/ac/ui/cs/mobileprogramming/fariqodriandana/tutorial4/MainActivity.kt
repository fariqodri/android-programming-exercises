package id.ac.ui.cs.mobileprogramming.fariqodriandana.tutorial4

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.*
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var mService: Messenger? = null
    private var mIsBound = false
    private lateinit var mMessenger: Messenger

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mMessenger = Messenger(IncomingHandler(time_view))
        button_time.setOnClickListener {
            if (!mIsBound) return@setOnClickListener
            val msg = Message.obtain(null, MSG_GET_TIME, System.currentTimeMillis())
            msg.replyTo = mMessenger
            try {
                mService?.send(msg)
            } catch (e: RemoteException) {
                e.printStackTrace()
            }
        }
    }

    internal class IncomingHandler(private val textView: TextView) : Handler() {
        override fun handleMessage(msg: Message) {
            when (msg.what) {
                MSG_GET_TIME -> {
                    val timeFormatted = msg.obj as String
                    textView.text = timeFormatted
                }
                else -> {
                    super.handleMessage(msg)
                }
            }
        }
    }

    private val mConnection: ServiceConnection = object : ServiceConnection {
        override fun onServiceDisconnected(p0: ComponentName?) {
            mService = null
            mIsBound = false
        }

        override fun onServiceConnected(p0: ComponentName?, service: IBinder?) {
            mService = Messenger(service)
            mIsBound = true
        }
    }

    override fun onStart() {
        super.onStart()
        Intent(this, TimeService::class.java).also {
            bindService(it, mConnection, Context.BIND_AUTO_CREATE)
        }
    }

    override fun onStop() {
        super.onStop()
        if (mIsBound) {
            unbindService(mConnection)
            mIsBound = false
        }
    }

}
