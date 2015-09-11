package example.android.com.chalrahi;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;;import java.util.Calendar;
import java.util.Date;

public class AutoBookActivity extends Fragment implements View.OnClickListener {

    private View view;
    private Button bookAnAuto;
    private Button sendAnAlert;
    static int count = 0;
    static Long currentTime = 0L;
    static Long firstDifference = 0L;
    static Long secondDifference = 0L;



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
}