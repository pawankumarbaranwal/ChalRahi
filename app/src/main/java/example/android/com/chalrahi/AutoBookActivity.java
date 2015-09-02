package example.android.com.chalrahi;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;;

public class AutoBookActivity extends Fragment implements View.OnClickListener {

    private View view;
    private Button bookAnAuto;
    private Button sendAnAlert;
    static int count = 0;

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
        if (v == bookAnAuto) {
            Toast.makeText(getActivity(), "Auto Booked", Toast.LENGTH_SHORT).show();
        } else if (v == sendAnAlert) {
            count++;
            if (count == 3) {
                count=0;
                Toast.makeText(getActivity(), "Alert Sent", Toast.LENGTH_SHORT).show();
            }
        }
    }
}