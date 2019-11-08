package com.fasilkom.tutorial3

import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.ref.WeakReference

class MainActivity : AppCompatActivity() {

    private lateinit var stopwatchRunner: StopwatchRunner

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        start_stopwatch.setOnClickListener {
            stopwatchRunner = StopwatchRunner(this)
            stopwatchRunner.execute(stopwatch_input.text.toString().toInt())
        }
        quit_button.setOnClickListener {
            finish()
        }
    }

    companion object {
        class StopwatchRunner internal constructor(context: MainActivity): AsyncTask<Int, Int, Unit>() {
            private val activityReference = WeakReference<MainActivity>(context)
            override fun onProgressUpdate(vararg values: Int?) {
                val activity = activityReference.get()
                if (activity == null || activity.isFinishing) return
                Log.d("Time", values.firstOrNull().toString())
                activity.stopwatch_result.text = values.firstOrNull().toString()
            }

            override fun doInBackground(vararg p0: Int?) {
                val count = p0[0]
                var i = count!!
                while (i >= 0) {
                    publishProgress(i)
                    i--
                    Thread.sleep(1000)
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        if (this::stopwatchRunner.isInitialized) {
            stopwatchRunner.cancel(true)
        }
    }

    override fun onBackPressed() {

    }


}
