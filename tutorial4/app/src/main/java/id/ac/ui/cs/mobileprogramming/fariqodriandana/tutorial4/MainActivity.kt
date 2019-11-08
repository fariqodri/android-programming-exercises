package id.ac.ui.cs.mobileprogramming.fariqodriandana.tutorial4

import android.app.Service
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.*
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var mService: ITimeAidlInterface? = null
    private var mIsBound = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.d("PID Activity", Process.myPid().toString())
        button_time.setOnClickListener {
            if (!mIsBound) return@setOnClickListener
            if (mService != null) {
                time_view.text = mService!!.getTimeString(System.currentTimeMillis())
            }
        }
    }

    private val mConnection: ServiceConnection = object : ServiceConnection {
        override fun onServiceDisconnected(p0: ComponentName?) {
            mService = null
            mIsBound = false
        }

        override fun onServiceConnected(p0: ComponentName?, boundService: IBinder?) {
            mService = ITimeAidlInterface.Stub.asInterface(boundService)
            mIsBound = true
        }
    }

    override fun onStart() {
        super.onStart()
        Intent(this, TimeService::class.java).also {
            bindService(it, mConnection, Service.BIND_AUTO_CREATE)
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
