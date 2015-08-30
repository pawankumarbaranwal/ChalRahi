package example.android.com.chalrahi;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    private Button login;
    private Button register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initializeView();

    }
    private void initializeView()
    {
        login=(Button)findViewById(R.id.btnLoginLink);
        register=(Button)findViewById(R.id.btnRegister);
        login.setOnClickListener(this);
        register.setOnClickListener(this);
    }

    @Override
    public void onClick(View v)
    {
        Intent intent;
        if (v==login){
            intent=new Intent(this,LoginActivity.class);
            startActivity(intent);
        }
        else if (v==register){

        }
    }
}
