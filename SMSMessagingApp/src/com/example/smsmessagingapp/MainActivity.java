package com.example.smsmessagingapp;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends Activity {
	Button buttonSend;
	EditText textPhoneNo;
	EditText textSMS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        buttonSend = (Button) findViewById(R.id.buttonSend);
      		textPhoneNo = (EditText) findViewById(R.id.editTextPhoneNo);
      		textSMS = (EditText) findViewById(R.id.editTextSMS);

      		buttonSend.setOnClickListener(new OnClickListener() 
      		{

				@Override
				public void onClick(View v) 
				{
			     	String phoneNo = textPhoneNo.getText().toString();
	            	String message = textSMS.getText().toString();
	            	if (phoneNo.length()>0 && message.length()>0)
	            	{
	                    sendSMS(phoneNo, message);
	            	}
	                else
	                
	                	{
	Toast.makeText(getBaseContext(),"Please enter both phone number and message.", 
	                        Toast.LENGTH_SHORT).show();
	                	}}

				});}
    private void sendSMS(String phoneNo, String message) 
	{
        PendingIntent pi = PendingIntent.getActivity(this, 0,
                new Intent(this, test.class), 0);                
        SmsManager sms = SmsManager.getDefault();
        sms.sendTextMessage(phoneNo, null, message, pi, null);    
        
    	String SENT = "SMS_SENT";
    	String DELIVERED = "SMS_DELIVERED";
    	
        PendingIntent sentPI = PendingIntent.getBroadcast(this, 0,
            new Intent(SENT), 0);
        PendingIntent deliveredPI = PendingIntent.getBroadcast(this, 0,
                new Intent(DELIVERED), 0);

        //---when the SMS has been sent---
        registerReceiver(new BroadcastReceiver()
        {
			@Override
			public void onReceive(Context context, Intent intent) 
			{
switch(getResultCode())
{
case Activity.RESULT_OK:
    Toast.makeText(getBaseContext(), "SMS sent", Toast.LENGTH_SHORT).show();
    break;
case SmsManager.RESULT_ERROR_GENERIC_FAILURE:
    Toast.makeText(getBaseContext(), "Generic failure", 
    		Toast.LENGTH_SHORT).show();
    break;
case SmsManager.RESULT_ERROR_NO_SERVICE:
    Toast.makeText(getBaseContext(), "No service", 
    		Toast.LENGTH_SHORT).show();
    break;
case SmsManager.RESULT_ERROR_NULL_PDU:
    Toast.makeText(getBaseContext(), "Null PDU", 
    		Toast.LENGTH_SHORT).show();
    break;
case SmsManager.RESULT_ERROR_RADIO_OFF:
    Toast.makeText(getBaseContext(), "Radio off", 
    		Toast.LENGTH_SHORT).show();
    break;
}
}
}, new IntentFilter(SENT));

        //---when the SMS has been delivered---
        registerReceiver(new BroadcastReceiver()
        {

			@Override
			public void onReceive(Context context, Intent intent) 
			{
				switch (getResultCode())
				{
				    case Activity.RESULT_OK:
					    Toast.makeText(getBaseContext(), "SMS delivered", 
					    		Toast.LENGTH_SHORT).show();
					    break;
				    case Activity.RESULT_CANCELED:
					    Toast.makeText(getBaseContext(), "SMS not delivered", 
					    		Toast.LENGTH_SHORT).show();
					    break;					    
				}
			}
        }, new IntentFilter(DELIVERED));        
    	
        //SmsManager sms = SmsManager.getDefault();
        sms.sendTextMessage(phoneNo, null, message, sentPI, deliveredPI);               
    } 			
}
