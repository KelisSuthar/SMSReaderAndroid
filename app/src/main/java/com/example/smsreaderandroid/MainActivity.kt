package com.example.smsreaderandroid

import android.Manifest
import android.content.IntentFilter
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {
    var otpBrodcastReciever: OtpBrodcastReciever? = null
    private var mIntentFilter: IntentFilter? = null
    var textViewMessage: TextView? = null

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val intent = intent
        val message = intent.getStringExtra("message")
        Toast.makeText(this, "TOAST EMSSGAge : " + message, Toast.LENGTH_SHORT).show()

        textViewMessage = findViewById(R.id.textViewMessage)

        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.RECEIVE_SMS
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            Toast.makeText(this, "Permission", Toast.LENGTH_SHORT).show()
            ActivityCompat.requestPermissions(
                this,
                arrayOf(
                    Manifest.permission.READ_SMS,
                    Manifest.permission.SEND_SMS,
                    Manifest.permission.READ_PHONE_STATE,
                    Manifest.permission.RECEIVE_SMS,

                    ),
                101
            )
        }
    }

    override fun onStart() {
        super.onStart()
        otpBrodcastReciever = OtpBrodcastReciever()
        mIntentFilter = IntentFilter()
        mIntentFilter!!.addAction("android.provider.Telephony.SMS_RECEIVED")
        registerReceiver(otpBrodcastReciever, mIntentFilter);
    }

    override fun onDestroy() {
        super.onDestroy()

        // Unregister the SMS receiver
        unregisterReceiver(otpBrodcastReciever)
    }

}