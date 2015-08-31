package example.android.com.chalrahi;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private Button login;
    private Button register;
    private EditText mobileNumber;
    private EditText password;
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
        mobileNumber=(EditText)findViewById(R.id.etPhone);
        password=(EditText)findViewById(R.id.etPassword);
        login.setOnClickListener(this);
        register.setOnClickListener(this);
    }

    @Override
    public void onClick(View v)
    {
        Intent intent;
        Validator validator =new Validator();
        if (v==login){
            try
            {
                validator.validateLoginDetails(mobileNumber.getText().toString(),password.getText().toString());
                intent=new Intent(this,UserHomeActivity.class);
                startActivity(intent);
            } catch (Exception e) {
                Toast.makeText(this,e.getMessage(),Toast.LENGTH_SHORT).show();
            }
        }
        else if (v==register){
            intent=new Intent(this,RegisterActivity.class);
            startActivity(intent);
        }
    }
}