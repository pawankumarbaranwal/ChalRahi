package example.android.com.chalrahi;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import example.android.com.R;

public class InviteAndEarnFragment extends Fragment implements View.OnClickListener
{

    private View view;
    private Button inviteAndEarn;
    private TextView inviteMessage;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        view = inflater.inflate(R.layout.fragment_invite_and_earn, container, false);
        initializeData();
        setData();
        return view;
    }

    private void initializeData()
    {
            inviteMessage=(TextView)view.findViewById(R.id.tvInviteMessage);
            inviteAndEarn=(Button)view.findViewById(R.id.btnInvite);
            inviteAndEarn.setOnClickListener(this);
    }

    private void setData() {
        String message = new String("Invite your friend to download the app " +
                "You and your friend will get Rs.100 each once " +
                "your friend completes first auto ride. Your unique code is ");
        inviteMessage.setText(message.toString());
    }

    @Override
    public void onClick(View v)
    {
        if (v==inviteAndEarn){
            try
            { Intent i = new Intent(Intent.ACTION_SEND);
                i.setType("text/plain");
                i.putExtra(Intent.EXTRA_SUBJECT, "My application name");
                String messageToBeSent = "\nLet me recommend you this application \n";
                messageToBeSent = messageToBeSent+ "https://play.google.com/store/apps/details?id=Orion.Soft \n";
                i.putExtra(Intent.EXTRA_TEXT, messageToBeSent);
                startActivity(Intent.createChooser(i, "Choose One"));
            }
            catch(Exception e)
            { //e.toString();
            }
        }
    }
}