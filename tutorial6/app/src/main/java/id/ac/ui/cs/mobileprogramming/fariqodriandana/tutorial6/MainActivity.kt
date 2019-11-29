package id.ac.ui.cs.mobileprogramming.fariqodriandana.tutorial6

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.text.toDouble
import kotlin.text.toInt

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // TODO: create logic for wifi connect

        equal.setOnClickListener {
            val baseNumber = base.text.toString().toDouble()
            val powerNumber = power.text.toString().toInt()
            calc_result.text = power(baseNumber, powerNumber).toString()
        }
    }

    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
    external fun power(base: Double, raise: Int): Double

    companion object {

        // Used to load the 'native-lib' library on application startup.
        init {
            System.loadLibrary("native-lib")
        }
    }
}
