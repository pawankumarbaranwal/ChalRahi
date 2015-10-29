package example.android.com.chalrahi;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;

import example.android.com.R;

public class OffersFragment extends Fragment {

    private View view;
    private RatingBar freeRidesAvailableTillNow;
    private RatingBar rideStatusToGetFreeRides1;
    private RatingBar rideStatusToGetFreeRides2;
    private RatingBar freeRidesByReferal;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_offers, container, false);
        initializeData();
        return view;
    }
    private void initializeData()
    {
        freeRidesAvailableTillNow=(RatingBar)view.findViewById(R.id.rbFreeRidesAvailableTillNow);
        rideStatusToGetFreeRides1=(RatingBar)view.findViewById(R.id.rbRideStatusToGetFreeRides1);
        rideStatusToGetFreeRides2=(RatingBar)view.findViewById(R.id.rbRideStatusToGetFreeRides2);
        freeRidesByReferal=(RatingBar)view.findViewById(R.id.rbFreeRidesByReferal);

        freeRidesAvailableTillNow.setRating(1);
        rideStatusToGetFreeRides1.setRating(2);
        rideStatusToGetFreeRides2.setRating(0);
        freeRidesByReferal.setRating(2);


    }
}
