# Welcome To SMS Reader (Get letest SMS in android studio) #

**Steps To implement The sms Preview in this(Almost Same for java)**

[(https://www.youtube.com/watch?v=S3gfmJrjUI0&t=228s)]

[**Note - You can refere a youtube video for java referance**] 
[**Note - Use Kotlin as programming languge in this app**] 


=> Create New android project name SMS Reader

=> Make Another Class for Broadcast Reciever


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
  

=>Add Following Perissions in Manifest File 

    <uses-permission android:name="android.permission.READ_SMS "/>
    <uses-permission android:name="android.permission.SEND_SMS"/>
    <uses-permission android:name="android.permission.READ_PHONE_NUMBERS"/>
    <uses-permission android:name="android.permission.READ_PHONE_STATE"/>
    <uses-permission android:name="android.permission.RECEIVE_SMS"/>
    
=>Check Permission in main activity

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

=>Register Reciever reciever and un reistrer reciever

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
      
=>Final Combin Code of Main Activity
  
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

              if(!message.isNullOrEmpty()){
                  textViewMessage?.text = message
              }

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
**Thankyou**
