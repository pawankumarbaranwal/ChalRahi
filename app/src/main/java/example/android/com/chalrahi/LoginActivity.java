package example.android.com.chalrahi;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.Arrays;
import java.util.List;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private Button login;
    private Button register;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initializeView();

    }

    private void initializeView()
    {
        login=(Button)findViewById(R.id.btnLoginAsCustomer);
        register=(Button)findViewById(R.id.btnRegisterLink);
        login.setOnClickListener(this);
        register.setOnClickListener(this);
    }

    @Override
    public void onClick(View v)
    {
        Intent intent;
        if (v==login){

        }
        else if (v==register){
            intent=new Intent(this,RegisterActivity.class);
            startActivity(intent);

        }
    }
}