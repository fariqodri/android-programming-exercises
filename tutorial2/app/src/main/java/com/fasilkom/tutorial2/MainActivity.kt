package com.fasilkom.tutorial2

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.fasilkom.tutorial2.models.Biodata
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val biodata = Biodata(name_input.text.toString(), npm_input.text.toString(), email_input.text.toString())
        submit_button.setOnClickListener {
            name_display.text = biodata.name
            npm_display.text = biodata.npm
            email_display.text = biodata.email
        }
    }
}
