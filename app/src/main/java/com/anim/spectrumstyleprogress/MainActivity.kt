package com.anim.spectrumstyleprogress

import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.anim.spectrumprogress.R
import com.anim.spectrumprogress.Spectrum
import com.anim.spectrumprogress.Type

class MainActivity : AppCompatActivity() {

    var spectrum: Spectrum?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        spectrum = findViewById(R.id.spectrum)

        spectrum!!.type = Type.MEDIA //set Type

        var startAnim: Button = findViewById(R.id.start_anim);

        startAnim.setOnClickListener {

            spectrum!!.startAnim(Type.MEDIA) //start anim

        }
    }

    override fun onPause() {
        super.onPause()

        spectrum!!.pauseAnims() //pause anim

    }

    override fun onResume() {
        super.onResume()

        spectrum!!.resumeAnims() //resume anim

    }
}