package id.ac.ui.cs.mobileprogramming.fariqodriandana.tutorial6

import android.content.Context
import android.net.wifi.WifiConfiguration
import android.net.wifi.WifiManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.text.toDouble
import kotlin.text.toInt

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        equal.setOnClickListener {
            val baseNumber = base.text.toString().toDouble()
            val powerNumber = power.text.toString().toInt()
            calc_result.text = power(baseNumber, powerNumber).toString()
        }

        connect.setOnClickListener {
            val wifiSSID = ssid.text
            val wifiPassword = password.text

            val wifi = applicationContext.getSystemService(Context.WIFI_SERVICE) as WifiManager
            wifi.isWifiEnabled = true

            val wifiConfiguration = WifiConfiguration()
            wifiConfiguration.SSID = String.format("\"%s\"", wifiSSID)
            wifiConfiguration.preSharedKey = String.format("\"%s\"", wifiPassword)

            val wifiManager = applicationContext.getSystemService(Context.WIFI_SERVICE) as WifiManager
            val netId = wifiManager.addNetwork(wifiConfiguration)
            wifiManager.disconnect()
            wifiManager.enableNetwork(netId, true)
            wifiManager.reconnect()
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
