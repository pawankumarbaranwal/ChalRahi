package example.android.com.chalrahi;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import example.android.com.utils.SharedPreferenceHandler;

public class ProfileFragment extends Fragment implements View.OnClickListener
{

    private View view;
    private Button signOut;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        view = inflater.inflate(R.layout.fragment_profile, container, false);
        initializeData();
        return view;
    }

    private void initializeData()
    {
        signOut=(Button)view.findViewById(R.id.btnSignOut);
        signOut.setOnClickListener(this);
    }

    @Override
    public void onClick(View v)
    {
     if (v==signOut)
     {
        SharedPreferenceHandler.deleteValue(getActivity());
         Intent intent=new Intent(getActivity(),LoginActivity.class);
         startActivity(intent);

     }
    }
}