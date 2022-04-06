package com.example.smsreaderandroid

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.provider.Telephony
import android.telephony.SmsMessage
import android.util.Log


class OtpBrodcastReciever : BroadcastReceiver() {
    override fun onReceive(p0: Context?, p1: Intent?) {

        var messages: Array<out SmsMessage>? = Telephony.Sms.Intents.getMessagesFromIntent(p1)

        for (sms: SmsMessage in messages!!) {
            Log.i("OtpBrodcastReciever", sms.messageBody.toString())

            val i = Intent(p0, MainActivity::class.java)
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            i.putExtra("message", sms.messageBody)
            p0?.startActivity(i)
        }
    }
}