package example.android.com.utils;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * Created by nadeem on 17-09-2015.
 */
public class SmsNotifications extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        String actionName = intent.getAction();


        if(actionName.equals("in.chalrahi.sent")){
            switch(getResultCode()){
                case Activity.RESULT_OK:
                    Toast.makeText(context, "Message is sent successfully", Toast.LENGTH_SHORT).show();
                    break;
                default:
                    Toast.makeText(context, "Error in sending Message", Toast.LENGTH_SHORT).show();
                    break;
            }
        }

        if(actionName.equals("in.chalrahi.delivered")){
            switch(getResultCode()){
                case Activity.RESULT_OK:
                    Toast.makeText(context, "Message is delivered" , Toast.LENGTH_SHORT).show();
                    break;
                default:
                    Toast.makeText(context, "Error in the delivery of message", Toast.LENGTH_SHORT).show();
                    break;

            }
        }
    }
}
