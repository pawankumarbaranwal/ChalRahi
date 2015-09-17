package example.android.com.chalrahi;

import android.app.Fragment;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;;import java.util.Calendar;
import java.util.Date;

import example.android.com.R;

public class AutoBookActivity extends Fragment implements View.OnClickListener {

    private View view;
    private Button bookAnAuto;
    private Button sendAnAlert;
    static int count = 0;
    static Long currentTime = 0L;
    static Long firstDifference = 0L;
    static Long secondDifference = 0L;
    Context context = null;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_auto_book, container, false);
        initializeData();
        return view;
    }

    private void initializeData() {
        bookAnAuto = (Button) view.findViewById(R.id.btnBookAnAuto);
        sendAnAlert = (Button) view.findViewById(R.id.btnSendAnAlert);
        bookAnAuto.setOnClickListener(this);
        sendAnAlert.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Date date= new Date();
        Long l=date.getTime()/3600;
        Calendar.getInstance().getTimeInMillis();
        if (v == bookAnAuto) {
            Toast.makeText(getActivity(), "Auto Booked"+l, Toast.LENGTH_SHORT).show();
            sendSMSToAdmin();
        } else if (v == sendAnAlert) {
            if (count==1000)
            {
                Toast.makeText(getActivity(), "You have already booked Auto", Toast.LENGTH_SHORT).show();
            }
            else {
                if (count == 0) {
                    currentTime = Calendar.getInstance().getTimeInMillis();
                    count++;
                } else if (count == 1) {
                    firstDifference = Calendar.getInstance().getTimeInMillis() - currentTime;
                    Log.i("FirstDifference", firstDifference + "");
                    currentTime = Calendar.getInstance().getTimeInMillis();
                    count++;
                } else {
                    secondDifference = Calendar.getInstance().getTimeInMillis() - currentTime;
                    Log.i("SecondDifference", secondDifference + "");
                    currentTime = Calendar.getInstance().getTimeInMillis();
                }
                if ((firstDifference != 0) && (secondDifference != 0)) {
                    Log.i("Difference", firstDifference + secondDifference + "");
                    if (firstDifference + secondDifference <= 2000) {
                        Toast.makeText(getActivity(), "Alert Sent" + firstDifference + secondDifference, Toast.LENGTH_SHORT).show();
                        count = 1000;
                    } else {
                        firstDifference = secondDifference;
                    }
                }
            }
            /*count++;

            if (count == 3) {
                count=0;
                Toast.makeText(getActivity(), "Alert Sent", Toast.LENGTH_SHORT).show();
            }*/
        }
    }
    public void sendSMSToAdmin(){
        PendingIntent piSent = PendingIntent.getBroadcast(this.getActivity().getBaseContext(), 0, new Intent("in.chalrahi.sent") , 0);

        /** Creating a pending intent which will be broadcasted when an sms message is successfully delivered */
        PendingIntent piDelivered = PendingIntent.getBroadcast(this.getActivity().getBaseContext(), 0, new Intent("in.chalrahi.delivered"), 0);

        /** Getting an instance of SmsManager to sent sms message from the application*/
        SmsManager smsManager = SmsManager.getDefault();

        /** Sending the Sms message to the intended party */
        //TODO Replace hard coded number with dynamic number of admin
        smsManager.sendTextMessage("8792859626", null, "Please provide an auto at Koramangla", piSent, piDelivered);

    }
}